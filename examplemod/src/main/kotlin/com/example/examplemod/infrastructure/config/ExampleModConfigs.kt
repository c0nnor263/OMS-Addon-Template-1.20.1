package com.example.examplemod.infrastructure.config

import com.example.examplemod.feature.helloworld.infrastructure.config.CHelloWorldFeature
import net.minecraftforge.common.ForgeConfigSpec
import net.minecraftforge.fml.config.ModConfig
import thedarkcolour.kotlinforforge.forge.registerConfig

object ExampleModConfigs {
    lateinit var feature: CHelloWorldFeature
        private set

    lateinit var spec: ForgeConfigSpec
        private set

    fun register() {
        val specPair = ForgeConfigSpec.Builder()
            .configure { builder ->
                builder.push(CHelloWorldFeature.NAME)

                val enabled = builder.define("enabled", true)
                val message = builder.define("message", "hello world")

                builder.pop()

                CHelloWorldFeature(enabled, message)
            }

        feature = specPair.left
        spec = specPair.right

        registerConfig(ModConfig.Type.SERVER, spec)
    }
}