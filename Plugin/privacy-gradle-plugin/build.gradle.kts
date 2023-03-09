import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("kotlin")
    id("java-gradle-plugin")
    id("kotlin-kapt")
    id("maven-publish")
}

val aliyun_maven_url: String = gradleLocalProperties(rootDir).getProperty("aliyun.maven.url")
val aliyun_maven_snapshotUrl: String =
    gradleLocalProperties(rootDir).getProperty("aliyun.maven.snapshotUrl")
val aliyun_maven_userName: String =
    gradleLocalProperties(rootDir).getProperty("aliyun.maven.userName")
val aliyun_maven_password: String = gradleLocalProperties(rootDir).getProperty("aliyun.maven.password")

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())
    implementation("org.ow2.asm:asm:9.2")
    implementation("org.ow2.asm:asm-tree:9.2")
    implementation("com.android.tools.build:gradle-api:7.2.1")
    implementation("commons-io:commons-io:2.11.0")
    implementation("com.google.auto.service:auto-service:1.0.1")
    kapt("com.google.auto.service:auto-service:1.0.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

gradlePlugin {
    plugins {
        this.create("version") {
            id = "privacy-hook"
            implementationClass = "org.c2h4.plugin.privacy.PrivacyHookPlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            val mavenUrl = when {
                version.toString()
                    .endsWith("SNAPSHOT") -> aliyun_maven_snapshotUrl
                else -> aliyun_maven_url
            }
            url = uri(mavenUrl)
            credentials {
                username = aliyun_maven_userName
                password = aliyun_maven_password
            }
        }
    }
}
