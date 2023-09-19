plugins {
    kotlin("plugin.serialization") version "1.8.21"
    kotlin("multiplatform") version "1.8.21"
//    kotlin("native.cocoapods") version "1.8.21"
    id("com.android.library")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
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
        }
    }

    val ktorVersion = "2.3.2"
    val koinVersion = "3.4.2"

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("io.insert-koin:koin-core:${koinVersion}")
                api("dev.icerock.moko:mvvm-core:0.13.1")
//                implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
//                implementation ("org.jetbrains.kotlin:kotlin-serialization-compiler-plugin-embeddable:1.5.21")


            }
        }
        val androidMain by getting {
            dependencies {
            }
        }
        val iosMain by getting {
            dependencies {

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.kotlinmultiplatformsandbox"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
}