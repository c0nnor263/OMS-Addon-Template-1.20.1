package com.example.examplemod.event

import com.example.examplemod.ExampleMod
import com.example.examplemod.elements.commands.ExampleModCommandBranch
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
internal object CommandsListener {
    @SubscribeEvent
    fun onRegisterCommands(event: RegisterCommandsEvent) {
        ExampleModCommandBranch().register(event.dispatcher)
    }
}