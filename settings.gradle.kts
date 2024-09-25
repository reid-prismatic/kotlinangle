rootProject.name = "KotlinAngle"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven("https://jogamp.org/deployment/maven") // For JOGL
        maven {
            url = uri("https://maven.pkg.github.com/theprismaticcompany/opus-math")
            credentials {
                username = settings.extra.properties["gpr.user"] as String? ?: System.getenv("USERNAME")
                password = settings.extra.properties["gpr.key"] as String? ?: System.getenv("TOKEN")
            }
        }
        maven {
            url = uri("https://maven.pkg.github.com/theprismaticcompany/opus")
            credentials {
                username = settings.extra.properties["gpr.user"] as String? ?: System.getenv("USERNAME")
                password = settings.extra.properties["gpr.key"] as String? ?: System.getenv("TOKEN")
            }
        }
        mavenLocal()
    }
}

include(":sample:composeApp")
include(":sample:server")
include(":sample:shared")
include(":kotlinangle")

