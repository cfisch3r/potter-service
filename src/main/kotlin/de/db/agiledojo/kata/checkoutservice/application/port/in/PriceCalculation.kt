package de.db.agiledojo.kata.checkoutservice.application.port.`in`

import de.db.agiledojo.kata.checkoutservice.application.domain.model.Basket
import de.db.agiledojo.kata.checkoutservice.application.domain.model.Price

interface PriceCalculation {

    data class PriceQuery(val basket: Basket)

    fun calculatePrice(query: PriceQuery): Price
}
