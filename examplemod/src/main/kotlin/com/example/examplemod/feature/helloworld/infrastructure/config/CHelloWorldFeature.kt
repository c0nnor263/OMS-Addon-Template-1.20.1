package com.example.examplemod.feature.helloworld.infrastructure.config

import io.conboi.oms.api.infrastructure.config.FeatureConfig
import net.minecraftforge.common.ForgeConfigSpec

class CHelloWorldFeature(
    private val enabledValue: ForgeConfigSpec.BooleanValue,
    private val messageValue: ForgeConfigSpec.ConfigValue<String>
) : FeatureConfig {

    override val name: String = NAME

    override fun isEnabled(): Boolean = enabledValue.get()

    override fun enable() {
        enabledValue.set(true)
    }

    override fun disable() {
        enabledValue.set(false)
    }

    fun message(): String = messageValue.get()

    override fun getConfigData(): Map<String, Any> {
        return mapOf(
            "enabled" to enabledValue.get(),
            "message" to messageValue.get()
        )
    }

    companion object {
        const val NAME = "hello_world_feature"
    }
}