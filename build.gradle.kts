plugins {
    id("com.android.application") version deps.versions.agp apply false
    id("com.android.library") version deps.versions.agp apply false
    id("org.jetbrains.kotlin.android") version deps.versions.kotlin apply false
    kotlin("plugin.serialization") version deps.versions.kotlin apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}