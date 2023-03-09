import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
plugins {
    id("com.android.library")
    id("kotlin-android")
    id("maven-publish")
}
val aliyun_maven_url: String = gradleLocalProperties(rootDir).getProperty("aliyun.maven.url")
val aliyun_maven_snapshotUrl: String = gradleLocalProperties(rootDir).getProperty("aliyun.maven.snapshotUrl")
val aliyun_maven_userName: String = gradleLocalProperties(rootDir).getProperty("aliyun.maven.userName")
val aliyun_maven_password: String = gradleLocalProperties(rootDir).getProperty("aliyun.maven.password")


android {
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles = "consumer-rules.pro"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.tencent:mmkv-static:1.2.13")
    implementation("com.blankj:utilcodex:1.31.0")
}

group = "org.c2h4"
version = "1.1.2-SNAPSHOT"

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
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "org.c2h4"
            artifactId = "privacy-proxy"
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}