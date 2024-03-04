package de.agiledojo.kata.checkoutservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CheckoutApplication

fun main(args: Array<String>) {
	runApplication<CheckoutApplication>(*args)
}
