dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Cross Life"

include(":app")
include(":network")
include(":domain")
include(":data")
include(":commons")
include(":navigation")
