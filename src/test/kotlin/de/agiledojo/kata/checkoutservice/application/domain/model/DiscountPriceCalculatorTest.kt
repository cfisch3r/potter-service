package de.agiledojo.kata.checkoutservice.application.domain.model

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class DiscountPriceCalculatorTest {
    
    companion object {
        
        @JvmStatic
        fun bundleDiscounts() = listOf(
            Arguments.of(Bundle(1), Price(800)),
            Arguments.of(Bundle(2), Price((0.95 * 2 * 800).toInt())),
            Arguments.of(Bundle(3), Price((0.90 * 3 * 800).toInt())),
            Arguments.of(Bundle(4), Price((0.80 * 4 * 800).toInt())),
            Arguments.of(Bundle(5), Price((0.75 * 5 * 800).toInt()))
        )

    }

    private val calculator = DiscountPriceCalculator() 
    
    @ParameterizedTest
    @MethodSource("bundleDiscounts")
    fun `price for a bundle calculated according its discount`(bundle: Bundle, bundlePrice: Price) {
        calculator.calculatePriceForCombination(mapOf(bundle to Amount(1))) shouldBe bundlePrice        
    }

    @Test
    fun `price for a bundle takes amount into account`() {
        calculator.calculatePriceForCombination(mapOf(Bundle(1) to Amount(2))) shouldBe Price(1600)
    }

    @Test
    fun `price is the sum of individual bundle prices`() {
        calculator.calculatePriceForCombination(mapOf(
            Bundle(1) to Amount(2),
            Bundle(2) to Amount(3)
        )) shouldBe Price(6160)
    }

    @Test
    fun `price for bundles with no defined bundle price cannot be calculated`() {
        shouldThrow<IllegalArgumentException> {
            calculator.calculatePriceForCombination(mapOf(Bundle(6) to Amount(1)))
        }
    }
}