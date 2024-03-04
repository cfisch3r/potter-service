package de.agiledojo.kata.checkoutservice.application.domain.model

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class AmountTest {

    @ParameterizedTest
    @ValueSource(ints = [-1,0])
    fun `amount has to be greater than one`(number: Int) {
        shouldThrow<IllegalArgumentException> { 
            Amount(number)
        }
    }
}