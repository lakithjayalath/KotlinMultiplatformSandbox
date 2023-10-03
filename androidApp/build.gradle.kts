plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.example.kotlinmultiplatformsandbox.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.example.kotlinmultiplatformsandbox.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

val koinVersion = "3.3.3"
val retroVersion = "2.9.0"
val okhttpVersion = "5.0.0-alpha.3"

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    val ktorVersion = "2.3.2"
    implementation ("io.ktor:ktor-client-okhttp:$ktorVersion")
    val koinVersion = "3.5.0"
//    implementation("io.insert-koin:koin-core:${koinVersion}")
//    implementation("io.insert-koin:koin-android:${koinVersion}")
//    implementation("io.insert-koin:koin-androidx-compose:3.4.2")
//    val koinComposeVersion = "3.4.3"
//    implementation("io.insert-koin:koin-androidx-compose:$koinComposeVersion")

    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    val koinComposeVersion = "3.4.3"
    implementation("io.insert-koin:koin-androidx-navigation:$koinComposeVersion")
    implementation("io.insert-koin:koin-androidx-compose:$koinComposeVersion")
    implementation("io.insert-koin:koin-test-junit4:$koinComposeVersion")
// Retrofit
    implementation("com.squareup.retrofit2:retrofit:${retroVersion}")
    implementation("com.squareup.retrofit2:converter-moshi:${retroVersion}")
    implementation("com.squareup.okhttp3:okhttp:${okhttpVersion}")
}