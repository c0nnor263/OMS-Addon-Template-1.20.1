package com.example.examplemod.foundation.manager

import com.example.examplemod.ExampleMod
import io.conboi.oms.api.event.OMSLifecycle
import io.conboi.oms.api.foundation.addon.AddonContext
import io.conboi.oms.api.foundation.manager.FeatureManager

class CustomFeatureManager : FeatureManager(ExampleMod.MOD_ID) {
    override fun onStartingEvent(event: OMSLifecycle.StartingEvent, context: AddonContext) {
        super.onStartingEvent(event, context)
        ExampleMod.LOGGER.info("CustomFeatureManager received StartingEvent")
    }
}