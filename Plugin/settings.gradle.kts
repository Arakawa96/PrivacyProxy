//include ':double_tap_plugin'
//include ':AutoTrackPlugin'

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://repo1.maven.org/maven2/") }
        google()
        maven {
            url = uri("http://mvn.mob.com/android")
            isAllowInsecureProtocol = true
        }
        maven { url = uri("https://jitpack.io") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        gradlePluginPortal()
        maven(url = "https://androidx.dev/snapshots/builds/8455591/artifacts/repository") {
            content {
                // The AndroidX snapshot repository will only have androidx artifacts, don't
                // bother trying to find other ones
                includeGroupByRegex("androidx\\..*")
            }
        }
        mavenCentral()
        maven { url = uri("https://4thline.org/m2") }
        maven { url = uri("https://repo1.maven.org/maven2/") }
        maven { url = uri("https://jitpack.io") }
        maven {
            url = uri("http://maven.youzanyun.com/repository/maven-releases")
            isAllowInsecureProtocol = true
        }
    }
}

//include(":base-gradle-plugin")
include(":privacy-gradle-plugin")

rootProject.name = "Plugin"

