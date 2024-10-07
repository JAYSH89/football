pluginManagement {
    includeBuild("build-logic")
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

rootProject.name = "football"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:data")
include(":core:designsystem")
include(":core:network")
include(":core:ui")
include(":feature:competition")
include(":feature:standing")
include(":feature:team")
include(":core:common")
include(":core:datastore")
include(":core:testing")
include(":core:domain")
