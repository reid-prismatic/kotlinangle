//
// Created by Reid Byun on 2024/08/14.
//

#ifndef PRISMANGLE_NATIVE_LIBRARY_H
#define PRISMANGLE_NATIVE_LIBRARY_H

#include <EGL/egl.h>
#include <GLES2/gl2.h>

class PrismAngleLib {
public:
    explicit PrismAngleLib(long windowHandle);
    virtual ~PrismAngleLib();

    int fillScreenRGBAngle(float red, float green, float blue);
    int testCallingAngle();
    int makeAngleContextCurrent();

private:
    GLuint LoadShader(GLenum type, const char *shaderSrc);

    int initAngle(long windowHandle);

    EGLDisplay display;
    EGLContext context;
    EGLSurface surface;
};

#endif //PRISMANGLE_NATIVE_LIBRARY_H
