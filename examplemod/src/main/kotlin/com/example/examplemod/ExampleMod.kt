package com.example.examplemod

import com.example.examplemod.ExampleMod.MOD_ID
import com.example.examplemod.infrastructure.config.ExampleModConfigs
import com.mojang.logging.LogUtils
import net.minecraftforge.fml.common.Mod

@Mod(MOD_ID)
object ExampleMod {
    const val MOD_ID: String = "examplemod"

    val LOGGER = LogUtils.getLogger()

    init {
        LOGGER.info("$MOD_ID initialized")

        ExampleModConfigs.register()
    }
}