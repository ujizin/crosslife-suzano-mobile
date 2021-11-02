import Deps.implementRetrofit

plugins {
    id("com.android.library")
    id("kotlin-android")
}

apply("../config-android")

dependencies {
    implementRetrofit()
}