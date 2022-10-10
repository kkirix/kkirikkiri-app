plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
}

version = "1.0"

kotlin {
    android()
    listOf(iosX64("uikitX64"), iosArm64("uikitArm64")).forEach {
        val frameworkPath = projectDir.resolve("src/nativeInterop/cinterop")
        it.compilations.all {
            cinterops.create("NaverLogin").compilerOpts("-F$frameworkPath")
        }
        it.binaries {
            executable {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
                if (it.name == "uikitArm64") {
                    freeCompilerArgs += "-Xdisable-phases=VerifyBitcode"
                }
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.material)
                implementation(compose.foundation)
            }
        }
        val uikitMain by creating {
            dependsOn(commonMain)
        }
        getByName("androidMain").dependencies {
            api("com.navercorp.nid:oauth:5.1.1")
        }
        getByName("uikitX64Main").dependsOn(uikitMain)
        getByName("uikitArm64Main").dependsOn(uikitMain)
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 28
        targetSdk = 33
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "com.kkirrix"
        projectName = "kkirikkiri"
        deployConfigurations {
            simulator("IPhone12Pro") {
                //Usage: ./gradlew iosDeployIPhone12ProDebug
                device = org.jetbrains.compose.experimental.dsl.IOSDevices.IPHONE_12_PRO
            }
        }
    }
}