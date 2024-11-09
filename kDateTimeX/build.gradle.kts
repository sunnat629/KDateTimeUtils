import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig
import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
//    id("maven-publish")
//    id("signing")
    id("com.vanniktech.maven.publish") version "0.30.0"
}

kotlin {

    // Configure Android target
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
        publishLibraryVariants("release")
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "KDateTimeX"
            isStatic = true
        }
    }

    jvm()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
            testTask {
                useKarma {
                    useFirefox()
                }
            }
        }
        nodejs()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization)
//            implementation(libs.koin.core)
//            implementation(libs.logback.classic)
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test")) // Kotlin Test framework for common tests
                implementation(libs.kotlinx.coroutines.test) // For testing coroutines
            }
        }
    }
}

android {
    namespace = "dev.sunnat629.kDateTimeX"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }

    val keystorePropertiesFile = rootProject.file("gradle.properties")
    val keystoreProperties = Properties().apply {
        load(FileInputStream(keystorePropertiesFile))
    }
        signingConfigs {
            create("config") {
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
            }
        }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("config")
        }
    }
}

group = "dev.sunnat629"
version = "1.0.1"

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL) // Use SonatypeHost.S01 if your account uses s01.oss.sonatype.org
    signAllPublications()
    pom {
        name.set("kDateTimeX")
        description.set("A Kotlin Multiplatform library for date and time utilities.")
        url.set("https://github.com/sunnat629/KDateTimeUtils")
        inceptionYear.set("2024")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("sunnat629")
                name.set("Mohi us Sunnat")
                email.set("suncha629@gmail.com")
                url.set("https://github.com/sunnat629")
            }
        }

        scm {
            connection.set("scm:git:https://github.com/sunnat629/KDateTimeUtils.git")
            developerConnection.set("scm:git:ssh://github.com/sunnat629/KDateTimeUtils.git")
            url.set("https://github.com/sunnat629/KDateTimeUtils")
        }
    }
}