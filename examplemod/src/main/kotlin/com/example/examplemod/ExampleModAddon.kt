package com.example.examplemod

import com.example.examplemod.feature.helloworld.HelloWorldFeature
import com.example.examplemod.foundation.manager.CustomFeatureManager
import com.example.examplemod.infrastructure.config.ExampleModConfigs
import com.example.examplemod.infrastructure.file.CustomAddonPaths
import io.conboi.oms.api.foundation.addon.AddonContext
import io.conboi.oms.api.foundation.addon.AddonContextSpec
import io.conboi.oms.api.foundation.addon.OmsAddon

class ExampleModAddon : OmsAddon(ExampleMod.MOD_ID) {

    /**
     * Configure the addon context for this addon if you need to provide your own implementations of FeatureManager, AddonPaths or other addon related services.
     * If you don't need to customize the context, just return the provided spec as is
     */
    override fun configureContext(spec: AddonContextSpec): AddonContextSpec {
        return spec.copy(
            pathsFactory = {
                CustomAddonPaths()
            },
            featureManagerFactory = {
                CustomFeatureManager()
            }
        ) // or simply return spec if no customization is needed
    }

    override fun onRegisterFeatures(context: AddonContext) {
        context.featureManager.register(
            HelloWorldFeature { ExampleModConfigs.feature }
        )
    }
}