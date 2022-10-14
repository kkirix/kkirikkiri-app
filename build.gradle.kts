plugins {
    id("com.android.application") version deps.versions.agp apply false
    id("com.android.library") version deps.versions.agp apply false
    id("org.jetbrains.kotlin.android") version deps.versions.kotlin apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}