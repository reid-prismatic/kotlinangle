
# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

project(PrismAngleLibrary)

set(CMAKE_CXX_STANDARD 11)
set(CMAKE_CXX_STANDARD_REQUIRED True)
set(source_dir ${CMAKE_CURRENT_SOURCE_DIR})

find_package(Java REQUIRED)
find_package(JNI REQUIRED)

# Common include directories
include_directories(
    ${source_dir}
    ${source_dir}/jni
    ${source_dir}/include
    ${source_dir}/include/EGL
    ${source_dir}/include/GLES2
    ${CMAKE_SOURCE_DIR}/src/main/cpp
    ${JNI_INCLUDE_DIRS}
)

# macOS
if(${CMAKE_SYSTEM_NAME} STREQUAL "Darwin")
    include_directories(${JNI_INCLUDE_DIRS}/darwin)
    file(GLOB LIBRARIES "${source_dir}/jniLibs/macOS/*.dylib")

# Windows
elseif(${CMAKE_SYSTEM_NAME} STREQUAL "Windows")
    include_directories(${JNI_INCLUDE_DIRS}/win32)
    file(GLOB LIBRARIES "${source_dir}/jniLibs/windows/*.dll")

# Linux
elseif(${CMAKE_SYSTEM_NAME} STREQUAL "Linux")
    file(GLOB LIBRARIES "${source_dir}/jniLibs/linux/*.so")

# Android
elseif(${CMAKE_SYSTEM_NAME} STREQUAL "Android")
    file(GLOB LIBRARIES "${source_dir}/jniLibs/android/*.so")

# iOS
elseif(${CMAKE_SYSTEM_NAME} STREQUAL "iOS")
    file(GLOB LIBRARIES "${source_dir}/jniLibs/ios/*.dylib")
endif()

# Common source files
file(GLOB SOURCES "${source_dir}/*.cpp")
file(GLOB JNI_NATIVE_SOURCES "${source_dir}/jni/*.cpp" "${source_dir}/jni/*.c")

# Define the library
add_library(${CMAKE_PROJECT_NAME} SHARED 
    ${JNI_NATIVE_SOURCES}
    ${SOURCES}    
)

# Link libraries
target_link_libraries(${CMAKE_PROJECT_NAME}
    ${LIBRARIES}
    # ${EGL_LIBRARY}
    # ${GLESV2_LIBRARY}
)

# Set target properties
set_target_properties(PrismAngleLibrary PROPERTIES
    BUILD_WITH_INSTALL_RPATH TRUE
    INSTALL_RPATH "@loader_path"
)