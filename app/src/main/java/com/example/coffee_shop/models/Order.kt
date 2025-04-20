package com.example.coffee_shop.models

data class Order(
    val items: List<Item>,
    val totalPrice: Double,
    val timestamp: Long = System.currentTimeMillis()
)
