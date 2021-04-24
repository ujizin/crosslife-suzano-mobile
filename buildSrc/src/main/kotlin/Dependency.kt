import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope

object Dependency {

    object Version {
        // Compose
        const val COMPOSE = "1.0.0-beta03"
        const val ACTIVITY_COMPOSE = "1.3.0-alpha07"
        const val NAVIGATION_COMPOSE = "1.0.0-alpha10"

        // Koin
        const val KOIN = "3.0.1"

        // Android
        const val ANDROID_CORE = "1.3.2"
        const val ANDROID_LIFECYCLE = "2.3.1"
        const val ANDROID_APP_COMPAT = "1.2.0"
        const val ANDROID_MATERIAL = "1.3.0"

        // Espresso
        const val ESPRESSO = "3.3.0"

        // jUnit
        const val ANDROID_TEST_JUNIT = "1.1.2"
        const val UNIT_TEST_JUNIT = "4.13.2"
    }

    private fun DependencyHandler.implementCompose() {
        implementation("androidx.compose.ui:ui-tooling:${Version.COMPOSE}")
        implementation("androidx.compose.material:material:${Version.COMPOSE}")
        implementation("androidx.compose.ui:ui:${Version.COMPOSE}")
        implementation("io.insert-koin:koin-androidx-compose:${Version.KOIN}")
        implementation("androidx.activity:activity-compose:${Version.ACTIVITY_COMPOSE}")
        implementation("androidx.navigation:navigation-compose:${Version.NAVIGATION_COMPOSE}")
    }

    private fun DependencyHandler.implementUnitTestLibs() {
        testImplementation("junit:junit:${Version.UNIT_TEST_JUNIT}")
    }

    private fun DependencyHandler.implementAndroidTestLibs() {
        androidTestImplementation("androidx.test.ext:junit:${Version.ANDROID_TEST_JUNIT}")
        androidTestImplementation("androidx.test.espresso:espresso-core:${Version.ESPRESSO}")
    }

    private fun DependencyHandler.implementAndroidLibs() {
        implementation("androidx.core:core-ktx:${Version.ANDROID_CORE}")
        implementation("androidx.appcompat:appcompat:${Version.ANDROID_APP_COMPAT}")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:${Version.ANDROID_LIFECYCLE}")
        implementation("com.google.android.material:material:${Version.ANDROID_MATERIAL}")
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

    fun DependencyHandlerScope.initImplementation(block: ImplementationBuilder.() -> Unit) {
        val builder = ImplementationBuilder()
        block.invoke(builder)
        builder.run {
            if (compose) implementCompose()
            if (unitTest) implementUnitTestLibs()
            if (androidTest) implementAndroidTestLibs()
            implementAndroidLibs()
        }
    }

    data class ImplementationBuilder(
        var compose: Boolean = false,
        var unitTest: Boolean = false,
        var androidTest: Boolean = false,
    )
}
