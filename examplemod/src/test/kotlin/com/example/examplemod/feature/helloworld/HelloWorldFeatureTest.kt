package com.example.examplemod.feature.helloworld

import io.kotest.matchers.shouldBe
import com.example.examplemod.feature.helloworld.elements.commands.HelloWorldPrintCommand
import com.example.examplemod.elements.commands.ExampleModStopCommand
import com.example.examplemod.feature.helloworld.event.HelloWorldEvents
import com.example.examplemod.feature.helloworld.infrastructure.config.CHelloWorldFeature
import io.conboi.oms.api.foundation.feature.Priority
import io.conboi.oms.api.infrastructure.config.ConfigProvider
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import net.minecraft.network.chat.Component
import net.minecraft.server.MinecraftServer
import net.minecraft.server.players.PlayerList

class HelloWorldFeatureTest : ShouldSpec({

    lateinit var sut: HelloWorldFeature

    val mockConfig = mockk<CHelloWorldFeature>(relaxed = true)
    val mockConfigProvider: ConfigProvider<CHelloWorldFeature> = mockk()
    val mockServer = mockk<MinecraftServer>(relaxed = true)
    val mockPlayers = mockk<PlayerList>(relaxed = true)

    beforeEach {
        every { mockConfigProvider.get() } returns mockConfig
        every { mockConfig.message() } returns "Hello from config"
        every { mockServer.playerList } returns mockPlayers

        sut = HelloWorldFeature(mockConfigProvider)
    }

    afterEach {
        clearAllMocks()
    }

    context("info") {

        should("override id and priority") {
            val info = sut.info()

            info.id shouldBe CHelloWorldFeature.NAME
            info.priority shouldBe Priority.COMMON
        }
    }

    context("additionalCommands") {

        should("include hello world commands") {
            sut.additionalCommands.map { it::class } shouldContainExactly listOf(
                HelloWorldPrintCommand::class,
                ExampleModStopCommand::class
            )
        }
    }

    context("createEventListeners") {

        should("include HelloWorldEvents") {
            sut.createEventListeners().map { it::class } shouldContainExactly listOf(
                HelloWorldEvents::class
            )
        }
    }

    context("sayHello") {

        should("broadcast hello message to player") {
            val slotMsg = slot<Component>()

            every { mockPlayers.broadcastSystemMessage(capture(slotMsg), false) } returns Unit

            sut.sayHello(mockServer, "Steve")

            verify {
                mockPlayers.broadcastSystemMessage(any(), false)
            }

            slotMsg.captured.string shouldBe "Hello, Steve!"
        }
    }
})
