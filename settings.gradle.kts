rootProject.name = "km_flow1000_admin"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {

    repositories {
        maven {
            url=uri ("https://maven.aliyun.com/repository/public/")
        }
        maven{
            url=uri ("https://maven.aliyun.com/repository/central")
        }
        maven{
            url=uri ("https://maven.aliyun.com/repository/gradle-plugin")
        }
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
        maven {
            url=uri ("https://maven.aliyun.com/repository/public/")
        }
        maven{
            url=uri ("https://maven.aliyun.com/repository/central")
        }
        maven{
            url=uri ("https://maven.aliyun.com/repository/gradle-plugin")
        }
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")