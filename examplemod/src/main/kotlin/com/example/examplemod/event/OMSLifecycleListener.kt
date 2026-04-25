package com.example.examplemod.event

import com.example.examplemod.ExampleMod
import com.example.examplemod.ExampleModAddon
import io.conboi.oms.api.event.OMSLifecycle
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID)
internal object OMSLifecycleListener {

    @SubscribeEvent
    fun onAddonRegisterEvent(event: OMSLifecycle.Addon.RegisterEvent) {
        event.registry.register(ExampleModAddon())
    }
}