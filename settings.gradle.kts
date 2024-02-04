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

rootProject.name = "OnlineStore"
include(":app")
include(":core")
include(":local_data")
include(":remote_data")
include(":auth_screen")
include(":catalogue_screen")
include(":product_card_screen")
include(":account_profile_screen")
include(":favourite_screen")
