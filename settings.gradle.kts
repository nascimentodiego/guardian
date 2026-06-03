pluginManagement {
    includeBuild("build-config")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

buildCache {
    local {
        isEnabled = true
    }
    remote<HttpBuildCache> {
        url = uri("https://gradle-cache-worker.guardianapp.workers.dev/cache/")
        isEnabled = System.getenv("CI") != null
        isPush = System.getenv("GRADLE_CACHE_PUSH").toBoolean()
        credentials {
            username = System.getenv("GRADLE_CACHE_USER") ?: ""
            password = System.getenv("GRADLE_CACHE_PASS") ?: ""
        }
        isAllowUntrustedServer = false
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
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
include(":feature:registration")
include(":feature:settings")
include(":feature:reports")
include(":feature:device:common")
include(":feature:device:garage")
include(":feature:device:management")
include(":appdscatalog")
