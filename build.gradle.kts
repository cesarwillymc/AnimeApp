// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.gitlab.arturbosch.detekt") version "1.20.0"
    id("org.jmailen.kotlinter") version "3.12.0"
    id("com.github.ben-manes.versions") version "0.39.0"
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
    }
}
allprojects {
    apply(plugin = "org.jmailen.kotlinter")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "jacoco")
    apply(plugin = "com.github.ben-manes.versions")
    detekt {
        config = files("${rootProject.projectDir}/detekt/detekt-config.yml")
        parallel = true
    }
    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.20.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}