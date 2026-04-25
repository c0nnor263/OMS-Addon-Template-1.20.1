package com.example.examplemod.elements.commands

import com.example.examplemod.foundation.reason.ExampleModManualStop
import com.mojang.brigadier.builder.ArgumentBuilder
import io.conboi.oms.api.elements.commands.OMSCommandEntry
import io.conboi.oms.api.event.OMSActions
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraftforge.common.MinecraftForge

class ExampleModStopCommand : OMSCommandEntry() {
    override fun init(): ArgumentBuilder<CommandSourceStack, *> {
        return Commands.literal("stop")
            .executes { ctx ->
                val server = ctx.source.server

                MinecraftForge.EVENT_BUS.post(
                    OMSActions.StopRequestedEvent(
                        server = server,
                        reason = ExampleModManualStop
                    )
                )
                1
            }
    }
}