package com.example.examplemod.feature.helloworld

import com.example.examplemod.ExampleMod
import com.example.examplemod.feature.helloworld.elements.commands.HelloWorldPrintCommand
import com.example.examplemod.feature.helloworld.event.HelloWorldEvents
import com.example.examplemod.feature.helloworld.infrastructure.config.CHelloWorldFeature
import io.conboi.oms.api.event.OMSLifecycle
import io.conboi.oms.api.foundation.addon.AddonContext
import io.conboi.oms.api.foundation.feature.FeatureInfo
import io.conboi.oms.api.foundation.feature.OmsFeature
import io.conboi.oms.api.foundation.feature.Priority
import io.conboi.oms.api.infrastructure.config.ConfigProvider
import net.minecraft.network.chat.Component
import net.minecraft.server.MinecraftServer

class HelloWorldFeature(
    configProvider: ConfigProvider<CHelloWorldFeature>
) : OmsFeature<CHelloWorldFeature>(configProvider) {
    override fun info(): FeatureInfo {
        return super.info().copy(
            id = CHelloWorldFeature.NAME,
            priority = Priority.COMMON,
        )
    }

    override val additionalCommands = listOf(
        HelloWorldPrintCommand(this)
    )

    override fun createEventListeners(): List<Any> {
        return listOf(
            HelloWorldEvents(this)
        )
    }

    override fun onOmsStarted(
        event: OMSLifecycle.StartingEvent,
        context: AddonContext
    ) {
        ExampleMod.LOGGER.info(config.message())
    }

    fun sayHello(server: MinecraftServer, playerName: String) {
        server.playerList.broadcastSystemMessage(
            Component.literal("Hello, $playerName!"),
            false
        )
    }
}