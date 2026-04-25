package com.example.examplemod.elements.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.conboi.oms.api.elements.commands.OMSCommandBranch
import io.conboi.oms.api.elements.commands.OMSCommandEntry
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands

internal class ExampleModCommandBranch : OMSCommandBranch() {
    override fun getCommands(): List<OMSCommandEntry> {
        return listOf(
            ExampleModStopCommand()
        )
    }

    override fun register(
        dispatcher: CommandDispatcher<CommandSourceStack>,
        groupBuilder: LiteralArgumentBuilder<CommandSourceStack>?
    ) {
        val builder = Commands.literal("examplemod")
            .requires { it.hasPermission(4) }
        super.register(dispatcher, builder)
    }
}