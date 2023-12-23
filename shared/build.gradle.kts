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
    val ktorVersion = "2.3.5"

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here

            // date time api
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

            // Courotines for write asynchronous code
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

            // Ktor stuffs

            // Ktor Client
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            // responsible for serializing/deserializing the content in a specific format.
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            // Instruct Ktor to use the JSON format and kotlinx.serialization as a serialization library.
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
        }

        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:$ktorVersion")
        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:$ktorVersion")
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
