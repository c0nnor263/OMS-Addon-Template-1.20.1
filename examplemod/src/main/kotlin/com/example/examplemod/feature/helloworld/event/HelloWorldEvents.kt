package com.example.examplemod.feature.helloworld.event

import com.example.examplemod.feature.helloworld.HelloWorldFeature
import net.minecraftforge.event.entity.player.PlayerEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

class HelloWorldEvents(
    private val feature: HelloWorldFeature
) {

    @SubscribeEvent
    fun onPlayerLoggedIn(event: PlayerEvent.PlayerLoggedInEvent) {
        val server = event.entity.server ?: return
        val playerName = event.entity.name.string
        feature.sayHello(server, playerName)
    }
}