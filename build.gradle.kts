plugins {
    id("com.android.application") version deps.versions.agp apply false
    id("com.android.library") version deps.versions.agp apply false
    id("org.jetbrains.kotlin.android") version deps.versions.kotlin apply false
    id("org.jetbrains.compose") version "1.2.0-beta02" apply false
}

allprojects {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}