package com.example.examplemod

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

/**
 * This is an example test class for the Example Addon. It demonstrates how to write tests using Kotest.
 * You can replace the example tests with actual tests for your addon features
 *
 * Make sure to install Kotest plugin in your IDE to get better support for writing and running tests.
 * You can run the tests using the Gradle task `test` or directly from your IDE
 * Refer to the [Kotest documentation](https://kotest.io/docs/framework/framework.html) for more information on how to write different types of tests and use various matchers
 */
class ExampleTest : ShouldSpec({

    should("sum two numbers") {
        val result = 2 + 2

        result shouldBe 4
    }

    should("filter even numbers from a range") {
        val evenNumbers = (1..6).filter { it % 2 == 0 }

        evenNumbers shouldContainExactly listOf(2, 4, 6)
    }
})