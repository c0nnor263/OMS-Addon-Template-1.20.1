package com.example.examplemod.foundation.reason

import com.example.examplemod.ExampleMod
import io.conboi.oms.api.foundation.reason.StopReason

interface ExampleModStopReason : StopReason {
    override val addonId: String
        get() = ExampleMod.MOD_ID
}