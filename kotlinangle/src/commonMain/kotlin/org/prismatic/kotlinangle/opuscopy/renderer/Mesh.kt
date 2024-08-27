package org.prismatic.kotlinangle.opuscopy.renderer

import kotlinx.io.Buffer
import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.buildByteString
import kotlinx.io.readByteString
import kotlinx.io.snapshot
import kotlinx.io.write
import kotlinx.io.writeFloat
import kotlinx.io.writeFloatLe
import org.prismatic.opus.math.Vec2
import org.prismatic.opus.math.Vec3
import org.prismatic.opus.math.Vec4

interface Mesh {

    val primitiveType: PrimitiveType
    val attributeData: ByteString
    val elementData: ByteString?
    val vertexCount: Int
    val stride: Int
    val attributes: Map<Attr.Tag,Attr>

    enum class PrimitiveType {
        Triangles, TriangleStrip
    }

    interface Attr {

        val size: Int               // Number of components (must be 1, 2, 3, or 4)
        val type: Type
        val offset: Int

        data class VecType(val type: Type, val count: Int)

        sealed interface Type
        data object FLOAT : Type
        data object INT : Type

        sealed interface Tag
        data object POSITION : Tag
        data object TEXCOORD : Tag
        data object NORMAL : Tag
        data object COLOR : Tag

    }

    companion object

}

val Mesh.Attr.Type.sizeInBytes get() = when(this) {
    Mesh.Attr.FLOAT -> Float.SIZE_BYTES
    Mesh.Attr.INT -> Int.SIZE_BYTES
}

val Mesh.Attr.VecType.sizeInBytes get() = type.sizeInBytes * count

@DslMarker annotation class MeshBuilderDsl

@MeshBuilderDsl
interface MeshBuilder {

    var primitiveType: Mesh.PrimitiveType

    fun vertices(vararg vertices: VertexBuilder.()->Unit) = vertices.forEach { vertex(it) }
    fun elements(vararg indices: Int)

    fun vertex(block: VertexBuilder.()->Unit)

    infix fun Mesh.Attr.Tag.ofType(type: Mesh.Attr.Type) = this ofType type * 1
    infix fun Mesh.Attr.Tag.ofType(vecType: Mesh.Attr.VecType)
    operator fun Mesh.Attr.Type.times(count: Int) = Mesh.Attr.VecType(this,count)

    val POSITION get() = Mesh.Attr.POSITION
    val TEXCOORD get() = Mesh.Attr.TEXCOORD
    val NORMAL get() = Mesh.Attr.NORMAL
    val COLOR get() = Mesh.Attr.COLOR

    val FLOAT get() = Mesh.Attr.FLOAT
    val INT get() = Mesh.Attr.INT

    val TRIANGLES get() = Mesh.PrimitiveType.Triangles
    val TRIANGLE_STRIP get() = Mesh.PrimitiveType.TriangleStrip

}

interface MeshAttributeMatcher {
    fun Mesh.Attr.Tag.matches(type: Mesh.Attr.Type, size: Int) : Boolean
    fun Mesh.Attr.Tag.optionallyMatches(type: Mesh.Attr.Type, size: Int) : Boolean

    val POSITION get() = Mesh.Attr.POSITION
    val TEXCOORD get() = Mesh.Attr.TEXCOORD
    val NORMAL get() = Mesh.Attr.NORMAL
    val COLOR get() = Mesh.Attr.COLOR

    val FLOAT get() = Mesh.Attr.FLOAT
    val INT get() = Mesh.Attr.INT
}

fun Map<Mesh.Attr.Tag, Mesh.Attr>.check(block: MeshAttributeMatcher.()->Boolean) : Boolean {
    return object : MeshAttributeMatcher {
        override fun Mesh.Attr.Tag.matches(type: Mesh.Attr.Type, size: Int): Boolean =
            get(this)?.let { it.type == type && it.size == size } ?: false
        override fun Mesh.Attr.Tag.optionallyMatches(type: Mesh.Attr.Type, size: Int): Boolean =
            get(this)?.let { it.type == type && it.size == size } ?: true
    }.block()
}

fun test(m: Mesh) {
    m.attributes.check {
        POSITION.matches(FLOAT,3)
    }
}

@MeshBuilderDsl
interface VertexBuilder {
    operator fun Mesh.Attr.Tag.invoke(value: Float)
    operator fun Mesh.Attr.Tag.invoke(x: Float, y: Float)
    operator fun Mesh.Attr.Tag.invoke(x: Float, y: Float, z: Float)
    operator fun Mesh.Attr.Tag.invoke(x: Float, y: Float, z: Float, w: Float)

    operator fun Mesh.Attr.Tag.invoke(value: Int)
    operator fun Mesh.Attr.Tag.invoke(x: Int, y: Int)
    operator fun Mesh.Attr.Tag.invoke(x: Int, y: Int, z: Int)
    operator fun Mesh.Attr.Tag.invoke(x: Int, y: Int, z: Int, w: Int)

    operator fun Mesh.Attr.Tag.invoke(value: Long)
    operator fun Mesh.Attr.Tag.invoke(x: Long, y: Long)
    operator fun Mesh.Attr.Tag.invoke(x: Long, y: Long, z: Long)
    operator fun Mesh.Attr.Tag.invoke(x: Long, y: Long, z: Long, w: Long)

    operator fun Mesh.Attr.Tag.invoke(value: Vec4) = invoke(value.x, value.y, value.z, value.w)
    operator fun Mesh.Attr.Tag.invoke(value: Vec3) = invoke(value.x, value.y, value.z)
    operator fun Mesh.Attr.Tag.invoke(value: Vec2) = invoke(value.x, value.y)

    val POSITION get() = Mesh.Attr.POSITION
    val TEXCOORD get() = Mesh.Attr.TEXCOORD
    val NORMAL get() = Mesh.Attr.NORMAL
    val COLOR get() = Mesh.Attr.COLOR
}

fun  buildMesh(block: MeshBuilder.()->Unit) : Mesh = MeshBuilderImpl().also(block).build()

private class MeshBuilderImpl() : MeshBuilder {

    override var primitiveType: Mesh.PrimitiveType = Mesh.PrimitiveType.Triangles
    var elements : IntArray? = null
    val attribues = mutableMapOf<Mesh.Attr.Tag,Mesh.Attr>()
    var offset = 0
    val attributeBuffer = Buffer()
    val vertexBuilder = VertexBuilderImpl(this,attributeBuffer)
    var vertexCount = 0

    override fun vertex(block: VertexBuilder.() -> Unit) {
        vertexBuilder.reset()
        vertexBuilder.block()
        vertexBuilder.flush()
        vertexCount++
    }

    override fun elements(vararg indices: Int) {
        elements = elements?.let { it + indices } ?: indices
    }

    override fun Mesh.Attr.Tag.ofType(vecType: Mesh.Attr.VecType) {
        check(this !in attribues) {"Duplicate attribute: $this"}
        check( attributeBuffer.size == 0L ) {"Cannot define attributes after vertices"}
        offset = alignVertexBufferOffset(offset, vecType.type)
        attribues[this] = AttrImpl(size = vecType.count, type = vecType.type, offset = offset)
        offset += vecType.sizeInBytes
    }

    fun build() : Mesh = MeshImpl(
        primitiveType = primitiveType,
        attributeData = attributeBuffer.snapshot(),
        elementData = elements?.let { Buffer().apply { it.forEach { writeInt(it) }  }.readByteString() },
        vertexCount = vertexCount,
        stride = offset,
        attributes = attribues
    )

}

/**
 * This is a no-op for now, but could be replaced with expect/actual if we need
 * to do alignment of types in buffer. The idea is it would return offset advanced to the
 * appropriate alignment for the given type.
 */
internal fun alignVertexBufferOffset(offset: Int, type: Mesh.Attr.Type) = offset

inline fun Buffer.writeTyped(type: Mesh.Attr.Type, vararg value: Float) {
    value.forEach {
        when(type) {
            Mesh.Attr.FLOAT -> writeFloatLe(it)
            Mesh.Attr.INT -> writeInt(it.toInt())
        }
    }
}

 inline fun Buffer.writeTyped(type: Mesh.Attr.Type, value: Int) {
    when(type) {
        Mesh.Attr.FLOAT -> writeFloatLe(value.toFloat())
        Mesh.Attr.INT -> writeInt(value)
    }
}

 inline fun <reified T> Buffer.writeTyped(type: Mesh.Attr.Type, value: T) {
    when(value) {
        is Int -> writeTyped(type,value)
        is Float -> writeTyped(type,value)
        else -> throw IllegalStateException()
    }
}

private class VertexBuilderImpl(val meshBuilder: MeshBuilderImpl, val buffer: Buffer) : VertexBuilder {

    private var initialBufferSize = buffer.size
    private val offset : Int get() = (buffer.size - initialBufferSize).toInt()
    private var deferredWrites: MutableMap<Int, () -> Unit>? = null

    fun reset() {
        initialBufferSize = buffer.size
        println("---BEGIN VERTEX---")
    }

    private inline fun <reified T> Mesh.Attr.Tag.provideAttr(vararg values: T) {
        println("Provide Attr: $this = ${values.joinToString(",")}")
        deferredWrites?.remove(offset)?.invoke()
        val attr = meshBuilder.attribues[this] ?: throw IllegalStateException("No such attribute: $this")
        require(values.size == attr.size) {"Argument count mismatch for $this: Expected ${attr.size} but got ${values.size}."}
        if( attr.offset == offset ) {
            println("  -- Write Attr: $this(${attr.type}*${attr.size}@${attr.offset}) = ${values.joinToString(",")}")
            values.forEach { buffer.writeTyped(attr.type,it) }
        } else {
            if( deferredWrites==null ) deferredWrites = mutableMapOf()
            deferredWrites!![attr.offset] = {
                println("  -- Write (Deferred) Attr: $this(${attr.type}*${attr.size}@${attr.offset}) = ${values.joinToString(",")}")
                values.forEach { buffer.writeTyped(attr.type,it) }
            }
        }
    }

    override fun Mesh.Attr.Tag.invoke(value: Float) = provideAttr(value)
    override fun Mesh.Attr.Tag.invoke(x: Float, y: Float) = provideAttr(x,y)
    override fun Mesh.Attr.Tag.invoke(x: Float, y: Float, z: Float) = provideAttr(x,y,z)
    override fun Mesh.Attr.Tag.invoke(x: Float, y: Float, z: Float, w: Float) = provideAttr(x,y,z,w)

    override fun Mesh.Attr.Tag.invoke(value: Int) = provideAttr(value)
    override fun Mesh.Attr.Tag.invoke(x: Int, y: Int) = provideAttr(x,y)
    override fun Mesh.Attr.Tag.invoke(x: Int, y: Int, z: Int) = provideAttr(x,y,z)
    override fun Mesh.Attr.Tag.invoke(x: Int, y: Int, z: Int, w: Int) = provideAttr(x,y,z,w)

    override fun Mesh.Attr.Tag.invoke(value: Long) = provideAttr(value)
    override fun Mesh.Attr.Tag.invoke(x: Long, y: Long) = provideAttr(x,y)
    override fun Mesh.Attr.Tag.invoke(x: Long, y: Long, z: Long) = provideAttr(x,y,z)
    override fun Mesh.Attr.Tag.invoke(x: Long, y: Long, z: Long, w: Long) = provideAttr(x,y,z,w)

    fun flush() {
        while( true ) deferredWrites?.remove(offset)?.invoke() ?: break
        check(deferredWrites?.isEmpty() != false)
        check(offset == meshBuilder.offset)
    }
}

private data class MeshImpl(
    override val primitiveType: Mesh.PrimitiveType,
    override val attributeData: ByteString,
    override val elementData: ByteString?,
    override val vertexCount: Int,
    override val stride: Int,
    override val attributes: Map<Mesh.Attr.Tag, Mesh.Attr>
) : Mesh {
    init {
        check(vertexCount * stride == attributeData.size) {"$vertexCount * $stride != ${attributeData.size}"}
    }
}

private data class AttrImpl(
    override val size: Int,
    override val type: Mesh.Attr.Type,
    override val offset: Int
) : Mesh.Attr

fun Mesh.Companion.Quad(left: Float, top: Float, right: Float, bottom: Float) = texturedMesh3D(
    left, bottom, 0f,   0f, 0f,
    right, bottom, 0f,  1f, 0f,
    left, top, 0f,      0f, 1f,
    right, top, 0f,     1f, 1f,
)//.withElements(0, 1, 2, 1, 2, 3)

fun texturedMesh3D(vararg data: Float) = buildMesh {
    require(data.size % 5 == 0)
    primitiveType = TRIANGLE_STRIP
    POSITION ofType FLOAT*3
    TEXCOORD ofType FLOAT*2
    for (i in data.indices step 5) {
        vertex {
            POSITION(data[i+0],data[i+1],data[i+2])
            TEXCOORD(data[i+3],data[i+4])
        }
    }
}

fun Mesh.withElements(vararg elements: Int): Mesh = (this as MeshImpl)
    .copy(elementData = Buffer()
        .apply { elements.forEach { writeInt(it) } }
        .readByteString())