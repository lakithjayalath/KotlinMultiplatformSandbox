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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.4.3")
    implementation("androidx.compose.ui:ui-tooling:1.4.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")
    implementation("androidx.compose.foundation:foundation:1.4.3")
    implementation("androidx.compose.material:material:1.4.3")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.compose.material3:material3:1.1.1")
    val ktorVersion = "2.3.2"
    implementation ("io.ktor:ktor-client-okhttp:$ktorVersion")
    val koinVersion = "3.4.2"
    implementation("io.insert-koin:koin-core:${koinVersion}")
    implementation("io.insert-koin:koin-android:${koinVersion}")
}