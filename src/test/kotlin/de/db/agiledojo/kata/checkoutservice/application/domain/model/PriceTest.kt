package de.db.agiledojo.kata.checkoutservice.application.domain.model

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PriceTest {

    @Test
    fun `price cannot be negative`() {
        shouldThrow<IllegalArgumentException> { 
            Price(-1)
        }
    }
}