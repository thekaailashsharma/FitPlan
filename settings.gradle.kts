pluginManagement {
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

rootProject.name = "Fitplan"
include(":app")
include(":feature:onboarding:presentation")
include(":theme")
include(":feature:onboarding:data")
include(":core:firebase")
include(":feature:signin:presentation")
include(":baseui")
include(":core:navigation")
