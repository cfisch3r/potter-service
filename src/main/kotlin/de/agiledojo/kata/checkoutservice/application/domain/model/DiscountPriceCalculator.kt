package de.agiledojo.kata.checkoutservice.application.domain.model

class DiscountPriceCalculator {
    
    private val singleCopyPrice = Price(800)
    fun calculatePriceForCombination(combination: Map<Bundle, Amount>) =
        combination
            .priceForEachEntry()
            .reduce { sum, price -> sum + price }
    
    private fun Map<Bundle, Amount>.priceForEachEntry() = this.map {
        (bundle, amount) -> singleCopyPrice * bundle.size * discount(bundle) * amount.value
    }

    private fun discount(bundle: Bundle) = when (bundle.size) {
        1 -> 1.0
        2 -> 0.95
        3 -> 0.9
        4 -> 0.8
        5 -> 0.75
        else -> {throw IllegalArgumentException("No bundle price for size ${bundle.size} defined.")}
    }

}