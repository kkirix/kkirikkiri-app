import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("kotlin-parcelize")
}

version = "1.0"

kotlin {
    android()
    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }
    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            export(deps.decompose.decompose)
            linkerOpts.add("-lsqlite3")
            transitiveExport = true
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(deps.kotlinx.coroutines)
                implementation(deps.kotlinx.serialization.json)
                implementation(deps.koin.core)
                implementation(deps.bundles.ktor)
                implementation(deps.bundles.mviKotlin)
                api(deps.bundles.decompose)
            }
        }
        getByName("androidMain").dependencies {
            implementation(deps.bundles.compose)
            implementation(deps.ktor.okHttp)
        }
        getByName("iosMain") {
            dependsOn(commonMain)
            dependencies {
                implementation(deps.ktor.darwin)
            }
        }
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 28
        targetSdk = 33
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = deps.versions.composeCompiler.get()
    }
}

configurations {
    create("composeCompiler") {
        isCanBeConsumed = false
    }
}

dependencies {
    add("composeCompiler", "androidx.compose.compiler:compiler:${deps.versions.composeCompiler.get()}")
}

afterEvaluate {
    val composeCompilerJar = project.configurations
        .getByName("composeCompiler")
        .resolve()
        .firstOrNull()
        ?: throw Exception("Please add \"androidx.compose.compiler:compiler\" (and only that) as a \"composeCompiler\" dependency")

    project.tasks.withType<KotlinCompile> {
        kotlinOptions.freeCompilerArgs += listOf("-Xuse-ir", "-Xplugin=$composeCompilerJar")
    }
}
