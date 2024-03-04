package de.agiledojo.kata.checkoutservice.application.domain.model

data class Price(val inCent: Int) {
    
    init {
        require(inCent >= 0) {"Price cannot be negative"}
    }
    
    operator fun plus(other: Price) = Price(this.inCent + other.inCent)
    
    operator fun times(factor: Double) = Price((this.inCent * factor).toInt())

    operator fun times(factor: Int) = Price(this.inCent * factor)
    
}