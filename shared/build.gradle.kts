plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    //for deserializing JSON responses into objects of entity classes used to process network operations.
    kotlin("plugin.serialization") version "1.9.21"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here

            // date time api to retrieve the system date
            implementation(libs.kotlinx.datetime)

            // Courotines for write asynchronous code
            implementation(libs.kotlinx.coroutines.core)

            // Ktor stuffs

            // Ktor Client
            implementation(libs.ktor.client.core)
            // responsible for serializing/deserializing the content in a specific format.
            implementation(libs.ktor.client.content.negotiation)
            // Instruct Ktor to use the JSON format and kotlinx.serialization as a serialization library.
            implementation(libs.ktor.serialization.kotlinx.json)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.codeplace.myfirstkmmapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}
