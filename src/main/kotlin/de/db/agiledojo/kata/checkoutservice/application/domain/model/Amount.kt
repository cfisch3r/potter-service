package de.db.agiledojo.kata.checkoutservice.application.domain.model

data class Amount(val value: Int) {
    init {
        require(value > 0) {"Amount has to be greater than 0"}
    }
}