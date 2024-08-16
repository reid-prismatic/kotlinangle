#include <jni.h>
#include <PrismAngleNative.h>
#include <PrismAngleLib.h>

extern "C" JNIEXPORT jint JNICALL 
Java_org_prismatic_kotlinangle_AngleNative_testCallingAngle(JNIEnv *env, jobject obj) {
    auto* ctx = new PrismAngleLib();
    int result = ctx->testCallingAngle();
    delete ctx;
    return result;
}

extern "C" JNIEXPORT jlong JNICALL Java_org_prismatic_kotlinangle_AngleNative_createPrismAngle(
    JNIEnv *env, 
    jobject obj
    ) {
        auto* ctx = new PrismAngleLib();
        return (jlong) ctx;
    }

extern "C" JNIEXPORT jlong JNICALL Java_org_prismatic_kotlinangle_AngleNative_fillScreenRGBAngle(
    JNIEnv *env, 
    jobject obj, 
    jlong native_ptr, 
    jfloat red, 
    jfloat green, 
    jfloat blue) {

        auto* ctx = reinterpret_cast<PrismAngleLib*>(native_ptr);
        ctx->fillScreenRGBAngle(red, green, blue);
        return 0;
    }

extern "C" JNIEXPORT jint JNICALL
Java_org_prismatic_kotlinangle_AngleNative_makeAngleContextCurrent(JNIEnv* env, jobject obj, jlong native_ptr) {
    auto* ctx = reinterpret_cast<PrismAngleLib*>(native_ptr);
    return ctx->makeAngleContextCurrent();
}