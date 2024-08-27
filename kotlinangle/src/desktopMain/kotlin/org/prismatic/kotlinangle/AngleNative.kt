package org.prismatic.kotlinangle

import com.jogamp.common.nio.Buffers
import com.jogamp.common.nio.PointerBuffer
//import com.jogamp.common.os.DynamicLibraryBundle
//import com.jogamp.common.os.DynamicLibraryBundleInfo
import java.io.File
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer


object AngleNative: AngleWrapper {
	init {
		try {
//			val info = DynamicLibraryBundleInfoz
//			val lib = DynamicLibraryBundle()
			val baseDir = System.getProperty("user.dir")
			val dylibPath =
				"$baseDir/../../kotlinangle/build/libs/libPrismAngleLibrary.dylib"
			println("Attempting to load .dylib from: $dylibPath")
			val dylibFile = File(dylibPath)
			if (dylibFile.exists()) {
				System.load(dylibPath)
			} else {
				throw UnsatisfiedLinkError("Library not found at: $dylibPath")
			}
			// System.loadLibrary("PrismAngleLibrary");
		} catch (e: Exception) {
			e.printStackTrace()
			throw e
		}
	}

	/** Defined as part of enum type "khronos_boolean_enum_t" with expression '`0`', CType: int  */
	const val KHRONOS_FALSE: Int = 0x0

	/** Defined as part of enum type "khronos_boolean_enum_t" with expression '`1`', CType: int  */
	const val KHRONOS_TRUE: Int = 0x1

	/** Defined as part of enum type "khronos_boolean_enum_t" with expression '`0x7FFFFFFF`', CType: int  */
	const val KHRONOS_BOOLEAN_ENUM_FORCE_SIZE: Int = 0x7fffffff

	/** Define "GL_CW" with expression '`0x0900`', CType: int  */
	const val GL_CW: Int = 0x900

	/** Define "GL_ALIASED_POINT_SIZE_RANGE" with expression '`0x846D`', CType: int  */
	const val GL_ALIASED_POINT_SIZE_RANGE: Int = 0x846d

	/** Define "GL_MAX_CUBE_MAP_TEXTURE_SIZE" with expression '`0x851C`', CType: int  */
	const val GL_MAX_CUBE_MAP_TEXTURE_SIZE: Int = 0x851c

	/** Define "GL_LEQUAL" with expression '`0x0203`', CType: int  */
	const val GL_LEQUAL: Int = 0x203

	/** Define "GL_LOW_FLOAT" with expression '`0x8DF0`', CType: int  */
	const val GL_LOW_FLOAT: Int = 0x8df0

	/** Define "GL_ONE_MINUS_SRC_COLOR" with expression '`0x0301`', CType: int  */
	const val GL_ONE_MINUS_SRC_COLOR: Int = 0x301

	/** Define "GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS" with expression '`0x8CD9`', CType: int  */
	const val GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS: Int = 0x8cd9

	/** Define "GL_ACTIVE_ATTRIBUTE_MAX_LENGTH" with expression '`0x8B8A`', CType: int  */
	const val GL_ACTIVE_ATTRIBUTE_MAX_LENGTH: Int = 0x8b8a

	/** Define "GL_SCISSOR_TEST" with expression '`0x0C11`', CType: int  */
	const val GL_SCISSOR_TEST: Int = 0xc11

	/** Define "GL_ARRAY_BUFFER_BINDING" with expression '`0x8894`', CType: int  */
	const val GL_ARRAY_BUFFER_BINDING: Int = 0x8894

	/** Define "GL_UNSIGNED_INT" with expression '`0x1405`', CType: int  */
	const val GL_UNSIGNED_INT: Int = 0x1405

	/** Define "GL_ONE_MINUS_DST_COLOR" with expression '`0x0307`', CType: int  */
	const val GL_ONE_MINUS_DST_COLOR: Int = 0x307

	/** Define "GL_DELETE_STATUS" with expression '`0x8B80`', CType: int  */
	const val GL_DELETE_STATUS: Int = 0x8b80

	/** Define "GL_GREEN_BITS" with expression '`0x0D53`', CType: int  */
	const val GL_GREEN_BITS: Int = 0xd53

	/** Define "GL_ACTIVE_UNIFORMS" with expression '`0x8B86`', CType: int  */
	const val GL_ACTIVE_UNIFORMS: Int = 0x8b86

	/** Define "GL_FRAMEBUFFER_COMPLETE" with expression '`0x8CD5`', CType: int  */
	const val GL_FRAMEBUFFER_COMPLETE: Int = 0x8cd5

	/** Define "GL_VENDOR" with expression '`0x1F00`', CType: int  */
	const val GL_VENDOR: Int = 0x1f00

	/** Define "GL_CURRENT_VERTEX_ATTRIB" with expression '`0x8626`', CType: int  */
	const val GL_CURRENT_VERTEX_ATTRIB: Int = 0x8626

	/** Define "GL_ALIASED_LINE_WIDTH_RANGE" with expression '`0x846E`', CType: int  */
	const val GL_ALIASED_LINE_WIDTH_RANGE: Int = 0x846e

	/** Define "GL_RENDERBUFFER_BINDING" with expression '`0x8CA7`', CType: int  */
	const val GL_RENDERBUFFER_BINDING: Int = 0x8ca7

	/** Define "GL_INVALID_ENUM" with expression '`0x0500`', CType: int  */
	const val GL_INVALID_ENUM: Int = 0x500

	/** Define "GL_RED_BITS" with expression '`0x0D52`', CType: int  */
	const val GL_RED_BITS: Int = 0xd52

	/** Define "GL_CCW" with expression '`0x0901`', CType: int  */
	const val GL_CCW: Int = 0x901

	/** Define "GL_SHORT" with expression '`0x1402`', CType: int  */
	const val GL_SHORT: Int = 0x1402

	/** Define "GL_VERTEX_SHADER" with expression '`0x8B31`', CType: int  */
	const val GL_VERTEX_SHADER: Int = 0x8b31

	/** Define "GL_COLOR_BUFFER_BIT" with expression '`0x00004000`', CType: int  */
	const val GL_COLOR_BUFFER_BIT: Int = 0x4000

	/** Define "GL_STENCIL_REF" with expression '`0x0B97`', CType: int  */
	const val GL_STENCIL_REF: Int = 0xb97

	/** Define "GL_INFO_LOG_LENGTH" with expression '`0x8B84`', CType: int  */
	const val GL_INFO_LOG_LENGTH: Int = 0x8b84

	/** Define "GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL" with expression '`0x8CD2`', CType: int  */
	const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL: Int = 0x8cd2

	/** Define "GL_DEPTH_ATTACHMENT" with expression '`0x8D00`', CType: int  */
	const val GL_DEPTH_ATTACHMENT: Int = 0x8d00

	/** Define "GL_SHADER_COMPILER" with expression '`0x8DFA`', CType: int  */
	const val GL_SHADER_COMPILER: Int = 0x8dfa

	/** Define "GL_TEXTURE" with expression '`0x1702`', CType: int  */
	const val GL_TEXTURE: Int = 0x1702

	/** Define "GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT" with expression '`0x8CD7`', CType: int  */
	const val GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT: Int = 0x8cd7

	/** Define "GL_TEXTURE_CUBE_MAP" with expression '`0x8513`', CType: int  */
	const val GL_TEXTURE_CUBE_MAP: Int = 0x8513

	/** Define "GL_NUM_SHADER_BINARY_FORMATS" with expression '`0x8DF9`', CType: int  */
	const val GL_NUM_SHADER_BINARY_FORMATS: Int = 0x8df9

	/** Define "GL_ALWAYS" with expression '`0x0207`', CType: int  */
	const val GL_ALWAYS: Int = 0x207

	/** Define "GL_SAMPLE_COVERAGE_VALUE" with expression '`0x80AA`', CType: int  */
	const val GL_SAMPLE_COVERAGE_VALUE: Int = 0x80aa

	/** Define "GL_INVALID_FRAMEBUFFER_OPERATION" with expression '`0x0506`', CType: int  */
	const val GL_INVALID_FRAMEBUFFER_OPERATION: Int = 0x506

	/** Define "GL_ONE_MINUS_DST_ALPHA" with expression '`0x0305`', CType: int  */
	const val GL_ONE_MINUS_DST_ALPHA: Int = 0x305

	/** Define "GL_MAX_FRAGMENT_UNIFORM_VECTORS" with expression '`0x8DFD`', CType: int  */
	const val GL_MAX_FRAGMENT_UNIFORM_VECTORS: Int = 0x8dfd

	/** Define "GL_INVERT" with expression '`0x150A`', CType: int  */
	const val GL_INVERT: Int = 0x150a

	/** Define "GL_NEAREST" with expression '`0x2600`', CType: int  */
	const val GL_NEAREST: Int = 0x2600

	/** Define "GL_RENDERBUFFER" with expression '`0x8D41`', CType: int  */
	const val GL_RENDERBUFFER: Int = 0x8d41

	/** Define "GL_MAX_VERTEX_UNIFORM_VECTORS" with expression '`0x8DFB`', CType: int  */
	const val GL_MAX_VERTEX_UNIFORM_VECTORS: Int = 0x8dfb

	/** Define "GL_STENCIL_PASS_DEPTH_PASS" with expression '`0x0B96`', CType: int  */
	const val GL_STENCIL_PASS_DEPTH_PASS: Int = 0xb96

	/** Define "GL_STENCIL_BACK_WRITEMASK" with expression '`0x8CA5`', CType: int  */
	const val GL_STENCIL_BACK_WRITEMASK: Int = 0x8ca5

	/** Define "GL_TRUE" with expression '`1`', CType: int  */
	const val GL_TRUE: Int = 0x1

	/** Define "GL_LINE_STRIP" with expression '`0x0003`', CType: int  */
	const val GL_LINE_STRIP: Int = 0x3

	/** Define "GL_LUMINANCE" with expression '`0x1909`', CType: int  */
	const val GL_LUMINANCE: Int = 0x1909

	/** Define "GL_DEPTH_TEST" with expression '`0x0B71`', CType: int  */
	const val GL_DEPTH_TEST: Int = 0xb71

	/** Define "GL_INVALID_OPERATION" with expression '`0x0502`', CType: int  */
	const val GL_INVALID_OPERATION: Int = 0x502

	/** Define "GL_FRAGMENT_SHADER" with expression '`0x8B30`', CType: int  */
	const val GL_FRAGMENT_SHADER: Int = 0x8b30

	/** Define "GL_CULL_FACE" with expression '`0x0B44`', CType: int  */
	const val GL_CULL_FACE: Int = 0xb44

	/** Define "GL_BLEND_EQUATION_ALPHA" with expression '`0x883D`', CType: int  */
	const val GL_BLEND_EQUATION_ALPHA: Int = 0x883d

	/** Define "GL_BLEND_SRC_RGB" with expression '`0x80C9`', CType: int  */
	const val GL_BLEND_SRC_RGB: Int = 0x80c9

	/** Define "GL_NO_ERROR" with expression '`0`', CType: int  */
	const val GL_NO_ERROR: Int = 0x0

	/** Define "GL_FRONT_AND_BACK" with expression '`0x0408`', CType: int  */
	const val GL_FRONT_AND_BACK: Int = 0x408

	/** Define "GL_DST_COLOR" with expression '`0x0306`', CType: int  */
	const val GL_DST_COLOR: Int = 0x306

	/** Define "GL_VIEWPORT" with expression '`0x0BA2`', CType: int  */
	const val GL_VIEWPORT: Int = 0xba2

	/** Define "GL_LESS" with expression '`0x0201`', CType: int  */
	const val GL_LESS: Int = 0x201

	/** Define "GL_POLYGON_OFFSET_FACTOR" with expression '`0x8038`', CType: int  */
	const val GL_POLYGON_OFFSET_FACTOR: Int = 0x8038

	/** Define "GL_LUMINANCE_ALPHA" with expression '`0x190A`', CType: int  */
	const val GL_LUMINANCE_ALPHA: Int = 0x190a

	/** Define "GL_SHADER_BINARY_FORMATS" with expression '`0x8DF8`', CType: int  */
	const val GL_SHADER_BINARY_FORMATS: Int = 0x8df8

	/** Define "GL_BLEND_COLOR" with expression '`0x8005`', CType: int  */
	const val GL_BLEND_COLOR: Int = 0x8005

	/** Define "GL_STENCIL_VALUE_MASK" with expression '`0x0B93`', CType: int  */
	const val GL_STENCIL_VALUE_MASK: Int = 0xb93

	/** Define "GL_DEPTH_COMPONENT" with expression '`0x1902`', CType: int  */
	const val GL_DEPTH_COMPONENT: Int = 0x1902

	/** Define "GL_TEXTURE_WRAP_S" with expression '`0x2802`', CType: int  */
	const val GL_TEXTURE_WRAP_S: Int = 0x2802

	/** Define "GL_DEPTH_CLEAR_VALUE" with expression '`0x0B73`', CType: int  */
	const val GL_DEPTH_CLEAR_VALUE: Int = 0xb73

	/** Define "GL_TEXTURE_WRAP_T" with expression '`0x2803`', CType: int  */
	const val GL_TEXTURE_WRAP_T: Int = 0x2803

	/** Define "GL_ONE" with expression '`1`', CType: int  */
	const val GL_ONE: Int = 0x1

	/** Define "GL_GREATER" with expression '`0x0204`', CType: int  */
	const val GL_GREATER: Int = 0x204

	/** Define "GL_DEPTH_FUNC" with expression '`0x0B74`', CType: int  */
	const val GL_DEPTH_FUNC: Int = 0xb74

	/** Define "GL_ELEMENT_ARRAY_BUFFER" with expression '`0x8893`', CType: int  */
	const val GL_ELEMENT_ARRAY_BUFFER: Int = 0x8893

	/** Define "GL_KEEP" with expression '`0x1E00`', CType: int  */
	const val GL_KEEP: Int = 0x1e00

	/** Define "GL_LINEAR_MIPMAP_NEAREST" with expression '`0x2701`', CType: int  */
	const val GL_LINEAR_MIPMAP_NEAREST: Int = 0x2701

	/** Define "GL_STENCIL_BACK_FAIL" with expression '`0x8801`', CType: int  */
	const val GL_STENCIL_BACK_FAIL: Int = 0x8801

	/** Define "GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME" with expression '`0x8CD1`', CType: int  */
	const val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME: Int = 0x8cd1

	/** Define "GL_MAX_RENDERBUFFER_SIZE" with expression '`0x84E8`', CType: int  */
	const val GL_MAX_RENDERBUFFER_SIZE: Int = 0x84e8

	/** Define "GL_REPLACE" with expression '`0x1E01`', CType: int  */
	const val GL_REPLACE: Int = 0x1e01

	/** Define "GL_STENCIL_BACK_PASS_DEPTH_PASS" with expression '`0x8803`', CType: int  */
	const val GL_STENCIL_BACK_PASS_DEPTH_PASS: Int = 0x8803

	/** Define "GL_MEDIUM_FLOAT" with expression '`0x8DF1`', CType: int  */
	const val GL_MEDIUM_FLOAT: Int = 0x8df1

	/** Define "GL_RENDERBUFFER_STENCIL_SIZE" with expression '`0x8D55`', CType: int  */
	const val GL_RENDERBUFFER_STENCIL_SIZE: Int = 0x8d55

	/** Define "GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS" with expression '`0x8B4C`', CType: int  */
	const val GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS: Int = 0x8b4c

	/** Define "GL_FRAMEBUFFER" with expression '`0x8D40`', CType: int  */
	const val GL_FRAMEBUFFER: Int = 0x8d40

	/** Define "GL_STENCIL_BACK_VALUE_MASK" with expression '`0x8CA4`', CType: int  */
	const val GL_STENCIL_BACK_VALUE_MASK: Int = 0x8ca4

	/** Define "GL_TRIANGLES" with expression '`0x0004`', CType: int  */
	const val GL_TRIANGLES: Int = 0x4

	/** Define "GL_TEXTURE_2D" with expression '`0x0DE1`', CType: int  */
	const val GL_TEXTURE_2D: Int = 0xde1

	/** Define "GL_HIGH_FLOAT" with expression '`0x8DF2`', CType: int  */
	const val GL_HIGH_FLOAT: Int = 0x8df2

	/** Define "GL_ARRAY_BUFFER" with expression '`0x8892`', CType: int  */
	const val GL_ARRAY_BUFFER: Int = 0x8892

	/** Define "GL_STENCIL_BACK_FUNC" with expression '`0x8800`', CType: int  */
	const val GL_STENCIL_BACK_FUNC: Int = 0x8800

	/** Define "GL_FIXED" with expression '`0x140C`', CType: int  */
	const val GL_FIXED: Int = 0x140c

	/** Define "GL_DEPTH_BUFFER_BIT" with expression '`0x00000100`', CType: int  */
	const val GL_DEPTH_BUFFER_BIT: Int = 0x100

	/** Define "GL_LINEAR" with expression '`0x2601`', CType: int  */
	const val GL_LINEAR: Int = 0x2601

	/** Define "GL_ACTIVE_UNIFORM_MAX_LENGTH" with expression '`0x8B87`', CType: int  */
	const val GL_ACTIVE_UNIFORM_MAX_LENGTH: Int = 0x8b87

	/** Define "GL_RGBA" with expression '`0x1908`', CType: int  */
	const val GL_RGBA: Int = 0x1908

	/** Define "GL_NEAREST_MIPMAP_LINEAR" with expression '`0x2702`', CType: int  */
	const val GL_NEAREST_MIPMAP_LINEAR: Int = 0x2702

	/** Define "GL_RENDERBUFFER_RED_SIZE" with expression '`0x8D50`', CType: int  */
	const val GL_RENDERBUFFER_RED_SIZE: Int = 0x8d50

	/** Define "GL_SAMPLE_COVERAGE" with expression '`0x80A0`', CType: int  */
	const val GL_SAMPLE_COVERAGE: Int = 0x80a0

	/** Define "GL_MAX_TEXTURE_SIZE" with expression '`0x0D33`', CType: int  */
	const val GL_MAX_TEXTURE_SIZE: Int = 0xd33

	/** Define "GL_DYNAMIC_DRAW" with expression '`0x88E8`', CType: int  */
	const val GL_DYNAMIC_DRAW: Int = 0x88e8

	/** Define "GL_OUT_OF_MEMORY" with expression '`0x0505`', CType: int  */
	const val GL_OUT_OF_MEMORY: Int = 0x505

	/** Define "GL_EXTENSIONS" with expression '`0x1F03`', CType: int  */
	const val GL_EXTENSIONS: Int = 0x1f03

	/** Define "GL_ACTIVE_TEXTURE" with expression '`0x84E0`', CType: int  */
	const val GL_ACTIVE_TEXTURE: Int = 0x84e0

	/** Define "GL_COLOR_WRITEMASK" with expression '`0x0C23`', CType: int  */
	const val GL_COLOR_WRITEMASK: Int = 0xc23

	/** Define "GL_NONE" with expression '`0`', CType: int  */
	const val GL_NONE: Int = 0x0

	/** Define "GL_UNSIGNED_SHORT_5_6_5" with expression '`0x8363`', CType: int  */
	const val GL_UNSIGNED_SHORT_5_6_5: Int = 0x8363

	/** Define "GL_RENDERBUFFER_WIDTH" with expression '`0x8D42`', CType: int  */
	const val GL_RENDERBUFFER_WIDTH: Int = 0x8d42

	/** Define "GL_DEPTH_BITS" with expression '`0x0D56`', CType: int  */
	const val GL_DEPTH_BITS: Int = 0xd56

	/** Define "GL_SRC_ALPHA_SATURATE" with expression '`0x0308`', CType: int  */
	const val GL_SRC_ALPHA_SATURATE: Int = 0x308

	/** Define "GL_UNPACK_ALIGNMENT" with expression '`0x0CF5`', CType: int  */
	const val GL_UNPACK_ALIGNMENT: Int = 0xcf5

	/** Define "GL_COMPILE_STATUS" with expression '`0x8B81`', CType: int  */
	const val GL_COMPILE_STATUS: Int = 0x8b81

	/** Define "GL_ALPHA" with expression '`0x1906`', CType: int  */
	const val GL_ALPHA: Int = 0x1906

	/** Define "GL_BYTE" with expression '`0x1400`', CType: int  */
	const val GL_BYTE: Int = 0x1400

	/** Define "GL_NEVER" with expression '`0x0200`', CType: int  */
	const val GL_NEVER: Int = 0x200

	/** Define "GL_DONT_CARE" with expression '`0x1100`', CType: int  */
	const val GL_DONT_CARE: Int = 0x1100

	/** Define "GL_MIRRORED_REPEAT" with expression '`0x8370`', CType: int  */
	const val GL_MIRRORED_REPEAT: Int = 0x8370

	/** Define "GL_FALSE" with expression '`0`', CType: int  */
	const val GL_FALSE: Int = 0x0

	/** Define "GL_GENERATE_MIPMAP_HINT" with expression '`0x8192`', CType: int  */
	const val GL_GENERATE_MIPMAP_HINT: Int = 0x8192

	/** Define "GL_RGB5_A1" with expression '`0x8057`', CType: int  */
	const val GL_RGB5_A1: Int = 0x8057

	/** Define "GL_SAMPLER_2D" with expression '`0x8B5E`', CType: int  */
	const val GL_SAMPLER_2D: Int = 0x8b5e

	/** Define "GL_STENCIL_BACK_REF" with expression '`0x8CA3`', CType: int  */
	const val GL_STENCIL_BACK_REF: Int = 0x8ca3

	/** Define "GL_BOOL" with expression '`0x8B56`', CType: int  */
	const val GL_BOOL: Int = 0x8b56

	/** Define "GL_EQUAL" with expression '`0x0202`', CType: int  */
	const val GL_EQUAL: Int = 0x202

	/** Define "GL_VERTEX_ATTRIB_ARRAY_POINTER" with expression '`0x8645`', CType: int  */
	const val GL_VERTEX_ATTRIB_ARRAY_POINTER: Int = 0x8645

	/** Define "GL_BUFFER_USAGE" with expression '`0x8765`', CType: int  */
	const val GL_BUFFER_USAGE: Int = 0x8765

	/** Define "GL_BACK" with expression '`0x0405`', CType: int  */
	const val GL_BACK: Int = 0x405

	/** Define "GL_TEXTURE_BINDING_CUBE_MAP" with expression '`0x8514`', CType: int  */
	const val GL_TEXTURE_BINDING_CUBE_MAP: Int = 0x8514

	/** Define "KHRONOS_MAX_ENUM" with expression '`0x7FFFFFFF`', CType: int  */
	const val KHRONOS_MAX_ENUM: Int = 0x7fffffff

	/** Define "GL_IMPLEMENTATION_COLOR_READ_TYPE" with expression '`0x8B9A`', CType: int  */
	const val GL_IMPLEMENTATION_COLOR_READ_TYPE: Int = 0x8b9a

	/** Define "GL_ONE_MINUS_SRC_ALPHA" with expression '`0x0303`', CType: int  */
	const val GL_ONE_MINUS_SRC_ALPHA: Int = 0x303

	/** Define "GL_SAMPLE_ALPHA_TO_COVERAGE" with expression '`0x809E`', CType: int  */
	const val GL_SAMPLE_ALPHA_TO_COVERAGE: Int = 0x809e

	/** Define "GL_ELEMENT_ARRAY_BUFFER_BINDING" with expression '`0x8895`', CType: int  */
	const val GL_ELEMENT_ARRAY_BUFFER_BINDING: Int = 0x8895

	/** Define "GL_INT" with expression '`0x1404`', CType: int  */
	const val GL_INT: Int = 0x1404

	/** Define "GL_VERTEX_ATTRIB_ARRAY_TYPE" with expression '`0x8625`', CType: int  */
	const val GL_VERTEX_ATTRIB_ARRAY_TYPE: Int = 0x8625

	/** Define "GL_FRAMEBUFFER_BINDING" with expression '`0x8CA6`', CType: int  */
	const val GL_FRAMEBUFFER_BINDING: Int = 0x8ca6

	/** Define "GL_FLOAT_VEC2" with expression '`0x8B50`', CType: int  */
	const val GL_FLOAT_VEC2: Int = 0x8b50

	/** Define "GL_MEDIUM_INT" with expression '`0x8DF4`', CType: int  */
	const val GL_MEDIUM_INT: Int = 0x8df4

	/** Define "GL_NUM_COMPRESSED_TEXTURE_FORMATS" with expression '`0x86A2`', CType: int  */
	const val GL_NUM_COMPRESSED_TEXTURE_FORMATS: Int = 0x86a2

	/** Define "GL_TRIANGLE_STRIP" with expression '`0x0005`', CType: int  */
	const val GL_TRIANGLE_STRIP: Int = 0x5

	/** Define "GL_FLOAT_VEC4" with expression '`0x8B52`', CType: int  */
	const val GL_FLOAT_VEC4: Int = 0x8b52

	/** Define "GL_FLOAT_VEC3" with expression '`0x8B51`', CType: int  */
	const val GL_FLOAT_VEC3: Int = 0x8b51

	/** Define "GL_LINE_LOOP" with expression '`0x0002`', CType: int  */
	const val GL_LINE_LOOP: Int = 0x2

	/** Define "GL_DEPTH_WRITEMASK" with expression '`0x0B72`', CType: int  */
	const val GL_DEPTH_WRITEMASK: Int = 0xb72

	/** Define "GL_MAX_VARYING_VECTORS" with expression '`0x8DFC`', CType: int  */
	const val GL_MAX_VARYING_VECTORS: Int = 0x8dfc

	/** Define "GL_SAMPLE_BUFFERS" with expression '`0x80A8`', CType: int  */
	const val GL_SAMPLE_BUFFERS: Int = 0x80a8

	/** Define "GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT" with expression '`0x8CD6`', CType: int  */
	const val GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT: Int = 0x8cd6

	/** Define "GL_FUNC_REVERSE_SUBTRACT" with expression '`0x800B`', CType: int  */
	const val GL_FUNC_REVERSE_SUBTRACT: Int = 0x800b

	/** Define "GL_STENCIL_BUFFER_BIT" with expression '`0x00000400`', CType: int  */
	const val GL_STENCIL_BUFFER_BIT: Int = 0x400

	/** Define "GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE" with expression '`0x8CD3`', CType: int  */
	const val GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE: Int = 0x8cd3

	/** Define "GL_SCISSOR_BOX" with expression '`0x0C10`', CType: int  */
	const val GL_SCISSOR_BOX: Int = 0xc10

	/** Define "GL_BUFFER_SIZE" with expression '`0x8764`', CType: int  */
	const val GL_BUFFER_SIZE: Int = 0x8764

	/** Define "GL_VALIDATE_STATUS" with expression '`0x8B83`', CType: int  */
	const val GL_VALIDATE_STATUS: Int = 0x8b83

	/** Define "GL_PACK_ALIGNMENT" with expression '`0x0D05`', CType: int  */
	const val GL_PACK_ALIGNMENT: Int = 0xd05

	/** Define "GL_REPEAT" with expression '`0x2901`', CType: int  */
	const val GL_REPEAT: Int = 0x2901

	/** Define "GL_TEXTURE_CUBE_MAP_POSITIVE_X" with expression '`0x8515`', CType: int  */
	const val GL_TEXTURE_CUBE_MAP_POSITIVE_X: Int = 0x8515

	/** Define "GL_RENDERER" with expression '`0x1F01`', CType: int  */
	const val GL_RENDERER: Int = 0x1f01

	/** Define "GL_TEXTURE_CUBE_MAP_POSITIVE_Y" with expression '`0x8517`', CType: int  */
	const val GL_TEXTURE_CUBE_MAP_POSITIVE_Y: Int = 0x8517

	/** Define "GL_TRIANGLE_FAN" with expression '`0x0006`', CType: int  */
	const val GL_TRIANGLE_FAN: Int = 0x6

	/** Define "GL_TEXTURE_CUBE_MAP_POSITIVE_Z" with expression '`0x8519`', CType: int  */
	const val GL_TEXTURE_CUBE_MAP_POSITIVE_Z: Int = 0x8519

	/** Define "GL_STENCIL_CLEAR_VALUE" with expression '`0x0B91`', CType: int  */
	const val GL_STENCIL_CLEAR_VALUE: Int = 0xb91

	/** Define "GL_RENDERBUFFER_DEPTH_SIZE" with expression '`0x8D54`', CType: int  */
	const val GL_RENDERBUFFER_DEPTH_SIZE: Int = 0x8d54

	/** Define "GL_RENDERBUFFER_ALPHA_SIZE" with expression '`0x8D53`', CType: int  */
	const val GL_RENDERBUFFER_ALPHA_SIZE: Int = 0x8d53

	/** Define "GL_FRONT_FACE" with expression '`0x0B46`', CType: int  */
	const val GL_FRONT_FACE: Int = 0xb46

	/** Define "GL_POLYGON_OFFSET_UNITS" with expression '`0x2A00`', CType: int  */
	const val GL_POLYGON_OFFSET_UNITS: Int = 0x2a00

	/** Define "GL_RGBA4" with expression '`0x8056`', CType: int  */
	const val GL_RGBA4: Int = 0x8056

	/** Define "GL_HIGH_INT" with expression '`0x8DF5`', CType: int  */
	const val GL_HIGH_INT: Int = 0x8df5

	/** Define "KHRONOS_SUPPORT_FLOAT" with expression '`1`', CType: int  */
	const val KHRONOS_SUPPORT_FLOAT: Int = 0x1

	/** Define "GL_TEXTURE0" with expression '`0x84C0`', CType: int  */
	const val GL_TEXTURE0: Int = 0x84c0

	/** Define "GL_STENCIL_INDEX8" with expression '`0x8D48`', CType: int  */
	const val GL_STENCIL_INDEX8: Int = 0x8d48

	/** Define "GL_ZERO" with expression '`0`', CType: int  */
	const val GL_ZERO: Int = 0x0

	/** Define "GL_STENCIL_TEST" with expression '`0x0B90`', CType: int  */
	const val GL_STENCIL_TEST: Int = 0xb90

	/** Define "GL_STREAM_DRAW" with expression '`0x88E0`', CType: int  */
	const val GL_STREAM_DRAW: Int = 0x88e0

	/** Define "GL_DECR" with expression '`0x1E03`', CType: int  */
	const val GL_DECR: Int = 0x1e03

	/** Define "GL_TEXTURE4" with expression '`0x84C4`', CType: int  */
	const val GL_TEXTURE4: Int = 0x84c4

	/** Define "GL_BOOL_VEC4" with expression '`0x8B59`', CType: int  */
	const val GL_BOOL_VEC4: Int = 0x8b59

	/** Define "GL_RENDERBUFFER_INTERNAL_FORMAT" with expression '`0x8D44`', CType: int  */
	const val GL_RENDERBUFFER_INTERNAL_FORMAT: Int = 0x8d44

	/** Define "GL_TEXTURE3" with expression '`0x84C3`', CType: int  */
	const val GL_TEXTURE3: Int = 0x84c3

	/** Define "GL_BOOL_VEC3" with expression '`0x8B58`', CType: int  */
	const val GL_BOOL_VEC3: Int = 0x8b58

	/** Define "GL_TEXTURE2" with expression '`0x84C2`', CType: int  */
	const val GL_TEXTURE2: Int = 0x84c2

	/** Define "GL_BOOL_VEC2" with expression '`0x8B57`', CType: int  */
	const val GL_BOOL_VEC2: Int = 0x8b57

	/** Define "GL_TEXTURE1" with expression '`0x84C1`', CType: int  */
	const val GL_TEXTURE1: Int = 0x84c1

	/** Define "GL_TEXTURE8" with expression '`0x84C8`', CType: int  */
	const val GL_TEXTURE8: Int = 0x84c8

	/** Define "GL_TEXTURE7" with expression '`0x84C7`', CType: int  */
	const val GL_TEXTURE7: Int = 0x84c7

	/** Define "GL_STATIC_DRAW" with expression '`0x88E4`', CType: int  */
	const val GL_STATIC_DRAW: Int = 0x88e4

	/** Define "GL_TEXTURE6" with expression '`0x84C6`', CType: int  */
	const val GL_TEXTURE6: Int = 0x84c6

	/** Define "GL_TEXTURE5" with expression '`0x84C5`', CType: int  */
	const val GL_TEXTURE5: Int = 0x84c5

	/** Define "GL_FUNC_ADD" with expression '`0x8006`', CType: int  */
	const val GL_FUNC_ADD: Int = 0x8006

	/** Define "GL_UNSIGNED_SHORT" with expression '`0x1403`', CType: int  */
	const val GL_UNSIGNED_SHORT: Int = 0x1403

	/** Define "GL_TEXTURE9" with expression '`0x84C9`', CType: int  */
	const val GL_TEXTURE9: Int = 0x84c9

	/** Define "GL_POINTS" with expression '`0x0000`', CType: int  */
	const val GL_POINTS: Int = 0x0

	/** Define "GL_CLAMP_TO_EDGE" with expression '`0x812F`', CType: int  */
	const val GL_CLAMP_TO_EDGE: Int = 0x812f

	/** Define "GL_LINES" with expression '`0x0001`', CType: int  */
	const val GL_LINES: Int = 0x1

	/** Define "GL_TEXTURE20" with expression '`0x84D4`', CType: int  */
	const val GL_TEXTURE20: Int = 0x84d4

	/** Define "KHRONOS_SUPPORT_INT64" with expression '`1`', CType: int  */
	const val KHRONOS_SUPPORT_INT64: Int = 0x1

	/** Define "GL_MAX_VIEWPORT_DIMS" with expression '`0x0D3A`', CType: int  */
	const val GL_MAX_VIEWPORT_DIMS: Int = 0xd3a

	/** Define "GL_DECR_WRAP" with expression '`0x8508`', CType: int  */
	const val GL_DECR_WRAP: Int = 0x8508

	/** Define "GL_COLOR_ATTACHMENT0" with expression '`0x8CE0`', CType: int  */
	const val GL_COLOR_ATTACHMENT0: Int = 0x8ce0

	/** Define "GL_SHADER_TYPE" with expression '`0x8B4F`', CType: int  */
	const val GL_SHADER_TYPE: Int = 0x8b4f

	/** Define "GL_ALPHA_BITS" with expression '`0x0D55`', CType: int  */
	const val GL_ALPHA_BITS: Int = 0xd55

	/** Define "GL_FLOAT_MAT4" with expression '`0x8B5C`', CType: int  */
	const val GL_FLOAT_MAT4: Int = 0x8b5c

	/** Define "GL_FLOAT_MAT3" with expression '`0x8B5B`', CType: int  */
	const val GL_FLOAT_MAT3: Int = 0x8b5b

	/** Define "GL_ONE_MINUS_CONSTANT_COLOR" with expression '`0x8002`', CType: int  */
	const val GL_ONE_MINUS_CONSTANT_COLOR: Int = 0x8002

	/** Define "GL_FLOAT_MAT2" with expression '`0x8B5A`', CType: int  */
	const val GL_FLOAT_MAT2: Int = 0x8b5a

	/** Define "GL_BLEND_DST_ALPHA" with expression '`0x80CA`', CType: int  */
	const val GL_BLEND_DST_ALPHA: Int = 0x80ca

	/** Define "GL_TEXTURE19" with expression '`0x84D3`', CType: int  */
	const val GL_TEXTURE19: Int = 0x84d3

	/** Define "GL_ACTIVE_ATTRIBUTES" with expression '`0x8B89`', CType: int  */
	const val GL_ACTIVE_ATTRIBUTES: Int = 0x8b89

	/** Define "GL_TEXTURE18" with expression '`0x84D2`', CType: int  */
	const val GL_TEXTURE18: Int = 0x84d2

	/** Define "GL_TEXTURE17" with expression '`0x84D1`', CType: int  */
	const val GL_TEXTURE17: Int = 0x84d1

	/** Define "GL_TEXTURE16" with expression '`0x84D0`', CType: int  */
	const val GL_TEXTURE16: Int = 0x84d0

	/** Define "GL_TEXTURE15" with expression '`0x84CF`', CType: int  */
	const val GL_TEXTURE15: Int = 0x84cf

	/** Define "GL_TEXTURE14" with expression '`0x84CE`', CType: int  */
	const val GL_TEXTURE14: Int = 0x84ce

	/** Define "GL_MAX_TEXTURE_IMAGE_UNITS" with expression '`0x8872`', CType: int  */
	const val GL_MAX_TEXTURE_IMAGE_UNITS: Int = 0x8872

	/** Define "GL_TEXTURE13" with expression '`0x84CD`', CType: int  */
	const val GL_TEXTURE13: Int = 0x84cd

	/** Define "GL_GLES_PROTOTYPES" with expression '`1`', CType: int  */
	const val GL_GLES_PROTOTYPES: Int = 0x1

	/** Define "GL_TEXTURE12" with expression '`0x84CC`', CType: int  */
	const val GL_TEXTURE12: Int = 0x84cc

	/** Define "GL_BLEND_SRC_ALPHA" with expression '`0x80CB`', CType: int  */
	const val GL_BLEND_SRC_ALPHA: Int = 0x80cb

	/** Define "GL_TEXTURE11" with expression '`0x84CB`', CType: int  */
	const val GL_TEXTURE11: Int = 0x84cb

	/** Define "GL_MAX_VERTEX_ATTRIBS" with expression '`0x8869`', CType: int  */
	const val GL_MAX_VERTEX_ATTRIBS: Int = 0x8869

	/** Define "GL_TEXTURE10" with expression '`0x84CA`', CType: int  */
	const val GL_TEXTURE10: Int = 0x84ca

	/** Define "GL_LINEAR_MIPMAP_LINEAR" with expression '`0x2703`', CType: int  */
	const val GL_LINEAR_MIPMAP_LINEAR: Int = 0x2703

	/** Define "GL_TEXTURE31" with expression '`0x84DF`', CType: int  */
	const val GL_TEXTURE31: Int = 0x84df

	/** Define "GL_COLOR_CLEAR_VALUE" with expression '`0x0C22`', CType: int  */
	const val GL_COLOR_CLEAR_VALUE: Int = 0xc22

	/** Define "GL_TEXTURE30" with expression '`0x84DE`', CType: int  */
	const val GL_TEXTURE30: Int = 0x84de

	/** Define "GL_BLEND_EQUATION_RGB" with expression '`0x8009`', CType: int  */
	const val GL_BLEND_EQUATION_RGB: Int = 0x8009

	/** Define "GL_TEXTURE_CUBE_MAP_NEGATIVE_X" with expression '`0x8516`', CType: int  */
	const val GL_TEXTURE_CUBE_MAP_NEGATIVE_X: Int = 0x8516

	/** Define "GL_TEXTURE_CUBE_MAP_NEGATIVE_Z" with expression '`0x851A`', CType: int  */
	const val GL_TEXTURE_CUBE_MAP_NEGATIVE_Z: Int = 0x851a

	/** Define "GL_INCR_WRAP" with expression '`0x8507`', CType: int  */
	const val GL_INCR_WRAP: Int = 0x8507

	/** Define "GL_TEXTURE_CUBE_MAP_NEGATIVE_Y" with expression '`0x8518`', CType: int  */
	const val GL_TEXTURE_CUBE_MAP_NEGATIVE_Y: Int = 0x8518

	/** Define "GL_UNSIGNED_SHORT_4_4_4_4" with expression '`0x8033`', CType: int  */
	const val GL_UNSIGNED_SHORT_4_4_4_4: Int = 0x8033

	/** Define "GL_VERTEX_ATTRIB_ARRAY_STRIDE" with expression '`0x8624`', CType: int  */
	const val GL_VERTEX_ATTRIB_ARRAY_STRIDE: Int = 0x8624

	/** Define "GL_NEAREST_MIPMAP_NEAREST" with expression '`0x2700`', CType: int  */
	const val GL_NEAREST_MIPMAP_NEAREST: Int = 0x2700

	/** Define "GL_CONSTANT_ALPHA" with expression '`0x8003`', CType: int  */
	const val GL_CONSTANT_ALPHA: Int = 0x8003

	/** Define "GL_STENCIL_PASS_DEPTH_FAIL" with expression '`0x0B95`', CType: int  */
	const val GL_STENCIL_PASS_DEPTH_FAIL: Int = 0xb95

	/** Define "GL_SAMPLE_COVERAGE_INVERT" with expression '`0x80AB`', CType: int  */
	const val GL_SAMPLE_COVERAGE_INVERT: Int = 0x80ab

	/** Define "GL_TEXTURE29" with expression '`0x84DD`', CType: int  */
	const val GL_TEXTURE29: Int = 0x84dd

	/** Define "GL_ONE_MINUS_CONSTANT_ALPHA" with expression '`0x8004`', CType: int  */
	const val GL_ONE_MINUS_CONSTANT_ALPHA: Int = 0x8004

	/** Define "GL_TEXTURE28" with expression '`0x84DC`', CType: int  */
	const val GL_TEXTURE28: Int = 0x84dc

	/** Define "GL_TEXTURE27" with expression '`0x84DB`', CType: int  */
	const val GL_TEXTURE27: Int = 0x84db

	/** Define "GL_TEXTURE26" with expression '`0x84DA`', CType: int  */
	const val GL_TEXTURE26: Int = 0x84da

	/** Define "GL_TEXTURE25" with expression '`0x84D9`', CType: int  */
	const val GL_TEXTURE25: Int = 0x84d9

	/** Define "GL_CURRENT_PROGRAM" with expression '`0x8B8D`', CType: int  */
	const val GL_CURRENT_PROGRAM: Int = 0x8b8d

	/** Define "GL_TEXTURE24" with expression '`0x84D8`', CType: int  */
	const val GL_TEXTURE24: Int = 0x84d8

	/** Define "GL_INT_VEC4" with expression '`0x8B55`', CType: int  */
	const val GL_INT_VEC4: Int = 0x8b55

	/** Define "GL_TEXTURE23" with expression '`0x84D7`', CType: int  */
	const val GL_TEXTURE23: Int = 0x84d7

	/** Define "GL_TEXTURE22" with expression '`0x84D6`', CType: int  */
	const val GL_TEXTURE22: Int = 0x84d6

	/** Define "GL_INT_VEC2" with expression '`0x8B53`', CType: int  */
	const val GL_INT_VEC2: Int = 0x8b53

	/** Define "GL_TEXTURE21" with expression '`0x84D5`', CType: int  */
	const val GL_TEXTURE21: Int = 0x84d5

	/** Define "GL_INT_VEC3" with expression '`0x8B54`', CType: int  */
	const val GL_INT_VEC3: Int = 0x8b54

	/** Define "GL_DEPTH_RANGE" with expression '`0x0B70`', CType: int  */
	const val GL_DEPTH_RANGE: Int = 0xb70

	/** Define "GL_BLUE_BITS" with expression '`0x0D54`', CType: int  */
	const val GL_BLUE_BITS: Int = 0xd54

	/** Define "GL_FLOAT" with expression '`0x1406`', CType: int  */
	const val GL_FLOAT: Int = 0x1406

	/** Define "GL_BLEND_EQUATION" with expression '`0x8009`', CType: int  */
	const val GL_BLEND_EQUATION: Int = 0x8009

	/** Define "GL_RENDERBUFFER_HEIGHT" with expression '`0x8D43`', CType: int  */
	const val GL_RENDERBUFFER_HEIGHT: Int = 0x8d43

	/** Define "GL_INCR" with expression '`0x1E02`', CType: int  */
	const val GL_INCR: Int = 0x1e02

	/** Define "GL_POLYGON_OFFSET_FILL" with expression '`0x8037`', CType: int  */
	const val GL_POLYGON_OFFSET_FILL: Int = 0x8037

	/** Define "GL_STENCIL_ATTACHMENT" with expression '`0x8D20`', CType: int  */
	const val GL_STENCIL_ATTACHMENT: Int = 0x8d20

	/** Define "GL_INVALID_VALUE" with expression '`0x0501`', CType: int  */
	const val GL_INVALID_VALUE: Int = 0x501

	/** Define "GL_FUNC_SUBTRACT" with expression '`0x800A`', CType: int  */
	const val GL_FUNC_SUBTRACT: Int = 0x800a

	/** Define "GL_FRAMEBUFFER_UNSUPPORTED" with expression '`0x8CDD`', CType: int  */
	const val GL_FRAMEBUFFER_UNSUPPORTED: Int = 0x8cdd

	/** Define "GL_GEQUAL" with expression '`0x0206`', CType: int  */
	const val GL_GEQUAL: Int = 0x206

	/** Define "GL_TEXTURE_MIN_FILTER" with expression '`0x2801`', CType: int  */
	const val GL_TEXTURE_MIN_FILTER: Int = 0x2801

	/** Define "GL_ES_VERSION_2_0" with expression '`1`', CType: int  */
	const val GL_ES_VERSION_2_0: Int = 0x1

	/** Define "GL_NICEST" with expression '`0x1102`', CType: int  */
	const val GL_NICEST: Int = 0x1102

	/** Define "GL_STENCIL_FUNC" with expression '`0x0B92`', CType: int  */
	const val GL_STENCIL_FUNC: Int = 0xb92

	/** Define "GL_UNSIGNED_BYTE" with expression '`0x1401`', CType: int  */
	const val GL_UNSIGNED_BYTE: Int = 0x1401

	/** Define "GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE" with expression '`0x8CD0`', CType: int  */
	const val GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE: Int = 0x8cd0

	/** Define "GL_SHADING_LANGUAGE_VERSION" with expression '`0x8B8C`', CType: int  */
	const val GL_SHADING_LANGUAGE_VERSION: Int = 0x8b8c

	/** Define "GL_VERSION" with expression '`0x1F02`', CType: int  */
	const val GL_VERSION: Int = 0x1f02

	/** Define "GL_CONSTANT_COLOR" with expression '`0x8001`', CType: int  */
	const val GL_CONSTANT_COLOR: Int = 0x8001

	/** Define "GL_IMPLEMENTATION_COLOR_READ_FORMAT" with expression '`0x8B9B`', CType: int  */
	const val GL_IMPLEMENTATION_COLOR_READ_FORMAT: Int = 0x8b9b

	/** Define "GL_TEXTURE_MAG_FILTER" with expression '`0x2800`', CType: int  */
	const val GL_TEXTURE_MAG_FILTER: Int = 0x2800

	/** Define "GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING" with expression '`0x889F`', CType: int  */
	const val GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING: Int = 0x889f

	/** Define "GL_DST_ALPHA" with expression '`0x0304`', CType: int  */
	const val GL_DST_ALPHA: Int = 0x304

	/** Define "GL_STENCIL_BACK_PASS_DEPTH_FAIL" with expression '`0x8802`', CType: int  */
	const val GL_STENCIL_BACK_PASS_DEPTH_FAIL: Int = 0x8802

	/** Define "GL_LINK_STATUS" with expression '`0x8B82`', CType: int  */
	const val GL_LINK_STATUS: Int = 0x8b82

	/** Define "GL_COMPRESSED_TEXTURE_FORMATS" with expression '`0x86A3`', CType: int  */
	const val GL_COMPRESSED_TEXTURE_FORMATS: Int = 0x86a3

	/** Define "GL_ATTACHED_SHADERS" with expression '`0x8B85`', CType: int  */
	const val GL_ATTACHED_SHADERS: Int = 0x8b85

	/** Define "GL_RENDERBUFFER_BLUE_SIZE" with expression '`0x8D52`', CType: int  */
	const val GL_RENDERBUFFER_BLUE_SIZE: Int = 0x8d52

	/** Define "GL_LOW_INT" with expression '`0x8DF3`', CType: int  */
	const val GL_LOW_INT: Int = 0x8df3

	/** Define "__gles2_gl2_h_" with expression '`1`', CType: int  */
	const val __gles2_gl2_h_: Int = 0x1

	/** Define "GL_DITHER" with expression '`0x0BD0`', CType: int  */
	const val GL_DITHER: Int = 0xbd0

	/** Define "GL_RGB565" with expression '`0x8D62`', CType: int  */
	const val GL_RGB565: Int = 0x8d62

	/** Define "GL_VERTEX_ATTRIB_ARRAY_SIZE" with expression '`0x8623`', CType: int  */
	const val GL_VERTEX_ATTRIB_ARRAY_SIZE: Int = 0x8623

	/** Define "GL_SAMPLES" with expression '`0x80A9`', CType: int  */
	const val GL_SAMPLES: Int = 0x80a9

	/** Define "GL_SRC_COLOR" with expression '`0x0300`', CType: int  */
	const val GL_SRC_COLOR: Int = 0x300

	/** Define "GL_LINE_WIDTH" with expression '`0x0B21`', CType: int  */
	const val GL_LINE_WIDTH: Int = 0xb21

	/** Define "GL_STENCIL_FAIL" with expression '`0x0B94`', CType: int  */
	const val GL_STENCIL_FAIL: Int = 0xb94

	/** Define "GL_STENCIL_WRITEMASK" with expression '`0x0B98`', CType: int  */
	const val GL_STENCIL_WRITEMASK: Int = 0xb98

	/** Define "GL_FRONT" with expression '`0x0404`', CType: int  */
	const val GL_FRONT: Int = 0x404

	/** Define "GL_TEXTURE_BINDING_2D" with expression '`0x8069`', CType: int  */
	const val GL_TEXTURE_BINDING_2D: Int = 0x8069

	/** Define "GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS" with expression '`0x8B4D`', CType: int  */
	const val GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS: Int = 0x8b4d

	/** Define "GL_NOTEQUAL" with expression '`0x0205`', CType: int  */
	const val GL_NOTEQUAL: Int = 0x205

	/** Define "GL_SUBPIXEL_BITS" with expression '`0x0D50`', CType: int  */
	const val GL_SUBPIXEL_BITS: Int = 0xd50

	/** Define "GL_RGB" with expression '`0x1907`', CType: int  */
	const val GL_RGB: Int = 0x1907

	/** Define "GL_VERTEX_ATTRIB_ARRAY_ENABLED" with expression '`0x8622`', CType: int  */
	const val GL_VERTEX_ATTRIB_ARRAY_ENABLED: Int = 0x8622

	/** Define "GL_SHADER_SOURCE_LENGTH" with expression '`0x8B88`', CType: int  */
	const val GL_SHADER_SOURCE_LENGTH: Int = 0x8b88

	/** Define "GL_STENCIL_BITS" with expression '`0x0D57`', CType: int  */
	const val GL_STENCIL_BITS: Int = 0xd57

	/** Define "GL_CULL_FACE_MODE" with expression '`0x0B45`', CType: int  */
	const val GL_CULL_FACE_MODE: Int = 0xb45

	/** Define "GL_RENDERBUFFER_GREEN_SIZE" with expression '`0x8D51`', CType: int  */
	const val GL_RENDERBUFFER_GREEN_SIZE: Int = 0x8d51

	/** Define "GL_UNSIGNED_SHORT_5_5_5_1" with expression '`0x8034`', CType: int  */
	const val GL_UNSIGNED_SHORT_5_5_5_1: Int = 0x8034

	/** Define "GL_SAMPLER_CUBE" with expression '`0x8B60`', CType: int  */
	const val GL_SAMPLER_CUBE: Int = 0x8b60

	/** Define "GL_FASTEST" with expression '`0x1101`', CType: int  */
	const val GL_FASTEST: Int = 0x1101

	/** Define "GL_BLEND_DST_RGB" with expression '`0x80C8`', CType: int  */
	const val GL_BLEND_DST_RGB: Int = 0x80c8

	/** Define "GL_VERTEX_ATTRIB_ARRAY_NORMALIZED" with expression '`0x886A`', CType: int  */
	const val GL_VERTEX_ATTRIB_ARRAY_NORMALIZED: Int = 0x886a

	/** Define "GL_BLEND" with expression '`0x0BE2`', CType: int  */
	const val GL_BLEND: Int = 0xbe2

	/** Define "GL_SRC_ALPHA" with expression '`0x0302`', CType: int  */
	const val GL_SRC_ALPHA: Int = 0x302

	/** Define "GL_DEPTH_COMPONENT16" with expression '`0x81A5`', CType: int  */
	const val GL_DEPTH_COMPONENT16: Int = 0x81a5

	/** Interface to C language function: <br></br> `void glActiveTexture(GLenum texture)`<br></br>    */
	external override fun glActiveTexture(texture: Int)

	/** Interface to C language function: <br></br> `void glAttachShader(GLuint program, GLuint shader)`<br></br>    */
	external override fun glAttachShader(program: Int, shader: Int)

	/** Interface to C language function: <br></br> `void glBindAttribLocation(GLuint program, GLuint index, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glBindAttribLocation(program: Int, index: Int, name: ByteBuffer?) {
		val name_is_direct: Boolean = Buffers.isDirect(name)
		glBindAttribLocation1(
			program,
			index,
			if (name_is_direct) name else Buffers.getArray(name),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name) else Buffers.getIndirectBufferByteOffset(
				name
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `void glBi`ndAttribLocation(GLuint program, GLuint index, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glBindAttribLocation1(
		program: Int,
		index: Int,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glBindAttribLocation(GLuint program, GLuint index, const GLchar *  name)`<br></br>    */
	override fun glBindAttribLocation(program: Int, index: Int, name: ByteArray?, name_offset: Int) {
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		glBindAttribLocation1(program, index, name, name_offset, false)
	}

	/** Interface to C language function: <br></br> `void glBindBuffer(GLenum target, GLuint buffer)`<br></br>    */
	external override fun glBindBuffer(target: Int, buffer: Int)

	/** Interface to C language function: <br></br> `void glBindFramebuffer(GLenum target, GLuint framebuffer)`<br></br>    */
	external override fun glBindFramebuffer(target: Int, framebuffer: Int)

	/** Interface to C language function: <br></br> `void glBindRenderbuffer(GLenum target, GLuint renderbuffer)`<br></br>    */
	external override fun glBindRenderbuffer(target: Int, renderbuffer: Int)

	/** Interface to C language function: <br></br> `void glBindTexture(GLenum target, GLuint texture)`<br></br>    */
	external override fun glBindTexture(target: Int, texture: Int)

	/** Interface to C language function: <br></br> `void glBlendColor(GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha)`<br></br>    */
	external override fun glBlendColor(red: Float, green: Float, blue: Float, alpha: Float)

	/** Interface to C language function: <br></br> `void glBlendEquation(GLenum mode)`<br></br>    */
	external override fun glBlendEquation(mode: Int)

	/** Interface to C language function: <br></br> `void glBlendEquationSeparate(GLenum modeRGB, GLenum modeAlpha)`<br></br>    */
	external override fun glBlendEquationSeparate(modeRGB: Int, modeAlpha: Int)

	/** Interface to C language function: <br></br> `void glBlendFunc(GLenum sfactor, GLenum dfactor)`<br></br>    */
	external override fun glBlendFunc(sfactor: Int, dfactor: Int)

	/** Interface to C language function: <br></br> `void glBlendFuncSeparate(GLenum sfactorRGB, GLenum dfactorRGB, GLenum sfactorAlpha, GLenum dfactorAlpha)`<br></br>    */
	external override fun glBlendFuncSeparate(
		sfactorRGB: Int,
		dfactorRGB: Int,
		sfactorAlpha: Int,
		dfactorAlpha: Int
	)

	/** Interface to C language function: <br></br> `void glBufferData(GLenum target, GLsizeiptr size, const void *  data, GLenum usage)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	override fun glBufferData(target: Int, size: Int, data: Buffer, usage: Int) {
		val data_is_direct: Boolean = Buffers.isDirect(data)
		glBufferData1(
			target,
			size,
			if (data_is_direct) data else Buffers.getArray(data),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data) else Buffers.getIndirectBufferByteOffset(
				data
			),
			data_is_direct,
			usage
		)
	}

	/** Entry point to C language function: `void glBufferData(GLenum target, GLsizeiptr size, const void *  data, GLenum usage)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glBufferData1(
		target: Int,
		size: Int,
		data: Any,
		data_byte_offset: Int,
		data_is_direct: Boolean,
		usage: Int
	)

	/** Interface to C language function: <br></br> `void glBufferSubData(GLenum target, GLintptr offset, GLsizeiptr size, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	override fun glBufferSubData(target: Int, offset: Int, size: Int, data: Buffer) {
		val data_is_direct: Boolean = Buffers.isDirect(data)
		glBufferSubData1(
			target,
			offset,
			size,
			if (data_is_direct) data else Buffers.getArray(data),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data) else Buffers.getIndirectBufferByteOffset(
				data
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glBufferSubData(GLenum target, GLintptr offset, GLsizeiptr size, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glBufferSubData1(
		target: Int,
		offset: Int,
		size: Int,
		data: Any,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `GLenum glCheckFramebufferStatus(GLenum target)`<br></br>    */
	external override fun glCheckFramebufferStatus(target: Int): Int

	/** Interface to C language function: <br></br> `void glClear(GLbitfield mask)`<br></br>    */
	external override fun glClear(mask: Int)

	/** Interface to C language function: <br></br> `void glClearColor(GLfloat red, GLfloat green, GLfloat blue, GLfloat alpha)`<br></br>    */
	external override fun glClearColor(red: Float, green: Float, blue: Float, alpha: Float)

	/** Interface to C language function: <br></br> `void glClearDepthf(GLfloat d)`<br></br>    */
	external override fun glClearDepthf(d: Float)

	/** Interface to C language function: <br></br> `void glClearStencil(GLint s)`<br></br>    */
	external override fun glClearStencil(s: Int)

	/** Interface to C language function: <br></br> `void glColorMask(GLboolean red, GLboolean green, GLboolean blue, GLboolean alpha)`<br></br>    */
	external override fun glColorMask(red: Byte, green: Byte, blue: Byte, alpha: Byte)

	/** Interface to C language function: <br></br> `void glCompileShader(GLuint shader)`<br></br>    */
	external override fun glCompileShader(shader: Int)

	/** Interface to C language function: <br></br> `void glCompressedTexImage2D(GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	override fun glCompressedTexImage2D(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		imageSize: Int,
		data: Buffer
	) {
		val data_is_direct: Boolean = Buffers.isDirect(data)
		glCompressedTexImage2D1(
			target,
			level,
			internalformat,
			width,
			height,
			border,
			imageSize,
			if (data_is_direct) data else Buffers.getArray(data),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data) else Buffers.getIndirectBufferByteOffset(
				data
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glCompressedTexImage2D(GLenum target, GLint level, GLenum internalformat, GLsizei width, GLsizei height, GLint border, GLsizei imageSize, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glCompressedTexImage2D1(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		imageSize: Int,
		data: Any,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glCompressedTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	override fun glCompressedTexSubImage2D(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		imageSize: Int,
		data: Buffer
	) {
		val data_is_direct: Boolean = Buffers.isDirect(data)
		glCompressedTexSubImage2D1(
			target,
			level,
			xoffset,
			yoffset,
			width,
			height,
			format,
			imageSize,
			if (data_is_direct) data else Buffers.getArray(data),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data) else Buffers.getIndirectBufferByteOffset(
				data
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glCompressedTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLsizei imageSize, const void *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glCompressedTexSubImage2D1(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		imageSize: Int,
		data: Any,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glCopyTexImage2D(GLenum target, GLint level, GLenum internalformat, GLint x, GLint y, GLsizei width, GLsizei height, GLint border)`<br></br>    */
	external override fun glCopyTexImage2D(
		target: Int,
		level: Int,
		internalformat: Int,
		x: Int,
		y: Int,
		width: Int,
		height: Int,
		border: Int
	)

	/** Interface to C language function: <br></br> `void glCopyTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLint x, GLint y, GLsizei width, GLsizei height)`<br></br>    */
	external override fun glCopyTexSubImage2D(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		x: Int,
		y: Int,
		width: Int,
		height: Int
	)

	/** Interface to C language function: <br></br> `GLuint glCreateProgram()`<br></br>    */
	external override fun glCreateProgram(): Int

	/** Interface to C language function: <br></br> `GLuint glCreateShader(GLenum type)`<br></br>    */
	external override fun glCreateShader(type: Int): Int

	/** Interface to C language function: <br></br> `void glCullFace(GLenum mode)`<br></br>    */
	external override fun glCullFace(mode: Int)

	/** Interface to C language function: <br></br> `void glDeleteBuffers(GLsizei n, const GLuint *  buffers)`<br></br>
	 * @param buffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glDeleteBuffers(n: Int, buffers: IntBuffer?) {
		val buffers_is_direct: Boolean = Buffers.isDirect(buffers)
		glDeleteBuffers1(
			n,
			if (buffers_is_direct) buffers else Buffers.getArray(buffers),
			if (buffers_is_direct) Buffers.getDirectBufferByteOffset(buffers) else Buffers.getIndirectBufferByteOffset(
				buffers
			),
			buffers_is_direct
		)
	}

	/** Entry point to C language function: `void glDeleteBuffers(GLsizei n, const GLuint *  buffers)`<br></br>
	 * @param buffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glDeleteBuffers1(
		n: Int,
		buffers: Any?,
		buffers_byte_offset: Int,
		buffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glDeleteBuffers(GLsizei n, const GLuint *  buffers)`<br></br>    */
	override fun glDeleteBuffers(n: Int, buffers: IntArray?, buffers_offset: Int) {
		if (buffers != null && buffers.size <= buffers_offset) throw RuntimeException("array offset argument \"buffers_offset\" (" + buffers_offset + ") equals or exceeds array length (" + buffers.size + ")")
		glDeleteBuffers1(n, buffers, Buffers.SIZEOF_INT * buffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glDeleteFramebuffers(GLsizei n, const GLuint *  framebuffers)`<br></br>
	 * @param framebuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glDeleteFramebuffers(n: Int, framebuffers: IntBuffer?) {
		val framebuffers_is_direct: Boolean = Buffers.isDirect(framebuffers)
		glDeleteFramebuffers1(
			n,
			if (framebuffers_is_direct) framebuffers else Buffers.getArray(framebuffers),
			if (framebuffers_is_direct) Buffers.getDirectBufferByteOffset(framebuffers) else Buffers.getIndirectBufferByteOffset(
				framebuffers
			),
			framebuffers_is_direct
		)
	}

	/** Entry point to C language function: `void glDeleteFramebuffers(GLsizei n, const GLuint *  framebuffers)`<br></br>
	 * @param framebuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glDeleteFramebuffers1(
		n: Int,
		framebuffers: Any?,
		framebuffers_byte_offset: Int,
		framebuffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glDeleteFramebuffers(GLsizei n, const GLuint *  framebuffers)`<br></br>    */
	override fun glDeleteFramebuffers(n: Int, framebuffers: IntArray?, framebuffers_offset: Int) {
		if (framebuffers != null && framebuffers.size <= framebuffers_offset) throw RuntimeException(
			"array offset argument \"framebuffers_offset\" (" + framebuffers_offset + ") equals or exceeds array length (" + framebuffers.size + ")"
		)
		glDeleteFramebuffers1(n, framebuffers, Buffers.SIZEOF_INT * framebuffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glDeleteProgram(GLuint program)`<br></br>    */
	external override fun glDeleteProgram(program: Int)

	/** Interface to C language function: <br></br> `void glDeleteRenderbuffers(GLsizei n, const GLuint *  renderbuffers)`<br></br>
	 * @param renderbuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glDeleteRenderbuffers(n: Int, renderbuffers: IntBuffer?) {
		val renderbuffers_is_direct: Boolean = Buffers.isDirect(renderbuffers)
		glDeleteRenderbuffers1(
			n,
			if (renderbuffers_is_direct) renderbuffers else Buffers.getArray(renderbuffers),
			if (renderbuffers_is_direct) Buffers.getDirectBufferByteOffset(renderbuffers) else Buffers.getIndirectBufferByteOffset(
				renderbuffers
			),
			renderbuffers_is_direct
		)
	}

	/** Entry point to C language function: `void glDeleteRenderbuffers(GLsizei n, const GLuint *  renderbuffers)`<br></br>
	 * @param renderbuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glDeleteRenderbuffers1(
		n: Int,
		renderbuffers: Any?,
		renderbuffers_byte_offset: Int,
		renderbuffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glDeleteRenderbuffers(GLsizei n, const GLuint *  renderbuffers)`<br></br>    */
	override fun glDeleteRenderbuffers(n: Int, renderbuffers: IntArray?, renderbuffers_offset: Int) {
		if (renderbuffers != null && renderbuffers.size <= renderbuffers_offset) throw RuntimeException(
			"array offset argument \"renderbuffers_offset\" (" + renderbuffers_offset + ") equals or exceeds array length (" + renderbuffers.size + ")"
		)
		glDeleteRenderbuffers1(n, renderbuffers, Buffers.SIZEOF_INT * renderbuffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glDeleteShader(GLuint shader)`<br></br>    */
	external override fun glDeleteShader(shader: Int)

	/** Interface to C language function: <br></br> `void glDeleteTextures(GLsizei n, const GLuint *  textures)`<br></br>
	 * @param textures a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glDeleteTextures(n: Int, textures: IntBuffer?) {
		val textures_is_direct: Boolean = Buffers.isDirect(textures)
		glDeleteTextures1(
			n,
			if (textures_is_direct) textures else Buffers.getArray(textures),
			if (textures_is_direct) Buffers.getDirectBufferByteOffset(textures) else Buffers.getIndirectBufferByteOffset(
				textures
			),
			textures_is_direct
		)
	}

	/** Entry point to C language function: `void glDeleteTextures(GLsizei n, const GLuint *  textures)`<br></br>
	 * @param textures a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glDeleteTextures1(
		n: Int,
		textures: Any?,
		textures_byte_offset: Int,
		textures_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glDeleteTextures(GLsizei n, const GLuint *  textures)`<br></br>    */
	override fun glDeleteTextures(n: Int, textures: IntArray?, textures_offset: Int) {
		if (textures != null && textures.size <= textures_offset) throw RuntimeException("array offset argument \"textures_offset\" (" + textures_offset + ") equals or exceeds array length (" + textures.size + ")")
		glDeleteTextures1(n, textures, Buffers.SIZEOF_INT * textures_offset, false)
	}

	/** Interface to C language function: <br></br> `void glDepthFunc(GLenum func)`<br></br>    */
	external override fun glDepthFunc(func: Int)

	/** Interface to C language function: <br></br> `void glDepthMask(GLboolean flag)`<br></br>    */
	external override fun glDepthMask(flag: Byte)

	/** Interface to C language function: <br></br> `void glDepthRangef(GLfloat n, GLfloat f)`<br></br>    */
	external override fun glDepthRangef(n: Float, f: Float)

	/** Interface to C language function: <br></br> `void glDetachShader(GLuint program, GLuint shader)`<br></br>    */
	external override fun glDetachShader(program: Int, shader: Int)

	/** Interface to C language function: <br></br> `void glDisable(GLenum cap)`<br></br>    */
	external override fun glDisable(cap: Int)

	/** Interface to C language function: <br></br> `void glDisableVertexAttribArray(GLuint index)`<br></br>    */
	external override fun glDisableVertexAttribArray(index: Int)

	/** Interface to C language function: <br></br> `void glDrawArrays(GLenum mode, GLint first, GLsizei count)`<br></br>    */
	external override fun glDrawArrays(mode: Int, first: Int, count: Int)

	/** Interface to C language function: <br></br> `void glDrawElements(GLenum mode, GLsizei count, GLenum type, const void *  indices)`<br></br>
	 * @param indices a direct or array-backed [java.nio.Buffer]
	 */
	override fun glDrawElements(mode: Int, count: Int, type: Int, indices: Buffer) {
		val indices_is_direct: Boolean = Buffers.isDirect(indices)
		glDrawElements1(
			mode,
			count,
			type,
			if (indices_is_direct) indices else Buffers.getArray(indices),
			if (indices_is_direct) Buffers.getDirectBufferByteOffset(indices) else Buffers.getIndirectBufferByteOffset(
				indices
			),
			indices_is_direct
		)
	}

	/** Entry point to C language function: `void glDrawElements(GLenum mode, GLsizei count, GLenum type, const void *  indices)`<br></br>
	 * @param indices a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glDrawElements1(
		mode: Int,
		count: Int,
		type: Int,
		indices: Any,
		indices_byte_offset: Int,
		indices_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glEnable(GLenum cap)`<br></br>    */
	external override fun glEnable(cap: Int)

	/** Interface to C language function: <br></br> `void glEnableVertexAttribArray(GLuint index)`<br></br>    */
	external override fun glEnableVertexAttribArray(index: Int)

	/** Interface to C language function: <br></br> `void glFinish()`<br></br>    */
	external override fun glFinish()

	/** Interface to C language function: <br></br> `void glFlush()`<br></br>    */
	external override fun glFlush()

	/** Interface to C language function: <br></br> `void glFramebufferRenderbuffer(GLenum target, GLenum attachment, GLenum renderbuffertarget, GLuint renderbuffer)`<br></br>    */
	external override fun glFramebufferRenderbuffer(
		target: Int,
		attachment: Int,
		renderbuffertarget: Int,
		renderbuffer: Int
	)

	/** Interface to C language function: <br></br> `void glFramebufferTexture2D(GLenum target, GLenum attachment, GLenum textarget, GLuint texture, GLint level)`<br></br>    */
	external override fun glFramebufferTexture2D(
		target: Int,
		attachment: Int,
		textarget: Int,
		texture: Int,
		level: Int
	)

	/** Interface to C language function: <br></br> `void glFrontFace(GLenum mode)`<br></br>    */
	external override fun glFrontFace(mode: Int)

	/** Interface to C language function: <br></br> `void glGenBuffers(GLsizei n, GLuint *  buffers)`<br></br>
	 * @param buffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGenBuffers(n: Int, buffers: IntBuffer?) {
		val buffers_is_direct: Boolean = Buffers.isDirect(buffers)
		glGenBuffers1(
			n,
			if (buffers_is_direct) buffers else Buffers.getArray(buffers),
			if (buffers_is_direct) Buffers.getDirectBufferByteOffset(buffers) else Buffers.getIndirectBufferByteOffset(
				buffers
			),
			buffers_is_direct
		)
	}

	/** Entry point to C language function: `void glGenBuffers(GLsizei n, GLuint *  buffers)`<br></br>
	 * @param buffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGenBuffers1(
		n: Int,
		buffers: Any?,
		buffers_byte_offset: Int,
		buffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGenBuffers(GLsizei n, GLuint *  buffers)`<br></br>    */
	override fun glGenBuffers(n: Int, buffers: IntArray?, buffers_offset: Int) {
		if (buffers != null && buffers.size <= buffers_offset) throw RuntimeException("array offset argument \"buffers_offset\" (" + buffers_offset + ") equals or exceeds array length (" + buffers.size + ")")
		glGenBuffers1(n, buffers, Buffers.SIZEOF_INT * buffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGenerateMipmap(GLenum target)`<br></br>    */
	external override fun glGenerateMipmap(target: Int)

	/** Interface to C language function: <br></br> `void glGenFramebuffers(GLsizei n, GLuint *  framebuffers)`<br></br>
	 * @param framebuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGenFramebuffers(n: Int, framebuffers: IntBuffer?) {
		val framebuffers_is_direct: Boolean = Buffers.isDirect(framebuffers)
		glGenFramebuffers1(
			n,
			if (framebuffers_is_direct) framebuffers else Buffers.getArray(framebuffers),
			if (framebuffers_is_direct) Buffers.getDirectBufferByteOffset(framebuffers) else Buffers.getIndirectBufferByteOffset(
				framebuffers
			),
			framebuffers_is_direct
		)
	}

	/** Entry point to C language function: `void glGenFramebuffers(GLsizei n, GLuint *  framebuffers)`<br></br>
	 * @param framebuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGenFramebuffers1(
		n: Int,
		framebuffers: Any?,
		framebuffers_byte_offset: Int,
		framebuffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGenFramebuffers(GLsizei n, GLuint *  framebuffers)`<br></br>    */
	override fun glGenFramebuffers(n: Int, framebuffers: IntArray?, framebuffers_offset: Int) {
		if (framebuffers != null && framebuffers.size <= framebuffers_offset) throw RuntimeException(
			"array offset argument \"framebuffers_offset\" (" + framebuffers_offset + ") equals or exceeds array length (" + framebuffers.size + ")"
		)
		glGenFramebuffers1(n, framebuffers, Buffers.SIZEOF_INT * framebuffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGenRenderbuffers(GLsizei n, GLuint *  renderbuffers)`<br></br>
	 * @param renderbuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGenRenderbuffers(n: Int, renderbuffers: IntBuffer?) {
		val renderbuffers_is_direct: Boolean = Buffers.isDirect(renderbuffers)
		glGenRenderbuffers1(
			n,
			if (renderbuffers_is_direct) renderbuffers else Buffers.getArray(renderbuffers),
			if (renderbuffers_is_direct) Buffers.getDirectBufferByteOffset(renderbuffers) else Buffers.getIndirectBufferByteOffset(
				renderbuffers
			),
			renderbuffers_is_direct
		)
	}

	/** Entry point to C language function: `void glGenRenderbuffers(GLsizei n, GLuint *  renderbuffers)`<br></br>
	 * @param renderbuffers a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGenRenderbuffers1(
		n: Int,
		renderbuffers: Any?,
		renderbuffers_byte_offset: Int,
		renderbuffers_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGenRenderbuffers(GLsizei n, GLuint *  renderbuffers)`<br></br>    */
	override fun glGenRenderbuffers(n: Int, renderbuffers: IntArray?, renderbuffers_offset: Int) {
		if (renderbuffers != null && renderbuffers.size <= renderbuffers_offset) throw RuntimeException(
			"array offset argument \"renderbuffers_offset\" (" + renderbuffers_offset + ") equals or exceeds array length (" + renderbuffers.size + ")"
		)
		glGenRenderbuffers1(n, renderbuffers, Buffers.SIZEOF_INT * renderbuffers_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGenTextures(GLsizei n, GLuint *  textures)`<br></br>
	 * @param textures a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGenTextures(n: Int, textures: IntBuffer?) {
		val textures_is_direct: Boolean = Buffers.isDirect(textures)
		glGenTextures1(
			n,
			if (textures_is_direct) textures else Buffers.getArray(textures),
			if (textures_is_direct) Buffers.getDirectBufferByteOffset(textures) else Buffers.getIndirectBufferByteOffset(
				textures
			),
			textures_is_direct
		)
	}

	/** Entry point to C language function: `void glGenTextures(GLsizei n, GLuint *  textures)`<br></br>
	 * @param textures a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGenTextures1(
		n: Int,
		textures: Any?,
		textures_byte_offset: Int,
		textures_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGenTextures(GLsizei n, GLuint *  textures)`<br></br>    */
	override fun glGenTextures(n: Int, textures: IntArray?, textures_offset: Int) {
		if (textures != null && textures.size <= textures_offset) throw RuntimeException("array offset argument \"textures_offset\" (" + textures_offset + ") equals or exceeds array length (" + textures.size + ")")
		glGenTextures1(n, textures, Buffers.SIZEOF_INT * textures_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetActiveAttrib(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param size a direct or array-backed [java.nio.IntBuffer]
	 * @param type a direct or array-backed [java.nio.IntBuffer]
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetActiveAttrib(
		program: Int,
		index: Int,
		bufSize: Int,
		length: IntBuffer?,
		size: IntBuffer?,
		type: IntBuffer?,
		name: ByteBuffer?
	) {


		val length_is_direct: Boolean = Buffers.isDirect(length)
		val size_is_direct: Boolean = Buffers.isDirect(size)
		val type_is_direct: Boolean = Buffers.isDirect(type)
		val name_is_direct: Boolean = Buffers.isDirect(name)
		glGetActiveAttrib1(
			program,
			index,
			bufSize,
			if (length_is_direct) length else Buffers.getArray(length),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length) else Buffers.getIndirectBufferByteOffset(
				length
			),
			length_is_direct,
			if (size_is_direct) size else Buffers.getArray(size),
			if (size_is_direct) Buffers.getDirectBufferByteOffset(size) else Buffers.getIndirectBufferByteOffset(
				size
			),
			size_is_direct,
			if (type_is_direct) type else Buffers.getArray(type),
			if (type_is_direct) Buffers.getDirectBufferByteOffset(type) else Buffers.getIndirectBufferByteOffset(
				type
			),
			type_is_direct,
			if (name_is_direct) name else Buffers.getArray(name),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name) else Buffers.getIndirectBufferByteOffset(
				name
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `void glGetActiveAttrib(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param size a direct or array-backed [java.nio.IntBuffer]
	 * @param type a direct or array-backed [java.nio.IntBuffer]
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetActiveAttrib1(
		program: Int,
		index: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		size: Any?,
		size_byte_offset: Int,
		size_is_direct: Boolean,
		type: Any?,
		type_byte_offset: Int,
		type_is_direct: Boolean,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetActiveAttrib(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>    */
	override fun glGetActiveAttrib(
		program: Int,
		index: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		size: IntArray?,
		size_offset: Int,
		type: IntArray?,
		type_offset: Int,
		name: ByteArray?,
		name_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (size != null && size.size <= size_offset) throw RuntimeException("array offset argument \"size_offset\" (" + size_offset + ") equals or exceeds array length (" + size.size + ")")
		if (type != null && type.size <= type_offset) throw RuntimeException("array offset argument \"type_offset\" (" + type_offset + ") equals or exceeds array length (" + type.size + ")")
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		glGetActiveAttrib1(
			program,
			index,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			size,
			Buffers.SIZEOF_INT * size_offset,
			false,
			type,
			Buffers.SIZEOF_INT * type_offset,
			false,
			name,
			name_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetActiveUniform(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param size a direct or array-backed [java.nio.IntBuffer]
	 * @param type a direct or array-backed [java.nio.IntBuffer]
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetActiveUniform(
		program: Int,
		index: Int,
		bufSize: Int,
		length: IntBuffer?,
		size: IntBuffer?,
		type: IntBuffer?,
		name: ByteBuffer?
	) {
		val length_is_direct: Boolean = Buffers.isDirect(length)
		val size_is_direct: Boolean = Buffers.isDirect(size)
		val type_is_direct: Boolean = Buffers.isDirect(type)
		val name_is_direct: Boolean = Buffers.isDirect(name)
		glGetActiveUniform1(
			program,
			index,
			bufSize,
			if (length_is_direct) length else Buffers.getArray(length),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length) else Buffers.getIndirectBufferByteOffset(
				length
			),
			length_is_direct,
			if (size_is_direct) size else Buffers.getArray(size),
			if (size_is_direct) Buffers.getDirectBufferByteOffset(size) else Buffers.getIndirectBufferByteOffset(
				size
			),
			size_is_direct,
			if (type_is_direct) type else Buffers.getArray(type),
			if (type_is_direct) Buffers.getDirectBufferByteOffset(type) else Buffers.getIndirectBufferByteOffset(
				type
			),
			type_is_direct,
			if (name_is_direct) name else Buffers.getArray(name),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name) else Buffers.getIndirectBufferByteOffset(
				name
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `void glGetActiveUniform(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param size a direct or array-backed [java.nio.IntBuffer]
	 * @param type a direct or array-backed [java.nio.IntBuffer]
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetActiveUniform1(
		program: Int,
		index: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		size: Any?,
		size_byte_offset: Int,
		size_is_direct: Boolean,
		type: Any?,
		type_byte_offset: Int,
		type_is_direct: Boolean,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetActiveUniform(GLuint program, GLuint index, GLsizei bufSize, GLsizei *  length, GLint *  size, GLenum *  type, GLchar *  name)`<br></br>    */
	override fun glGetActiveUniform(
		program: Int,
		index: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		size: IntArray?,
		size_offset: Int,
		type: IntArray?,
		type_offset: Int,
		name: ByteArray?,
		name_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (size != null && size.size <= size_offset) throw RuntimeException("array offset argument \"size_offset\" (" + size_offset + ") equals or exceeds array length (" + size.size + ")")
		if (type != null && type.size <= type_offset) throw RuntimeException("array offset argument \"type_offset\" (" + type_offset + ") equals or exceeds array length (" + type.size + ")")
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		glGetActiveUniform1(
			program,
			index,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			size,
			Buffers.SIZEOF_INT * size_offset,
			false,
			type,
			Buffers.SIZEOF_INT * type_offset,
			false,
			name,
			name_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetAttachedShaders(GLuint program, GLsizei maxCount, GLsizei *  count, GLuint *  shaders)`<br></br>
	 * @param count a direct or array-backed [java.nio.IntBuffer]
	 * @param shaders a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetAttachedShaders(program: Int, maxCount: Int, count: IntBuffer?, shaders: IntBuffer?) {
		val count_is_direct: Boolean = Buffers.isDirect(count)
		val shaders_is_direct: Boolean = Buffers.isDirect(shaders)
		glGetAttachedShaders1(
			program,
			maxCount,
			if (count_is_direct) count else Buffers.getArray(count),
			if (count_is_direct) Buffers.getDirectBufferByteOffset(count) else Buffers.getIndirectBufferByteOffset(
				count
			),
			count_is_direct,
			if (shaders_is_direct) shaders else Buffers.getArray(shaders),
			if (shaders_is_direct) Buffers.getDirectBufferByteOffset(shaders) else Buffers.getIndirectBufferByteOffset(
				shaders
			),
			shaders_is_direct
		)
	}

	/** Entry point to C language function: `void glGetAttachedShaders(GLuint program, GLsizei maxCount, GLsizei *  count, GLuint *  shaders)`<br></br>
	 * @param count a direct or array-backed [java.nio.IntBuffer]
	 * @param shaders a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetAttachedShaders1(
		program: Int,
		maxCount: Int,
		count: Any?,
		count_byte_offset: Int,
		count_is_direct: Boolean,
		shaders: Any?,
		shaders_byte_offset: Int,
		shaders_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetAttachedShaders(GLuint program, GLsizei maxCount, GLsizei *  count, GLuint *  shaders)`<br></br>    */
	override fun glGetAttachedShaders(
		program: Int,
		maxCount: Int,
		count: IntArray?,
		count_offset: Int,
		shaders: IntArray?,
		shaders_offset: Int
	) {
		if (count != null && count.size <= count_offset) throw RuntimeException("array offset argument \"count_offset\" (" + count_offset + ") equals or exceeds array length (" + count.size + ")")
		if (shaders != null && shaders.size <= shaders_offset) throw RuntimeException("array offset argument \"shaders_offset\" (" + shaders_offset + ") equals or exceeds array length (" + shaders.size + ")")
		glGetAttachedShaders1(
			program,
			maxCount,
			count,
			Buffers.SIZEOF_INT * count_offset,
			false,
			shaders,
			Buffers.SIZEOF_INT * shaders_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `GLint glGetAttribLocation(GLuint program, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetAttribLocation(program: Int, name: ByteBuffer?): Int {
		val name_is_direct: Boolean = Buffers.isDirect(name)
		return glGetAttribLocation1(
			program,
			if (name_is_direct) name else Buffers.getArray(name),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name) else Buffers.getIndirectBufferByteOffset(
				name
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `GLint glGetAttribLocation(GLuint program, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetAttribLocation1(
		program: Int,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	): Int

	/** Interface to C language function: <br></br> `GLint glGetAttribLocation(GLuint program, const GLchar *  name)`<br></br>    */
	override fun glGetAttribLocation(program: Int, name: ByteArray?, name_offset: Int): Int {
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		return glGetAttribLocation1(program, name, name_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetBooleanv(GLenum pname, GLboolean *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetBooleanv(pname: Int, data: ByteBuffer?) {
		val data_is_direct: Boolean = Buffers.isDirect(data)
		glGetBooleanv1(
			pname,
			if (data_is_direct) data else Buffers.getArray(data),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data) else Buffers.getIndirectBufferByteOffset(
				data
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glGetBooleanv(GLenum pname, GLboolean *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetBooleanv1(
		pname: Int,
		data: Any?,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetBooleanv(GLenum pname, GLboolean *  data)`<br></br>    */
	override fun glGetBooleanv(pname: Int, data: ByteArray?, data_offset: Int) {
		if (data != null && data.size <= data_offset) throw RuntimeException("array offset argument \"data_offset\" (" + data_offset + ") equals or exceeds array length (" + data.size + ")")
		glGetBooleanv1(pname, data, data_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetBufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetBufferParameteriv(target: Int, pname: Int, params: IntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetBufferParameteriv1(
			target,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetBufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetBufferParameteriv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetBufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetBufferParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetBufferParameteriv1(target, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `GLenum glGetError()`<br></br>    */
	external override fun glGetError(): Int

	/** Interface to C language function: <br></br> `void glGetFloatv(GLenum pname, GLfloat *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glGetFloatv(pname: Int, data: FloatBuffer?) {
		val data_is_direct: Boolean = Buffers.isDirect(data)
		glGetFloatv1(
			pname,
			if (data_is_direct) data else Buffers.getArray(data),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data) else Buffers.getIndirectBufferByteOffset(
				data
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glGetFloatv(GLenum pname, GLfloat *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glGetFloatv1(
		pname: Int,
		data: Any?,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetFloatv(GLenum pname, GLfloat *  data)`<br></br>    */
	override fun glGetFloatv(pname: Int, data: FloatArray?, data_offset: Int) {
		if (data != null && data.size <= data_offset) throw RuntimeException("array offset argument \"data_offset\" (" + data_offset + ") equals or exceeds array length (" + data.size + ")")
		glGetFloatv1(pname, data, Buffers.SIZEOF_FLOAT * data_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetFramebufferAttachmentParameteriv(GLenum target, GLenum attachment, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetFramebufferAttachmentParameteriv(
		target: Int,
		attachment: Int,
		pname: Int,
		params: IntBuffer?
	) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetFramebufferAttachmentParameteriv1(
			target,
			attachment,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetFramebufferAttachmentParameteriv(GLenum target, GLenum attachment, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetFramebufferAttachmentParameteriv1(
		target: Int,
		attachment: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetFramebufferAttachmentParameteriv(GLenum target, GLenum attachment, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetFramebufferAttachmentParameteriv(
		target: Int,
		attachment: Int,
		pname: Int,
		params: IntArray?,
		params_offset: Int
	) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetFramebufferAttachmentParameteriv1(
			target,
			attachment,
			pname,
			params,
			Buffers.SIZEOF_INT * params_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetIntegerv(GLenum pname, GLint *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetIntegerv(pname: Int, data: IntBuffer?) {
		val data_is_direct: Boolean = Buffers.isDirect(data)
		glGetIntegerv1(
			pname,
			if (data_is_direct) data else Buffers.getArray(data),
			if (data_is_direct) Buffers.getDirectBufferByteOffset(data) else Buffers.getIndirectBufferByteOffset(
				data
			),
			data_is_direct
		)
	}

	/** Entry point to C language function: `void glGetIntegerv(GLenum pname, GLint *  data)`<br></br>
	 * @param data a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetIntegerv1(
		pname: Int,
		data: Any?,
		data_byte_offset: Int,
		data_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetIntegerv(GLenum pname, GLint *  data)`<br></br>    */
	override fun glGetIntegerv(pname: Int, data: IntArray?, data_offset: Int) {
		if (data != null && data.size <= data_offset) throw RuntimeException("array offset argument \"data_offset\" (" + data_offset + ") equals or exceeds array length (" + data.size + ")")
		glGetIntegerv1(pname, data, Buffers.SIZEOF_INT * data_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetProgramiv(GLuint program, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetProgramiv(program: Int, pname: Int, params: IntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetProgramiv1(
			program,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetProgramiv(GLuint program, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetProgramiv1(
		program: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetProgramiv(GLuint program, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetProgramiv(program: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetProgramiv1(program, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetProgramInfoLog(GLuint program, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param infoLog a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetProgramInfoLog(program: Int, bufSize: Int, length: IntBuffer?, infoLog: ByteBuffer?) {
		val length_is_direct: Boolean = Buffers.isDirect(length)
		val infoLog_is_direct: Boolean = Buffers.isDirect(infoLog)
		glGetProgramInfoLog1(
			program,
			bufSize,
			if (length_is_direct) length else Buffers.getArray(length),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length) else Buffers.getIndirectBufferByteOffset(
				length
			),
			length_is_direct,
			if (infoLog_is_direct) infoLog else Buffers.getArray(infoLog),
			if (infoLog_is_direct) Buffers.getDirectBufferByteOffset(infoLog) else Buffers.getIndirectBufferByteOffset(
				infoLog
			),
			infoLog_is_direct
		)
	}

	/** Entry point to C language function: `void glGetProgramInfoLog(GLuint program, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param infoLog a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetProgramInfoLog1(
		program: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		infoLog: Any?,
		infoLog_byte_offset: Int,
		infoLog_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetProgramInfoLog(GLuint program, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>    */
	override fun glGetProgramInfoLog(
		program: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		infoLog: ByteArray?,
		infoLog_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (infoLog != null && infoLog.size <= infoLog_offset) throw RuntimeException("array offset argument \"infoLog_offset\" (" + infoLog_offset + ") equals or exceeds array length (" + infoLog.size + ")")
		glGetProgramInfoLog1(
			program,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			infoLog,
			infoLog_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetRenderbufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetRenderbufferParameteriv(target: Int, pname: Int, params: IntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetRenderbufferParameteriv1(
			target,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetRenderbufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetRenderbufferParameteriv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetRenderbufferParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetRenderbufferParameteriv(
		target: Int,
		pname: Int,
		params: IntArray?,
		params_offset: Int
	) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetRenderbufferParameteriv1(
			target,
			pname,
			params,
			Buffers.SIZEOF_INT * params_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetShaderiv(GLuint shader, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetShaderiv(shader: Int, pname: Int, params: IntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetShaderiv1(
			shader,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetShaderiv(GLuint shader, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetShaderiv1(
		shader: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetShaderiv(GLuint shader, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetShaderiv(shader: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetShaderiv1(shader, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetShaderInfoLog(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param infoLog a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetShaderInfoLog(shader: Int, bufSize: Int, length: IntBuffer?, infoLog: ByteBuffer?) {
		val length_is_direct: Boolean = Buffers.isDirect(length)
		val infoLog_is_direct: Boolean = Buffers.isDirect(infoLog)
		glGetShaderInfoLog1(
			shader,
			bufSize,
			if (length_is_direct) length else Buffers.getArray(length),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length) else Buffers.getIndirectBufferByteOffset(
				length
			),
			length_is_direct,
			if (infoLog_is_direct) infoLog else Buffers.getArray(infoLog),
			if (infoLog_is_direct) Buffers.getDirectBufferByteOffset(infoLog) else Buffers.getIndirectBufferByteOffset(
				infoLog
			),
			infoLog_is_direct
		)
	}

	/** Entry point to C language function: `void glGetShaderInfoLog(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param infoLog a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetShaderInfoLog1(
		shader: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		infoLog: Any?,
		infoLog_byte_offset: Int,
		infoLog_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetShaderInfoLog(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  infoLog)`<br></br>    */
	override fun glGetShaderInfoLog(
		shader: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		infoLog: ByteArray?,
		infoLog_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (infoLog != null && infoLog.size <= infoLog_offset) throw RuntimeException("array offset argument \"infoLog_offset\" (" + infoLog_offset + ") equals or exceeds array length (" + infoLog.size + ")")
		glGetShaderInfoLog1(
			shader,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			infoLog,
			infoLog_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetShaderPrecisionFormat(GLenum shadertype, GLenum precisiontype, GLint *  range, GLint *  precision)`<br></br>
	 * @param range a direct or array-backed [java.nio.IntBuffer]
	 * @param precision a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetShaderPrecisionFormat(
		shadertype: Int,
		precisiontype: Int,
		range: IntBuffer?,
		precision: IntBuffer?
	) {
		val range_is_direct: Boolean = Buffers.isDirect(range)
		val precision_is_direct: Boolean = Buffers.isDirect(precision)
		glGetShaderPrecisionFormat1(
			shadertype,
			precisiontype,
			if (range_is_direct) range else Buffers.getArray(range),
			if (range_is_direct) Buffers.getDirectBufferByteOffset(range) else Buffers.getIndirectBufferByteOffset(
				range
			),
			range_is_direct,
			if (precision_is_direct) precision else Buffers.getArray(precision),
			if (precision_is_direct) Buffers.getDirectBufferByteOffset(precision) else Buffers.getIndirectBufferByteOffset(
				precision
			),
			precision_is_direct
		)
	}

	/** Entry point to C language function: `void glGetShaderPrecisionFormat(GLenum shadertype, GLenum precisiontype, GLint *  range, GLint *  precision)`<br></br>
	 * @param range a direct or array-backed [java.nio.IntBuffer]
	 * @param precision a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetShaderPrecisionFormat1(
		shadertype: Int,
		precisiontype: Int,
		range: Any?,
		range_byte_offset: Int,
		range_is_direct: Boolean,
		precision: Any?,
		precision_byte_offset: Int,
		precision_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetShaderPrecisionFormat(GLenum shadertype, GLenum precisiontype, GLint *  range, GLint *  precision)`<br></br>    */
	override fun glGetShaderPrecisionFormat(
		shadertype: Int,
		precisiontype: Int,
		range: IntArray?,
		range_offset: Int,
		precision: IntArray?,
		precision_offset: Int
	) {
		if (range != null && range.size <= range_offset) throw RuntimeException("array offset argument \"range_offset\" (" + range_offset + ") equals or exceeds array length (" + range.size + ")")
		if (precision != null && precision.size <= precision_offset) throw RuntimeException("array offset argument \"precision_offset\" (" + precision_offset + ") equals or exceeds array length (" + precision.size + ")")
		glGetShaderPrecisionFormat1(
			shadertype,
			precisiontype,
			range,
			Buffers.SIZEOF_INT * range_offset,
			false,
			precision,
			Buffers.SIZEOF_INT * precision_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glGetShaderSource(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  source)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param source a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetShaderSource(shader: Int, bufSize: Int, length: IntBuffer?, source: ByteBuffer?) {
		val length_is_direct: Boolean = Buffers.isDirect(length)
		val source_is_direct: Boolean = Buffers.isDirect(source)
		glGetShaderSource1(
			shader,
			bufSize,
			if (length_is_direct) length else Buffers.getArray(length),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length) else Buffers.getIndirectBufferByteOffset(
				length
			),
			length_is_direct,
			if (source_is_direct) source else Buffers.getArray(source),
			if (source_is_direct) Buffers.getDirectBufferByteOffset(source) else Buffers.getIndirectBufferByteOffset(
				source
			),
			source_is_direct
		)
	}

	/** Entry point to C language function: `void glGetShaderSource(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  source)`<br></br>
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 * @param source a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetShaderSource1(
		shader: Int,
		bufSize: Int,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean,
		source: Any?,
		source_byte_offset: Int,
		source_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetShaderSource(GLuint shader, GLsizei bufSize, GLsizei *  length, GLchar *  source)`<br></br>    */
	override fun glGetShaderSource(
		shader: Int,
		bufSize: Int,
		length: IntArray?,
		length_offset: Int,
		source: ByteArray?,
		source_offset: Int
	) {
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		if (source != null && source.size <= source_offset) throw RuntimeException("array offset argument \"source_offset\" (" + source_offset + ") equals or exceeds array length (" + source.size + ")")
		glGetShaderSource1(
			shader,
			bufSize,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false,
			source,
			source_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `const GLubyte *  glGetString(GLenum name)`<br></br>    */
	override fun glGetString(name: Int): ByteBuffer? {
		val _res = glGetString1(name) ?: return null
		Buffers.nativeOrder(_res)
		return _res
	}

	/** Entry point to C language function: `const GLubyte *  glGetString(GLenum name)`<br></br>    */
	private external fun glGetString1(name: Int): ByteBuffer?

	/** Interface to C language function: <br></br> `void glGetTexParameterfv(GLenum target, GLenum pname, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glGetTexParameterfv(target: Int, pname: Int, params: FloatBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetTexParameterfv1(
			target,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetTexParameterfv(GLenum target, GLenum pname, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glGetTexParameterfv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetTexParameterfv(GLenum target, GLenum pname, GLfloat *  params)`<br></br>    */
	override fun glGetTexParameterfv(target: Int, pname: Int, params: FloatArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetTexParameterfv1(target, pname, params, Buffers.SIZEOF_FLOAT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetTexParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetTexParameteriv(target: Int, pname: Int, params: IntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetTexParameteriv1(
			target,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetTexParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetTexParameteriv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetTexParameteriv(GLenum target, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetTexParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetTexParameteriv1(target, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetUniformfv(GLuint program, GLint location, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glGetUniformfv(program: Int, location: Int, params: FloatBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetUniformfv1(
			program,
			location,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetUniformfv(GLuint program, GLint location, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glGetUniformfv1(
		program: Int,
		location: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetUniformfv(GLuint program, GLint location, GLfloat *  params)`<br></br>    */
	override fun glGetUniformfv(program: Int, location: Int, params: FloatArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetUniformfv1(program, location, params, Buffers.SIZEOF_FLOAT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetUniformiv(GLuint program, GLint location, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetUniformiv(program: Int, location: Int, params: IntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetUniformiv1(
			program,
			location,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetUniformiv(GLuint program, GLint location, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetUniformiv1(
		program: Int,
		location: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetUniformiv(GLuint program, GLint location, GLint *  params)`<br></br>    */
	override fun glGetUniformiv(program: Int, location: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetUniformiv1(program, location, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `GLint glGetUniformLocation(GLuint program, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	override fun glGetUniformLocation(program: Int, name: ByteBuffer?): Int {
		val name_is_direct: Boolean = Buffers.isDirect(name)
		return glGetUniformLocation1(
			program,
			if (name_is_direct) name else Buffers.getArray(name),
			if (name_is_direct) Buffers.getDirectBufferByteOffset(name) else Buffers.getIndirectBufferByteOffset(
				name
			),
			name_is_direct
		)
	}

	/** Entry point to C language function: `GLint glGetUniformLocation(GLuint program, const GLchar *  name)`<br></br>
	 * @param name a direct or array-backed [java.nio.ByteBuffer]
	 */
	private external fun glGetUniformLocation1(
		program: Int,
		name: Any?,
		name_byte_offset: Int,
		name_is_direct: Boolean
	): Int

	/** Interface to C language function: <br></br> `GLint glGetUniformLocation(GLuint program, const GLchar *  name)`<br></br>    */
	override fun glGetUniformLocation(program: Int, name: ByteArray?, name_offset: Int): Int {
		if (name != null && name.size <= name_offset) throw RuntimeException("array offset argument \"name_offset\" (" + name_offset + ") equals or exceeds array length (" + name.size + ")")
		return glGetUniformLocation1(program, name, name_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetVertexAttribfv(GLuint index, GLenum pname, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glGetVertexAttribfv(index: Int, pname: Int, params: FloatBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetVertexAttribfv1(
			index,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetVertexAttribfv(GLuint index, GLenum pname, GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glGetVertexAttribfv1(
		index: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetVertexAttribfv(GLuint index, GLenum pname, GLfloat *  params)`<br></br>    */
	override fun glGetVertexAttribfv(index: Int, pname: Int, params: FloatArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetVertexAttribfv1(index, pname, params, Buffers.SIZEOF_FLOAT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetVertexAttribiv(GLuint index, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glGetVertexAttribiv(index: Int, pname: Int, params: IntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glGetVertexAttribiv1(
			index,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glGetVertexAttribiv(GLuint index, GLenum pname, GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glGetVertexAttribiv1(
		index: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glGetVertexAttribiv(GLuint index, GLenum pname, GLint *  params)`<br></br>    */
	override fun glGetVertexAttribiv(index: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glGetVertexAttribiv1(index, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glGetVertexAttribPointerv(GLuint index, GLenum pname, void *  *  pointer)`<br></br>
	 * @param pointer a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
	 */
	override fun glGetVertexAttribPointerv(index: Int, pname: Int, pointer: PointerBuffer?) {
		val pointer_is_direct: Boolean = Buffers.isDirect(pointer)
		glGetVertexAttribPointerv1(
			index,
			pname,
			if (pointer_is_direct) (if (pointer != null) pointer.getBuffer() else null) else Buffers.getArray(
				pointer
			),
			if (pointer_is_direct) Buffers.getDirectBufferByteOffset(pointer) else Buffers.getIndirectBufferByteOffset(
				pointer
			),
			pointer_is_direct
		)
	}

	/** Entry point to C language function: `void glGetVertexAttribPointerv(GLuint index, GLenum pname, void *  *  pointer)`<br></br>
	 * @param pointer a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
	 */
	private external fun glGetVertexAttribPointerv1(
		index: Int,
		pname: Int,
		pointer: Any?,
		pointer_byte_offset: Int,
		pointer_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glHint(GLenum target, GLenum mode)`<br></br>    */
	external override fun glHint(target: Int, mode: Int)

	/** Interface to C language function: <br></br> `GLboolean glIsBuffer(GLuint buffer)`<br></br>    */
	external override fun glIsBuffer(buffer: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsEnabled(GLenum cap)`<br></br>    */
	external override fun glIsEnabled(cap: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsFramebuffer(GLuint framebuffer)`<br></br>    */
	external override fun glIsFramebuffer(framebuffer: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsProgram(GLuint program)`<br></br>    */
	external override fun glIsProgram(program: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsRenderbuffer(GLuint renderbuffer)`<br></br>    */
	external override fun glIsRenderbuffer(renderbuffer: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsShader(GLuint shader)`<br></br>    */
	external override fun glIsShader(shader: Int): Byte

	/** Interface to C language function: <br></br> `GLboolean glIsTexture(GLuint texture)`<br></br>    */
	external override fun glIsTexture(texture: Int): Byte

	/** Interface to C language function: <br></br> `void glLineWidth(GLfloat width)`<br></br>    */
	external override fun glLineWidth(width: Float)

	/** Interface to C language function: <br></br> `void glLinkProgram(GLuint program)`<br></br>    */
	external override fun glLinkProgram(program: Int)

	/** Interface to C language function: <br></br> `void glPixelStorei(GLenum pname, GLint param)`<br></br>    */
	external override fun glPixelStorei(pname: Int, param: Int)

	/** Interface to C language function: <br></br> `void glPolygonOffset(GLfloat factor, GLfloat units)`<br></br>    */
	external override fun glPolygonOffset(factor: Float, units: Float)

	/** Interface to C language function: <br></br> `void glReadPixels(GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	override fun glReadPixels(
		x: Int,
		y: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: Buffer
	) {
		val pixels_is_direct: Boolean = Buffers.isDirect(pixels)
		glReadPixels1(
			x,
			y,
			width,
			height,
			format,
			type,
			if (pixels_is_direct) pixels else Buffers.getArray(pixels),
			if (pixels_is_direct) Buffers.getDirectBufferByteOffset(pixels) else Buffers.getIndirectBufferByteOffset(
				pixels
			),
			pixels_is_direct
		)
	}

	/** Entry point to C language function: `void glReadPixels(GLint x, GLint y, GLsizei width, GLsizei height, GLenum format, GLenum type, void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glReadPixels1(
		x: Int,
		y: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: Any,
		pixels_byte_offset: Int,
		pixels_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glReleaseShaderCompiler()`<br></br>    */
	external override fun glReleaseShaderCompiler()

	/** Interface to C language function: <br></br> `void glRenderbufferStorage(GLenum target, GLenum internalformat, GLsizei width, GLsizei height)`<br></br>    */
	external override fun glRenderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int)

	/** Interface to C language function: <br></br> `void glSampleCoverage(GLfloat value, GLboolean invert)`<br></br>    */
	external override fun glSampleCoverage(value: Float, invert: Byte)

	/** Interface to C language function: <br></br> `void glScissor(GLint x, GLint y, GLsizei width, GLsizei height)`<br></br>    */
	external override fun glScissor(x: Int, y: Int, width: Int, height: Int)

	/** Interface to C language function: <br></br> `void glShaderBinary(GLsizei count, const GLuint *  shaders, GLenum binaryFormat, const void *  binary, GLsizei length)`<br></br>
	 * @param shaders a direct or array-backed [java.nio.IntBuffer]
	 * @param binary a direct or array-backed [java.nio.Buffer]
	 */
	override fun glShaderBinary(
		count: Int,
		shaders: IntBuffer?,
		binaryFormat: Int,
		binary: Buffer,
		length: Int
	) {
		val shaders_is_direct: Boolean = Buffers.isDirect(shaders)
		val binary_is_direct: Boolean = Buffers.isDirect(binary)
		glShaderBinary1(
			count,
			if (shaders_is_direct) shaders else Buffers.getArray(shaders),
			if (shaders_is_direct) Buffers.getDirectBufferByteOffset(shaders) else Buffers.getIndirectBufferByteOffset(
				shaders
			),
			shaders_is_direct,
			binaryFormat,
			if (binary_is_direct) binary else Buffers.getArray(binary),
			if (binary_is_direct) Buffers.getDirectBufferByteOffset(binary) else Buffers.getIndirectBufferByteOffset(
				binary
			),
			binary_is_direct,
			length
		)
	}

	/** Entry point to C language function: `void glShaderBinary(GLsizei count, const GLuint *  shaders, GLenum binaryFormat, const void *  binary, GLsizei length)`<br></br>
	 * @param shaders a direct or array-backed [java.nio.IntBuffer]
	 * @param binary a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glShaderBinary1(
		count: Int,
		shaders: Any?,
		shaders_byte_offset: Int,
		shaders_is_direct: Boolean,
		binaryFormat: Int,
		binary: Any,
		binary_byte_offset: Int,
		binary_is_direct: Boolean,
		length: Int
	)

	/** Interface to C language function: <br></br> `void glShaderBinary(GLsizei count, const GLuint *  shaders, GLenum binaryFormat, const void *  binary, GLsizei length)`<br></br>
	 * @param binary a direct or array-backed [java.nio.Buffer]
	 */
	override fun glShaderBinary(
		count: Int,
		shaders: IntArray?,
		shaders_offset: Int,
		binaryFormat: Int,
		binary: Buffer,
		length: Int
	) {
		if (shaders != null && shaders.size <= shaders_offset) throw RuntimeException("array offset argument \"shaders_offset\" (" + shaders_offset + ") equals or exceeds array length (" + shaders.size + ")")
		val binary_is_direct: Boolean = Buffers.isDirect(binary)
		glShaderBinary1(
			count,
			shaders,
			Buffers.SIZEOF_INT * shaders_offset,
			false,
			binaryFormat,
			if (binary_is_direct) binary else Buffers.getArray(binary),
			if (binary_is_direct) Buffers.getDirectBufferByteOffset(binary) else Buffers.getIndirectBufferByteOffset(
				binary
			),
			binary_is_direct,
			length
		)
	}

	/** Interface to C language function: <br></br> `void glShaderSource(GLuint shader, GLsizei count, const GLchar * const  *  string, const GLint *  length)`<br></br>
	 * @param string a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glShaderSource(shader: Int, count: Int, string: PointerBuffer?, length: IntBuffer?) {
		val string_is_direct: Boolean = Buffers.isDirect(string)
		val length_is_direct: Boolean = Buffers.isDirect(length)
		glShaderSource1(
			shader,
			count,
			if (string_is_direct) (if (string != null) string.getBuffer() else null) else Buffers.getArray(
				string
			),
			if (string_is_direct) Buffers.getDirectBufferByteOffset(string) else Buffers.getIndirectBufferByteOffset(
				string
			),
			string_is_direct,
			if (length_is_direct) length else Buffers.getArray(length),
			if (length_is_direct) Buffers.getDirectBufferByteOffset(length) else Buffers.getIndirectBufferByteOffset(
				length
			),
			length_is_direct
		)
	}

	/** Entry point to C language function: `void glShaderSource(GLuint shader, GLsizei count, const GLchar * const  *  string, const GLint *  length)`<br></br>
	 * @param string a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
	 * @param length a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glShaderSource1(
		shader: Int,
		count: Int,
		string: Any?,
		string_byte_offset: Int,
		string_is_direct: Boolean,
		length: Any?,
		length_byte_offset: Int,
		length_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glShaderSource(GLuint shader, GLsizei count, const GLchar * const  *  string, const GLint *  length)`<br></br>
	 * @param string a direct or array-backed [com.jogamp.common.nio.PointerBuffer]
	 */
	override fun glShaderSource(
		shader: Int,
		count: Int,
		string: PointerBuffer?,
		length: IntArray?,
		length_offset: Int
	) {
		val string_is_direct: Boolean = Buffers.isDirect(string)
		if (length != null && length.size <= length_offset) throw RuntimeException("array offset argument \"length_offset\" (" + length_offset + ") equals or exceeds array length (" + length.size + ")")
		glShaderSource1(
			shader,
			count,
			if (string_is_direct) (if (string != null) string.getBuffer() else null) else Buffers.getArray(
				string
			),
			if (string_is_direct) Buffers.getDirectBufferByteOffset(string) else Buffers.getIndirectBufferByteOffset(
				string
			),
			string_is_direct,
			length,
			Buffers.SIZEOF_INT * length_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glStencilFunc(GLenum func, GLint ref, GLuint mask)`<br></br>    */
	external override fun glStencilFunc(func: Int, ref: Int, mask: Int)

	/** Interface to C language function: <br></br> `void glStencilFuncSeparate(GLenum face, GLenum func, GLint ref, GLuint mask)`<br></br>    */
	external override fun glStencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int)

	/** Interface to C language function: <br></br> `void glStencilMask(GLuint mask)`<br></br>    */
	external override fun glStencilMask(mask: Int)

	/** Interface to C language function: <br></br> `void glStencilMaskSeparate(GLenum face, GLuint mask)`<br></br>    */
	external override fun glStencilMaskSeparate(face: Int, mask: Int)

	/** Interface to C language function: <br></br> `void glStencilOp(GLenum fail, GLenum zfail, GLenum zpass)`<br></br>    */
	external override fun glStencilOp(fail: Int, zfail: Int, zpass: Int)

	/** Interface to C language function: <br></br> `void glStencilOpSeparate(GLenum face, GLenum sfail, GLenum dpfail, GLenum dppass)`<br></br>    */
	external override fun glStencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int)

	/** Interface to C language function: <br></br> `void glTexImage2D(GLenum target, GLint level, GLint internalformat, GLsizei width, GLsizei height, GLint border, GLenum format, GLenum type, const void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	override fun glTexImage2D(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		format: Int,
		type: Int,
		pixels: Buffer
	) {
		val pixels_is_direct: Boolean = Buffers.isDirect(pixels)
		glTexImage2D1(
			target,
			level,
			internalformat,
			width,
			height,
			border,
			format,
			type,
			if (pixels_is_direct) pixels else Buffers.getArray(pixels),
			if (pixels_is_direct) Buffers.getDirectBufferByteOffset(pixels) else Buffers.getIndirectBufferByteOffset(
				pixels
			),
			pixels_is_direct
		)
	}

	/** Entry point to C language function: `void glTexImage2D(GLenum target, GLint level, GLint internalformat, GLsizei width, GLsizei height, GLint border, GLenum format, GLenum type, const void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glTexImage2D1(
		target: Int,
		level: Int,
		internalformat: Int,
		width: Int,
		height: Int,
		border: Int,
		format: Int,
		type: Int,
		pixels: Any,
		pixels_byte_offset: Int,
		pixels_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glTexParameterf(GLenum target, GLenum pname, GLfloat param)`<br></br>    */
	external override fun glTexParameterf(target: Int, pname: Int, param: Float)

	/** Interface to C language function: <br></br> `void glTexParameterfv(GLenum target, GLenum pname, const GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glTexParameterfv(target: Int, pname: Int, params: FloatBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glTexParameterfv1(
			target,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glTexParameterfv(GLenum target, GLenum pname, const GLfloat *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glTexParameterfv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glTexParameterfv(GLenum target, GLenum pname, const GLfloat *  params)`<br></br>    */
	override fun glTexParameterfv(target: Int, pname: Int, params: FloatArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glTexParameterfv1(target, pname, params, Buffers.SIZEOF_FLOAT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glTexParameteri(GLenum target, GLenum pname, GLint param)`<br></br>    */
	external override fun glTexParameteri(target: Int, pname: Int, param: Int)

	/** Interface to C language function: <br></br> `void glTexParameteriv(GLenum target, GLenum pname, const GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glTexParameteriv(target: Int, pname: Int, params: IntBuffer?) {
		val params_is_direct: Boolean = Buffers.isDirect(params)
		glTexParameteriv1(
			target,
			pname,
			if (params_is_direct) params else Buffers.getArray(params),
			if (params_is_direct) Buffers.getDirectBufferByteOffset(params) else Buffers.getIndirectBufferByteOffset(
				params
			),
			params_is_direct
		)
	}

	/** Entry point to C language function: `void glTexParameteriv(GLenum target, GLenum pname, const GLint *  params)`<br></br>
	 * @param params a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glTexParameteriv1(
		target: Int,
		pname: Int,
		params: Any?,
		params_byte_offset: Int,
		params_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glTexParameteriv(GLenum target, GLenum pname, const GLint *  params)`<br></br>    */
	override fun glTexParameteriv(target: Int, pname: Int, params: IntArray?, params_offset: Int) {
		if (params != null && params.size <= params_offset) throw RuntimeException("array offset argument \"params_offset\" (" + params_offset + ") equals or exceeds array length (" + params.size + ")")
		glTexParameteriv1(target, pname, params, Buffers.SIZEOF_INT * params_offset, false)
	}

	/** Interface to C language function: <br></br> `void glTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLenum type, const void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	override fun glTexSubImage2D(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: Buffer
	) {
		val pixels_is_direct: Boolean = Buffers.isDirect(pixels)
		glTexSubImage2D1(
			target,
			level,
			xoffset,
			yoffset,
			width,
			height,
			format,
			type,
			if (pixels_is_direct) pixels else Buffers.getArray(pixels),
			if (pixels_is_direct) Buffers.getDirectBufferByteOffset(pixels) else Buffers.getIndirectBufferByteOffset(
				pixels
			),
			pixels_is_direct
		)
	}

	/** Entry point to C language function: `void glTexSubImage2D(GLenum target, GLint level, GLint xoffset, GLint yoffset, GLsizei width, GLsizei height, GLenum format, GLenum type, const void *  pixels)`<br></br>
	 * @param pixels a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glTexSubImage2D1(
		target: Int,
		level: Int,
		xoffset: Int,
		yoffset: Int,
		width: Int,
		height: Int,
		format: Int,
		type: Int,
		pixels: Any,
		pixels_byte_offset: Int,
		pixels_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform1f(GLint location, GLfloat v0)`<br></br>    */
	external override fun glUniform1f(location: Int, v0: Float)

	/** Interface to C language function: <br></br> `void glUniform1fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniform1fv(location: Int, count: Int, value: FloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniform1fv1(
			location,
			count,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform1fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniform1fv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform1fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>    */
	override fun glUniform1fv(location: Int, count: Int, value: FloatArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform1fv1(location, count, value, Buffers.SIZEOF_FLOAT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform1i(GLint location, GLint v0)`<br></br>    */
	external override fun glUniform1i(location: Int, v0: Int)

	/** Interface to C language function: <br></br> `void glUniform1iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glUniform1iv(location: Int, count: Int, value: IntBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniform1iv1(
			location,
			count,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform1iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glUniform1iv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform1iv(GLint location, GLsizei count, const GLint *  value)`<br></br>    */
	override fun glUniform1iv(location: Int, count: Int, value: IntArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform1iv1(location, count, value, Buffers.SIZEOF_INT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform2f(GLint location, GLfloat v0, GLfloat v1)`<br></br>    */
	external override fun glUniform2f(location: Int, v0: Float, v1: Float)

	/** Interface to C language function: <br></br> `void glUniform2fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniform2fv(location: Int, count: Int, value: FloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniform2fv1(
			location,
			count,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform2fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniform2fv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform2fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>    */
	override fun glUniform2fv(location: Int, count: Int, value: FloatArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform2fv1(location, count, value, Buffers.SIZEOF_FLOAT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform2i(GLint location, GLint v0, GLint v1)`<br></br>    */
	external override fun glUniform2i(location: Int, v0: Int, v1: Int)

	/** Interface to C language function: <br></br> `void glUniform2iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glUniform2iv(location: Int, count: Int, value: IntBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniform2iv1(
			location,
			count,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform2iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glUniform2iv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform2iv(GLint location, GLsizei count, const GLint *  value)`<br></br>    */
	override fun glUniform2iv(location: Int, count: Int, value: IntArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform2iv1(location, count, value, Buffers.SIZEOF_INT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform3f(GLint location, GLfloat v0, GLfloat v1, GLfloat v2)`<br></br>    */
	external override fun glUniform3f(location: Int, v0: Float, v1: Float, v2: Float)

	/** Interface to C language function: <br></br> `void glUniform3fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniform3fv(location: Int, count: Int, value: FloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniform3fv1(
			location,
			count,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform3fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniform3fv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform3fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>    */
	override fun glUniform3fv(location: Int, count: Int, value: FloatArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform3fv1(location, count, value, Buffers.SIZEOF_FLOAT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform3i(GLint location, GLint v0, GLint v1, GLint v2)`<br></br>    */
	external override fun glUniform3i(location: Int, v0: Int, v1: Int, v2: Int)

	/** Interface to C language function: <br></br> `void glUniform3iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glUniform3iv(location: Int, count: Int, value: IntBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniform3iv1(
			location,
			count,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform3iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glUniform3iv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform3iv(GLint location, GLsizei count, const GLint *  value)`<br></br>    */
	override fun glUniform3iv(location: Int, count: Int, value: IntArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform3iv1(location, count, value, Buffers.SIZEOF_INT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform4f(GLint location, GLfloat v0, GLfloat v1, GLfloat v2, GLfloat v3)`<br></br>    */
	external override fun glUniform4f(location: Int, v0: Float, v1: Float, v2: Float, v3: Float)

	/** Interface to C language function: <br></br> `void glUniform4fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniform4fv(location: Int, count: Int, value: FloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniform4fv1(
			location,
			count,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform4fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniform4fv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform4fv(GLint location, GLsizei count, const GLfloat *  value)`<br></br>    */
	override fun glUniform4fv(location: Int, count: Int, value: FloatArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform4fv1(location, count, value, Buffers.SIZEOF_FLOAT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniform4i(GLint location, GLint v0, GLint v1, GLint v2, GLint v3)`<br></br>    */
	external override fun glUniform4i(location: Int, v0: Int, v1: Int, v2: Int, v3: Int)

	/** Interface to C language function: <br></br> `void glUniform4iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	override fun glUniform4iv(location: Int, count: Int, value: IntBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniform4iv1(
			location,
			count,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniform4iv(GLint location, GLsizei count, const GLint *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.IntBuffer]
	 */
	private external fun glUniform4iv1(
		location: Int,
		count: Int,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniform4iv(GLint location, GLsizei count, const GLint *  value)`<br></br>    */
	override fun glUniform4iv(location: Int, count: Int, value: IntArray?, value_offset: Int) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniform4iv1(location, count, value, Buffers.SIZEOF_INT * value_offset, false)
	}

	/** Interface to C language function: <br></br> `void glUniformMatrix2fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniformMatrix2fv(location: Int, count: Int, transpose: Byte, value: FloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniformMatrix2fv1(
			location,
			count,
			transpose,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniformMatrix2fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniformMatrix2fv1(
		location: Int,
		count: Int,
		transpose: Byte,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniformMatrix2fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>    */
	override fun glUniformMatrix2fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniformMatrix2fv1(
			location,
			count,
			transpose,
			value,
			Buffers.SIZEOF_FLOAT * value_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glUniformMatrix3fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniformMatrix3fv(location: Int, count: Int, transpose: Byte, value: FloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniformMatrix3fv1(
			location,
			count,
			transpose,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniformMatrix3fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniformMatrix3fv1(
		location: Int,
		count: Int,
		transpose: Byte,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniformMatrix3fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>    */
	override fun glUniformMatrix3fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniformMatrix3fv1(
			location,
			count,
			transpose,
			value,
			Buffers.SIZEOF_FLOAT * value_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glUniformMatrix4fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glUniformMatrix4fv(location: Int, count: Int, transpose: Byte, value: FloatBuffer?) {
		val value_is_direct: Boolean = Buffers.isDirect(value)
		glUniformMatrix4fv1(
			location,
			count,
			transpose,
			if (value_is_direct) value else Buffers.getArray(value),
			if (value_is_direct) Buffers.getDirectBufferByteOffset(value) else Buffers.getIndirectBufferByteOffset(
				value
			),
			value_is_direct
		)
	}

	/** Entry point to C language function: `void glUniformMatrix4fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>
	 * @param value a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glUniformMatrix4fv1(
		location: Int,
		count: Int,
		transpose: Byte,
		value: Any?,
		value_byte_offset: Int,
		value_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glUniformMatrix4fv(GLint location, GLsizei count, GLboolean transpose, const GLfloat *  value)`<br></br>    */
	override fun glUniformMatrix4fv(
		location: Int,
		count: Int,
		transpose: Byte,
		value: FloatArray?,
		value_offset: Int
	) {
		if (value != null && value.size <= value_offset) throw RuntimeException("array offset argument \"value_offset\" (" + value_offset + ") equals or exceeds array length (" + value.size + ")")
		glUniformMatrix4fv1(
			location,
			count,
			transpose,
			value,
			Buffers.SIZEOF_FLOAT * value_offset,
			false
		)
	}

	/** Interface to C language function: <br></br> `void glUseProgram(GLuint program)`<br></br>    */
	external override fun glUseProgram(program: Int)

	/** Interface to C language function: <br></br> `void glValidateProgram(GLuint program)`<br></br>    */
	external override fun glValidateProgram(program: Int)

	/** Interface to C language function: <br></br> `void glVertexAttrib1f(GLuint index, GLfloat x)`<br></br>    */
	external override fun glVertexAttrib1f(index: Int, x: Float)

	/** Interface to C language function: <br></br> `void glVertexAttrib1fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glVertexAttrib1fv(index: Int, v: FloatBuffer?) {
		val v_is_direct: Boolean = Buffers.isDirect(v)
		glVertexAttrib1fv1(
			index,
			if (v_is_direct) v else Buffers.getArray(v),
			if (v_is_direct) Buffers.getDirectBufferByteOffset(v) else Buffers.getIndirectBufferByteOffset(
				v
			),
			v_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttrib1fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glVertexAttrib1fv1(
		index: Int,
		v: Any?,
		v_byte_offset: Int,
		v_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glVertexAttrib1fv(GLuint index, const GLfloat *  v)`<br></br>    */
	override fun glVertexAttrib1fv(index: Int, v: FloatArray?, v_offset: Int) {
		if (v != null && v.size <= v_offset) throw RuntimeException("array offset argument \"v_offset\" (" + v_offset + ") equals or exceeds array length (" + v.size + ")")
		glVertexAttrib1fv1(index, v, Buffers.SIZEOF_FLOAT * v_offset, false)
	}

	/** Interface to C language function: <br></br> `void glVertexAttrib2f(GLuint index, GLfloat x, GLfloat y)`<br></br>    */
	external override fun glVertexAttrib2f(index: Int, x: Float, y: Float)

	/** Interface to C language function: <br></br> `void glVertexAttrib2fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glVertexAttrib2fv(index: Int, v: FloatBuffer?) {
		val v_is_direct: Boolean = Buffers.isDirect(v)
		glVertexAttrib2fv1(
			index,
			if (v_is_direct) v else Buffers.getArray(v),
			if (v_is_direct) Buffers.getDirectBufferByteOffset(v) else Buffers.getIndirectBufferByteOffset(
				v
			),
			v_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttrib2fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glVertexAttrib2fv1(
		index: Int,
		v: Any?,
		v_byte_offset: Int,
		v_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glVertexAttrib2fv(GLuint index, const GLfloat *  v)`<br></br>    */
	override fun glVertexAttrib2fv(index: Int, v: FloatArray?, v_offset: Int) {
		if (v != null && v.size <= v_offset) throw RuntimeException("array offset argument \"v_offset\" (" + v_offset + ") equals or exceeds array length (" + v.size + ")")
		glVertexAttrib2fv1(index, v, Buffers.SIZEOF_FLOAT * v_offset, false)
	}

	/** Interface to C language function: <br></br> `void glVertexAttrib3f(GLuint index, GLfloat x, GLfloat y, GLfloat z)`<br></br>    */
	external override fun glVertexAttrib3f(index: Int, x: Float, y: Float, z: Float)

	/** Interface to C language function: <br></br> `void glVertexAttrib3fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glVertexAttrib3fv(index: Int, v: FloatBuffer?) {
		val v_is_direct: Boolean = Buffers.isDirect(v)
		glVertexAttrib3fv1(
			index,
			if (v_is_direct) v else Buffers.getArray(v),
			if (v_is_direct) Buffers.getDirectBufferByteOffset(v) else Buffers.getIndirectBufferByteOffset(
				v
			),
			v_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttrib3fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glVertexAttrib3fv1(
		index: Int,
		v: Any?,
		v_byte_offset: Int,
		v_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glVertexAttrib3fv(GLuint index, const GLfloat *  v)`<br></br>    */
	override fun glVertexAttrib3fv(index: Int, v: FloatArray?, v_offset: Int) {
		if (v != null && v.size <= v_offset) throw RuntimeException("array offset argument \"v_offset\" (" + v_offset + ") equals or exceeds array length (" + v.size + ")")
		glVertexAttrib3fv1(index, v, Buffers.SIZEOF_FLOAT * v_offset, false)
	}

	/** Interface to C language function: <br></br> `void glVertexAttrib4f(GLuint index, GLfloat x, GLfloat y, GLfloat z, GLfloat w)`<br></br>    */
	external override fun glVertexAttrib4f(index: Int, x: Float, y: Float, z: Float, w: Float)

	/** Interface to C language function: <br></br> `void glVertexAttrib4fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	override fun glVertexAttrib4fv(index: Int, v: FloatBuffer?) {
		val v_is_direct: Boolean = Buffers.isDirect(v)
		glVertexAttrib4fv1(
			index,
			if (v_is_direct) v else Buffers.getArray(v),
			if (v_is_direct) Buffers.getDirectBufferByteOffset(v) else Buffers.getIndirectBufferByteOffset(
				v
			),
			v_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttrib4fv(GLuint index, const GLfloat *  v)`<br></br>
	 * @param v a direct or array-backed [java.nio.FloatBuffer]
	 */
	private external fun glVertexAttrib4fv1(
		index: Int,
		v: Any?,
		v_byte_offset: Int,
		v_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glVertexAttrib4fv(GLuint index, const GLfloat *  v)`<br></br>    */
	override fun glVertexAttrib4fv(index: Int, v: FloatArray?, v_offset: Int) {
		if (v != null && v.size <= v_offset) throw RuntimeException("array offset argument \"v_offset\" (" + v_offset + ") equals or exceeds array length (" + v.size + ")")
		glVertexAttrib4fv1(index, v, Buffers.SIZEOF_FLOAT * v_offset, false)
	}

	/** Interface to C language function: <br></br> `void glVertexAttribPointer(GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const void *  pointer)`<br></br>
	 * @param pointer a direct or array-backed [java.nio.Buffer]
	 */
	override fun glVertexAttribPointer(
		index: Int,
		size: Int,
		type: Int,
		normalized: Byte,
		stride: Int,
		pointer: Buffer
	) {
		val pointer_is_direct: Boolean = Buffers.isDirect(pointer)
		glVertexAttribPointer1(
			index,
			size,
			type,
			normalized,
			stride,
			if (pointer_is_direct) pointer else Buffers.getArray(pointer),
			if (pointer_is_direct) Buffers.getDirectBufferByteOffset(pointer) else Buffers.getIndirectBufferByteOffset(
				pointer
			),
			pointer_is_direct
		)
	}

	/** Entry point to C language function: `void glVertexAttribPointer(GLuint index, GLint size, GLenum type, GLboolean normalized, GLsizei stride, const void *  pointer)`<br></br>
	 * @param pointer a direct or array-backed [java.nio.Buffer]
	 */
	private external fun glVertexAttribPointer1(
		index: Int,
		size: Int,
		type: Int,
		normalized: Byte,
		stride: Int,
		pointer: Any,
		pointer_byte_offset: Int,
		pointer_is_direct: Boolean
	)

	/** Interface to C language function: <br></br> `void glViewport(GLint x, GLint y, GLsizei width, GLsizei height)`<br></br>    */
	external override fun glViewport(x: Int, y: Int, width: Int, height: Int)
} // end of class AngleNative


//class AngleNative {
//	@Suppress("UnsafeDynamicallyLoadedCode")
//	companion object {
//		init {
//			val baseDir = System.getProperty("user.dir")
//			val dylibPath = "$baseDir/../../kotlinangle/build/libs/libPrismAngleLibrary.dylib"
//			println("Attempting to load .dylib from: $dylibPath")
//			if (File(dylibPath).exists()) {
//				System.load(dylibPath)
//			} else {
//				throw UnsatisfiedLinkError("Library not found at: $dylibPath")
//			}
//			//System.loadLibrary("PrismAngleLibrary")
//		}
//	}
//
//	external override fun createPrismAngle(): Long
//	external override fun fillScreenRGBAngle(nativePtr: Long, red: Float, green: Float, blue: Float): Int
//	external override fun testCallingAngle(): Int
//	external override fun makeAngleContextCurrent(nativePtr: Long): Int
//}