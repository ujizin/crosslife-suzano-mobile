import Deps.generalDependencies
import Deps.implementAndroidLibs
import Deps.implementAndroidTestLibs
import Deps.implementCompose
import Deps.implementHilt
import Deps.implementLifecycle
import Deps.implementUnitTestLibs

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "br.com.crosslife"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"https://www.google.com/\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Version.COMPOSE
    }
}

dependencies {
    implementCompose()
    implementUnitTestLibs()
    implementAndroidTestLibs()
    implementHilt()
    implementLifecycle()
    implementAndroidLibs()
    generalDependencies()
}
