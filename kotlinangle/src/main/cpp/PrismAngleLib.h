//
// Created by Reid Byun on 2024/08/14.
//

#ifndef PRISMANGLE_NATIVE_LIBRARY_H
#define PRISMANGLE_NATIVE_LIBRARY_H

#include <EGL/egl.h>
#include <GLES2/gl2.h>

class PrismAngleLib {
public:
    explicit PrismAngleLib();
    virtual ~PrismAngleLib() = default;

    int fillScreenRGBAngle(float red, float green, float blue);
    int testCallingAngle();

private:
    GLuint LoadShader(GLenum type, const char *shaderSrc);
};

#endif //PRISMANGLE_NATIVE_LIBRARY_H
