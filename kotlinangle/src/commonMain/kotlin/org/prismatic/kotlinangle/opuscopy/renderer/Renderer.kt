package org.prismatic.kotlinangle.opuscopy.renderer

import org.prismatic.kotlinangle.opuscopy.MediaTime
import org.prismatic.kotlinangle.opuscopy.OpusConfig
import org.prismatic.kotlinangle.opuscopy.OpusNode
import org.prismatic.kotlinangle.opuscopy.OpusTree
import org.prismatic.kotlinangle.opuscopy.composable.core.CameraNode
import org.prismatic.kotlinangle.opuscopy.composable.core.GraphicsMesh
import org.prismatic.kotlinangle.opuscopy.composable.core.GraphicsMeshImpl
import org.prismatic.kotlinangle.opuscopy.composable.core.GraphicsTexture
import org.prismatic.kotlinangle.opuscopy.composable.core.GraphicsTextureNode
import org.prismatic.kotlinangle.opuscopy.composable.core.MeshNode
import org.prismatic.kotlinangle.opuscopy.composable.core.ProjectionType
import org.prismatic.kotlinangle.opuscopy.composable.core.TransformNode
import org.prismatic.kotlinangle.opuscopy.composable.core.VisualRenderNode
import org.prismatic.kotlinangle.opuscopy.forEachNodeOfType
import org.prismatic.opus.math.Mat4x4
import org.prismatic.opus.math.perspective
import org.prismatic.kotlinangle.opuscopy.size

internal interface OpusRenderer {
    fun dispose()
    fun PlatformVisualRenderContext.renderTree(tree: OpusTree)
}

interface OpusTexture {
    val width: Int
    val height: Int
}

interface OpusMesh

private data class CachedTexture(
    val key: Any,
    val texture: OpusTexture
)

private data class CachedMesh(
    val key: Any,
    val mesh: OpusMesh
)

internal class OpusRendererImpl : OpusRenderer {

    private val textureCache = mutableMapOf<GraphicsTexture,CachedTexture>()
    private val meshCache = mutableMapOf<GraphicsMesh,CachedMesh>()
    private var activeConfig : OpusConfig = OpusConfig()
    private lateinit var builtInMeshes : Map<GraphicsMesh,OpusMesh>

    private val QUAD_MESH = GraphicsMeshImpl()

    override fun dispose() {

    }

    override fun PlatformVisualRenderContext.renderTree(tree: OpusTree) {

        if(!::builtInMeshes.isInitialized) {
            builtInMeshes = mapOf(
                QUAD_MESH to loadMesh(Mesh.Quad(-1f,1f,1f,-1f))
            )
        }


        val playhead = tree.currentTime.takeOrElse { MediaTime.ZERO }

        fun findPrimaryCamera() : CameraNode? {
            var primaryCamera : CameraNode? = null
            tree.forEachNodeOfType<CameraNode>(playhead) { camera ->
                if( camera.priority > (primaryCamera?.priority ?: Int.MIN_VALUE) )
                    primaryCamera = camera
            }
            return primaryCamera
        }

        val primaryCamera = findPrimaryCamera()
        val viewMatrix: Mat4x4 = primaryCamera?.transform ?: Mat4x4.IDENTITY
        val projectionMatrix: Mat4x4 = when(primaryCamera?.projection) {
            ProjectionType.Perspective -> Mat4x4.perspective(
                fov = primaryCamera.fov,
                aspect = tree.config.width.toFloat() / tree.config.height,
                near = 0.3f,
                far = 1000f
            )
            ProjectionType.Orthographic -> TODO()
            null -> Mat4x4.IDENTITY
        }

        val usedTextures = mutableSetOf<GraphicsTexture>()
        val usedMeshes = mutableSetOf<GraphicsMesh>()

        if( tree.config.width != activeConfig.width || tree.config.height != activeConfig.height ) {
            textureCache.values.forEach { deleteTexture(it.texture) }
            textureCache.clear()
        }
        activeConfig = tree.config

        fillScreenRGB(0f,0f,0f)

        var entityDuration: MediaTime = MediaTime.Unspecified
        var entityLocalTime: MediaTime = MediaTime.Unspecified
        var modelMatrix: Mat4x4 = Mat4x4.IDENTITY

        val context = object : VisualRenderContext, CoreVisualRenderContext by this {

            override val entityDuration: MediaTime = entityDuration
            override val entityLocalTime: MediaTime = entityLocalTime
            override val opusDuration: MediaTime get() = tree.root.globalTiming.endTime
            override val opusHeight: Int = tree.config.width
            override val opusWidth: Int = tree.config.height
            override val Quad: GraphicsMesh = QUAD_MESH
            override val modelMatrix: Mat4x4 get() = modelMatrix
            override val projectionMatrix: Mat4x4 = projectionMatrix
            override val viewMatrix: Mat4x4 = viewMatrix

            override fun drawTexturedMesh(
                mesh: GraphicsMesh,
                texture: GraphicsTexture,
                transform: Mat4x4
            ) {
                val actualMesh = builtInMeshes[mesh] ?: meshCache[mesh]?.mesh ?: return
                val actualTexture  = textureCache[texture]?.texture ?: return
                drawTexturedMesh(actualMesh,actualTexture,transform)
            }

            override fun drawTexturedRect(
                texture: GraphicsTexture,
                left: Float,
                top: Float,
                right: Float,
                bottom: Float
            ) {
                textureCache[texture]?.texture?.let {
                    drawTexturedRect(it,left, top, right, bottom)
                }
            }
        }

        // Load any meshes we need for the actual rendering
        tree.forEachNodeOfType<MeshNode>(playhead) {
            val meshFactory = it.meshFactory
            val key = it.key
            val nodeId = it.meshId
            if( meshFactory!=null && key!=null && nodeId != null ) {
                usedMeshes += nodeId
                if( meshCache[nodeId]?.key != key ) {
                    meshCache[nodeId]?.let { deleteMesh(it.mesh) }
                    meshCache[nodeId] = CachedMesh(key,loadMesh(meshFactory()))
                }
            }
        }

        // Load any resources we need for the actual rendering
        tree.forEachNodeOfType<GraphicsTextureNode>(playhead) {
            val imageFactory = it.imageFactory
            val key = it.key
            val nodeId = it.textureId
            if( imageFactory!=null && key!=null && nodeId != null ) {
                usedTextures += nodeId
                if( textureCache[nodeId]?.key != key ) {
                    textureCache[nodeId]?.let { deleteTexture(it.texture) }
                    textureCache[nodeId] = CachedTexture(key,createTexture(imageFactory(tree.config.size)))
                }
            }
        }

        // Free any resources that we have loaded previously but no longer needed
        val unusedTextures = textureCache.keys - usedTextures
        unusedTextures.forEach { id ->
            textureCache.remove(id)?.let { deleteTexture(it.texture) }
        }
        val unusedMeshes = meshCache.keys - usedMeshes
        unusedMeshes.forEach { id ->
            meshCache.remove(id)?.let { deleteMesh(it.mesh) }
        }

        // Main rendering pass
        tree.forEachNodeOfType<VisualRenderNode>(playhead) {
            val onRender = it.onRender
            if (onRender != null) {
                val globalEndTime = it.globalTiming.endTime.takeOrElse { MediaTime.ZERO }
                val globalStartTime = it.globalTiming.startTime.takeOrElse { MediaTime.ZERO }
                entityDuration = (globalEndTime - globalStartTime).coerceAtLeast(MediaTime.ZERO)
                entityLocalTime = tree.currentTime - globalStartTime
                modelMatrix = it.computeModelMatrix()
                context.onRender()
            }
        }

        // Post-rendering pass (for overlays, etc.)
        tree.forEachNodeOfType<VisualRenderNode>(playhead) {
            val onPostRender = it.onPostRender
            if (onPostRender != null) {
                entityDuration = it.globalTiming.endTime - it.globalTiming.startTime
                entityLocalTime = tree.currentTime - it.globalTiming.startTime
                context.onPostRender()
            }
        }
    }
}

private fun OpusNode.computeModelMatrix(): Mat4x4 {
    val transformer = (this as? TransformNode)?.matrixTransformer
    val parentMatrix = parent?.computeModelMatrix() ?: Mat4x4.IDENTITY
    return if( transformer!=null ) transformer(parentMatrix) else parentMatrix
}
