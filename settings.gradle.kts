pluginManagement {
    includeBuild("build-config")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Guardian"

include(":app")
include(":core:common")
include(":core:designsystem")
include(":core:domain")
include(":core:data:datastore")
include(":core:data:repository")
include(":core:data:datasource")
include(":core:data:network")
include(":core:test")
include(":core:ui")
include(":feature:login")
include(":feature:settings")
include(":feature:reports")
include(":feature:device:common")
include(":feature:device:garage")
include(":feature:device:management")
include(":app-ds-catalog")
