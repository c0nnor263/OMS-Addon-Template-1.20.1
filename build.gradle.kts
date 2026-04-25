plugins {
    alias(libs.plugins.kotlin) apply false
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://thedarkcolour.github.io/KotlinForForge/")
            content { includeGroup("thedarkcolour") }
        }
        maven("https://maven.parchmentmc.org") // Parchment mappings
        maven("https://c0nnor263.github.io/OperateMyServer/maven") // OMS
    }
}