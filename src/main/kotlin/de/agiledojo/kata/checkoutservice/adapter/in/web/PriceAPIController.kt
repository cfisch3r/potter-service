package de.agiledojo.kata.checkoutservice.adapter.`in`.web

import de.agiledojo.kata.checkoutservice.application.domain.model.Amount
import de.agiledojo.kata.checkoutservice.application.domain.model.PotterVolume
import de.agiledojo.kata.checkoutservice.application.port.`in`.PriceCalculation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

typealias PriceRequest = Map<PotterVolume, Int>
@RestController
class PriceAPIController(private val priceCalculation: PriceCalculation) {
    
    @PostMapping("/price")
    fun price(@RequestBody request: PriceRequest) =
        priceCalculation.calculatePrice(request.toPriceQuery())
    
    // Unfortunately a direct request body mapping to a map with data classes is not possible
    // see https://github.com/FasterXML/jackson-module-kotlin/issues/432
    private fun PriceRequest.toPriceQuery() =
        PriceCalculation.PriceQuery(this.mapValues { (_, amount) -> Amount(amount) })
    
}