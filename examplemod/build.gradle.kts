import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.slf4j.event.Level

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotest)
    alias(libs.plugins.modDevGradle)
    `maven-publish`
    idea
}

version = "${ModInfo.VERSION}+mc${libs.versions.minecraft.get()}"
group = ModInfo.GROUP_ID

base {
    archivesName = ModInfo.ID
}

legacyForge {
    version = libs.versions.minecraft.get() + "-" + libs.versions.forge.get()

    parchment {
        mappingsVersion.set(
            libs.versions.parchment.get()
        )
        minecraftVersion.set(
            libs.versions.minecraft.get()
        )
    }

    runs {
        create("client") {
            client()

            systemProperty("forge.enabledGameTestNamespaces", ModInfo.ID)
        }

        create("server") {
            server()
            systemProperty("forge.enabledGameTestNamespaces", ModInfo.ID)
        }

        create("data") {
            data()

            programArguments.addAll(
                listOf(
                    "--mod",
                    ModInfo.ID,
                    "--all",
                    "--output",
                    file("src/generated/resources/").absolutePath,
                    "--existing",
                    file("src/main/resources/").absolutePath
                )
            )
        }

        configureEach {
            systemProperty("forge.logging.markers", "REGISTRIES")
            logLevel.set(Level.DEBUG)

            jvmArgument("-XX:+IgnoreUnrecognizedVMOptions")
            jvmArgument("-XX:+UnlockExperimentalVMOptions")
        }
    }

    mods {
        create(ModInfo.ID) {
            sourceSet(sourceSets.getByName("main"))
        }
    }

    // Add modding dependencies to the test source set
    addModdingDependenciesTo(sourceSets.getByName("test"))
}

sourceSets.getByName("main").resources.srcDir(layout.buildDirectory.dir("generated/resources"))

// Sets up a dependency configuration called 'localRuntime' and a deobfuscating one called 'modLocalRuntime'
// These configurations should be used instead of 'runtimeOnly' to declare
// a dependency that will be present for runtime testing but that is
// "optional", meaning it will not be pulled by dependents of this mod.
val localRuntime by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(localRuntime)
    }
}

obfuscation {
    createRemappingConfiguration(localRuntime)
}

dependencies {
    "modLocalRuntime"(libs.oms)
    modCompileOnly(libs.omsApi)

    implementation(libs.kotlinforforge)

    // Optional: If your mod uses Kotlin serialization, include the library and apply the appropriate plugin
    // implementation(libs.kotlinxSerialization)

    // Testing dependencies
    testImplementation(libs.bundles.testing)
    testImplementation(libs.omsApi)
}

val generateModMetadata = tasks.register("generateModMetadata", ProcessResources::class.java) {
    val replaceProperties = mapOf(
        "minecraft_version" to libs.versions.minecraft.get(),
        "forge_version" to libs.versions.forge.get(),
        "loader_version" to libs.versions.kotlinForForge.get(),
        "oms_version" to libs.versions.oms.get(),
        "mod_id" to ModInfo.ID,
        "mod_name" to ModInfo.DISPLAY_NAME,
        "mod_license" to ModInfo.LICENSE,
        "mod_version" to ModInfo.VERSION,
        "mod_authors" to ModInfo.AUTHOR,
        "mod_description" to ModInfo.DESCRIPTION
    )
    inputs.properties(replaceProperties).apply {
        expand(replaceProperties)
        from("src/main/templates")
        into("build/generated/sources/modMetadata")
    }
}
sourceSets.getByName("main").resources.srcDir(generateModMetadata)
legacyForge.ideSyncTask(generateModMetadata)

kotlin {
    jvmToolchain(17)
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release.set(17)
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}