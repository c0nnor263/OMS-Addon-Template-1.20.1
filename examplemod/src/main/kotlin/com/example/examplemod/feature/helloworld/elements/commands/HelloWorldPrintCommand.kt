package com.example.examplemod.feature.helloworld.elements.commands

import com.example.examplemod.feature.helloworld.HelloWorldFeature
import com.mojang.brigadier.builder.ArgumentBuilder
import io.conboi.oms.api.elements.commands.OMSCommandEntry
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component

class HelloWorldPrintCommand(
    private val feature: HelloWorldFeature
) : OMSCommandEntry() {

    override fun init(): ArgumentBuilder<CommandSourceStack, *> {
        return Commands.literal("print")
            .executes { ctx ->
                val server = ctx.source.server
                server.playerList.broadcastSystemMessage(
                    Component.literal(
                        feature.config.message()
                    ),
                    false
                )
                1
            }
    }
}