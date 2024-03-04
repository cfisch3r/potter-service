package de.agiledojo.kata.checkoutservice.application.domain.service

import de.agiledojo.kata.checkoutservice.application.domain.model.BundleCombiner
import de.agiledojo.kata.checkoutservice.application.domain.model.DiscountPriceCalculator
import de.agiledojo.kata.checkoutservice.application.domain.model.Price
import de.agiledojo.kata.checkoutservice.application.port.`in`.PriceCalculation
import org.springframework.stereotype.Component

@Component
class ComposedPriceCalculation: PriceCalculation {

    private val bundleCombiner = BundleCombiner()
    
    private val calculator = DiscountPriceCalculator()

    override fun calculatePrice(query: PriceCalculation.PriceQuery) =
        bundleCombiner
            .findBundleCombinations(query.basket)
            .map(calculator::calculatePriceForCombination)
            .minBy(Price::inCent)
}