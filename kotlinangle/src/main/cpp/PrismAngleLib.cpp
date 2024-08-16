#include <iostream>
#include "PrismAngleLib.h"

PrismAngleLib::PrismAngleLib() {
    printf("initialized PrismAngleLib");
}


int PrismAngleLib::fillScreenRGBAngle(float red, float green, float blue) {
    glClearColor(red, green, blue, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);
    return 0;
}

int PrismAngleLib::testCallingAngle() {
    // Vertex shader source code
    const char* vertexShaderSource = R"(
        attribute vec4 a_position;
        void main() {
            gl_Position = a_position;
        }
    )";

    // Fragment shader source code
    const char* fragmentShaderSource = R"(
        precision mediump float;
        void main() {
            gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0); // Red color
        }
    )";

    // Initialize EGL
    EGLDisplay display = eglGetDisplay(EGL_DEFAULT_DISPLAY);
    if (display == EGL_NO_DISPLAY) {
        std::cerr << "Unable to open connection to local windowing system" << std::endl;
        return -1;
    }

    if (!eglInitialize(display, NULL, NULL)) {
        std::cerr << "Unable to initialize EGL" << std::endl;
        return -1;
    }

    // Set EGL configuration
    EGLint configAttribs[] = {
        EGL_SURFACE_TYPE, EGL_PBUFFER_BIT,
        EGL_RENDERABLE_TYPE, EGL_OPENGL_ES2_BIT,
        EGL_NONE
    };
    EGLConfig config;
    EGLint numConfigs;
    if (!eglChooseConfig(display, configAttribs, &config, 1, &numConfigs)) {
        std::cerr << "Failed to choose config" << std::endl;
        return -1;
    }

    // Create a surface
    EGLint surfaceAttribs[] = {
        EGL_WIDTH, 128,
        EGL_HEIGHT, 128,
        EGL_NONE
    };
    EGLSurface surface = eglCreatePbufferSurface(display, config, surfaceAttribs);
    if (surface == EGL_NO_SURFACE) {
        std::cerr << "Unable to create EGL surface" << std::endl;
        return -1;
    }

    // Bind OpenGL ES API
    if (!eglBindAPI(EGL_OPENGL_ES_API)) {
        std::cerr << "Failed to bind OpenGL ES API" << std::endl;
        return -1;
    }

    // Create a context
    EGLint contextAttribs[] = {
        EGL_CONTEXT_CLIENT_VERSION, 2,
        EGL_NONE
    };
    EGLContext context = eglCreateContext(display, config, EGL_NO_CONTEXT, contextAttribs);
    if (context == EGL_NO_CONTEXT) {
        std::cerr << "Unable to create EGL context" << std::endl;
        return -1;
    }

    // Make the context current
    if (!eglMakeCurrent(display, surface, surface, context)) {
        std::cerr << "Unable to make EGL context current" << std::endl;
        return -1;
    }

    // Load and compile shaders
    GLuint vertexShader = LoadShader(GL_VERTEX_SHADER, vertexShaderSource);
    GLuint fragmentShader = LoadShader(GL_FRAGMENT_SHADER, fragmentShaderSource);

    // Create and link program
    GLuint program = glCreateProgram();
    glAttachShader(program, vertexShader);
    glAttachShader(program, fragmentShader);
    glLinkProgram(program);

    GLint linked;
    glGetProgramiv(program, GL_LINK_STATUS, &linked);
    if (!linked) {
        GLint infoLen = 0;
        glGetProgramiv(program, GL_INFO_LOG_LENGTH, &infoLen);
        if (infoLen > 1) {
            char *infoLog = (char*)malloc(infoLen);
            glGetProgramInfoLog(program, infoLen, NULL, infoLog);
            std::cerr << "Error linking program:\n" << infoLog << std::endl;
            free(infoLog);
        }
        glDeleteProgram(program);
        return -1;
    }

    // Use the program
    glUseProgram(program);

    // Set up vertex data
    GLfloat vertices[] = {
        0.0f,  0.5f,
       -0.5f, -0.5f,
        0.5f, -0.5f,
    };

    GLuint positionLoc = glGetAttribLocation(program, "a_position");
    glVertexAttribPointer(positionLoc, 2, GL_FLOAT, GL_FALSE, 0, vertices);
    glEnableVertexAttribArray(positionLoc);

    // Clear the color buffer and draw the triangle
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);
    glDrawArrays(GL_TRIANGLES, 0, 3);

    // Swap buffers
    eglSwapBuffers(display, surface);

    // Clean up
    glDeleteProgram(program);
    glDeleteShader(vertexShader);
    glDeleteShader(fragmentShader);
    eglDestroySurface(display, surface);
    eglDestroyContext(display, context);
    eglTerminate(display);

    printf("Done!!");

    return 30;
}

GLuint PrismAngleLib::LoadShader(GLenum type, const char *shaderSrc) {
    GLuint shader = glCreateShader(type);
    glShaderSource(shader, 1, &shaderSrc, NULL);
    glCompileShader(shader);

    GLint compiled;
    glGetShaderiv(shader, GL_COMPILE_STATUS, &compiled);
    if (!compiled) {
        GLint infoLen = 0;
        glGetShaderiv(shader, GL_INFO_LOG_LENGTH, &infoLen);
        if (infoLen > 1) {
            char *infoLog = (char*)malloc(infoLen);
            glGetShaderInfoLog(shader, infoLen, NULL, infoLog);
            std::cerr << "Error compiling shader:\n" << infoLog << std::endl;
            free(infoLog);
        }
        glDeleteShader(shader);
        return 0;
    }
    return shader;
}