pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://artifactory.rakuten-it.com/mobile-libs-mvn-release")
        }
    }
}

rootProject.name = "My Application"
include(":app")
include(":mypoc")
include(":mylibrary")
include(":basicviews")
include(":bottomnavigationviews")
include(":emptyviews")
include(":empty")
include(":gogleadmobadsviews")
include(":googlemapsviews")
include(":googlepayviews")
include(":googlewallet")
include(":loginviews")
include(":primarydetailsviews")
include(":navigationdrawerviews")
include(":responsiveviews")
include(":settingsviews")
include(":settingsviews2")
include(":scrollingviews")
include(":tabbedviews")
include(":fragmentviewmodel")
include(":fullscreenviews")
include(":myapp")
