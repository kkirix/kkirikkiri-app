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
    ios()

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
                implementation(deps.kotlinx.coroutines)
                implementation(deps.kotlinx.serialization.json)
                implementation(deps.koin.core)
                implementation(deps.bundles.ktor)
                implementation(deps.bundles.mviKotlin)
                implementation(deps.bundles.decompose)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(deps.bundles.compose)
                implementation(deps.ktor.okHttp)
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