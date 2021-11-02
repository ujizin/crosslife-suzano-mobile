import Deps.generalDependencies
import Deps.implementAndroidLibs
import Deps.implementAndroidTestLibs
import Deps.implementCompose
import Deps.implementHilt
import Deps.implementLifecycle
import Deps.implementRoom
import Deps.implementUnitTestLibs

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "br.com.crosslife"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "BASE_URL", "\"https://crosslifeapi.herokuapp.com/\"")
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
    }

//    buildFeatures {
//        compose = true
//    }
//
//    composeOptions {
//        kotlinCompilerExtensionVersion = Deps.Version.COMPOSE
//    }
}
