import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

object Deps {

    object Version {
        // Compose
        const val COMPOSE = "1.0.0-beta09"
        const val ACTIVITY_COMPOSE = "1.3.0-alpha07"
        const val NAVIGATION_COMPOSE = "2.4.0-alpha01"

        // Hilt
        const val HILT = "2.36"

        // Android
        const val ANDROID_CORE = "1.3.2"
        const val ANDROID_APP_COMPAT = "1.2.0"
        const val ANDROID_MATERIAL = "1.3.0"

        // Android Compose Lifecycle

        const val COMPOSE_LIFECYCLE = "1.0.0-alpha04"

        // Retrofit
        const val RETROFIT = "2.9.0"

        // Room
        const val ROOM = "2.3.0"

        // Espresso
        const val ESPRESSO = "3.3.0"

        // jUnit
        const val ANDROID_TEST_JUNIT = "1.1.2"
        const val UNIT_TEST_JUNIT = "4.13.2"
    }

    private fun DependencyHandler.implementation(name: String) {
        add("implementation", name)
    }

    private fun DependencyHandler.testImplementation(name: String) {
        add("testImplementation", name)
    }

    private fun DependencyHandler.androidTestImplementation(name: String) {
        add("androidTestImplementation", name)
    }

    private fun DependencyHandler.kapt(name: String) {
        add("kapt", name)
    }

    private fun DependencyHandler.annotationProcessor(name: String) {
        add("annotationProcessor", name)
    }

    fun DependencyHandler.implementCompose() {
        implementation("androidx.compose.ui:ui-tooling:${Version.COMPOSE}")
        implementation("androidx.compose.material:material:${Version.COMPOSE}")
        implementation("androidx.compose.ui:ui:${Version.COMPOSE}")
        implementation("androidx.compose.runtime:runtime:${Version.COMPOSE}")
        implementation("androidx.activity:activity-compose:${Version.ACTIVITY_COMPOSE}")
        implementation("androidx.navigation:navigation-compose:${Version.NAVIGATION_COMPOSE}")
        implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha02")
        implementation("androidx.compose.material:material-icons-extended:${Version.COMPOSE}")
    }

    fun DependencyHandler.implementUnitTestLibs() {
        testImplementation("junit:junit:${Version.UNIT_TEST_JUNIT}")
    }

    fun DependencyHandler.implementAndroidTestLibs() {
        androidTestImplementation("androidx.test.ext:junit:${Version.ANDROID_TEST_JUNIT}")
        androidTestImplementation("androidx.test.espresso:espresso-core:${Version.ESPRESSO}")
    }

    fun DependencyHandler.implementAndroidLibs() {
        implementation("androidx.core:core-ktx:${Version.ANDROID_CORE}")
        implementation("androidx.appcompat:appcompat:${Version.ANDROID_APP_COMPAT}")
        implementation("com.google.android.material:material:${Version.ANDROID_MATERIAL}")
    }

    fun DependencyHandler.implementHilt() {
        implementation("com.google.dagger:hilt-android:${Version.HILT}")
        kapt("com.google.dagger:hilt-android-compiler:${Version.HILT}")
        kapt("com.google.dagger:hilt-compiler:${Version.HILT}")
        implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
        kapt("androidx.hilt:hilt-compiler:1.0.0")
    }

    fun DependencyHandler.implementLifecycle() {
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${Version.COMPOSE_LIFECYCLE}")
    }

    fun DependencyHandlerScope.implementRoom() {
        implementation("androidx.room:room-runtime:${Version.ROOM}")
        kapt("androidx.room:room-compiler:${Version.ROOM}")
    }

    fun DependencyHandlerScope.generalDependencies() {
        implementation("androidx.datastore:datastore-preferences:1.0.0-beta01")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")
        implementation("com.google.accompanist:accompanist-pager:0.13.0")
        implementation("com.squareup.retrofit2:retrofit:${Version.RETROFIT}")
        implementation("com.squareup.retrofit2:converter-moshi:${Version.RETROFIT}")
    }
}
