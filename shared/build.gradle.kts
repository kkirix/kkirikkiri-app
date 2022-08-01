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

    fun nativeTargetConfig(): KotlinNativeTarget.() -> Unit = {
        val nativeFrameworkPaths = projectDir.resolve("src/nativeInterop/cinterop")

        compilations.getByName("main") {
            cinterops.create("NaverThirdPartyLogin") {
                compilerOpts("-F$nativeFrameworkPaths")
            }
        }
    }
    ios(configure = nativeTargetConfig())

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(deps.koin.core)
                implementation(deps.kotlinx.coroutines)
                implementation(deps.decompose.decompose)
                implementation(deps.kotlinx.serialization.json)
                implementation(deps.bundles.ktor)
                implementation(deps.bundles.mviKotlin)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(deps.ktor.okHttp)
                implementation(deps.bundles.compose)
                implementation(deps.decompose.extension.compose)
            }
        }
        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(deps.ktor.darwin)
            }
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 28
        targetSdk = 32
    }
}