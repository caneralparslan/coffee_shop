package com.example.coffee_shop.models

data class Item(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imagePath: Int, // or String if using URL or filename
    val category: ItemCategory
)