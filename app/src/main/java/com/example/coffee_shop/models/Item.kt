package com.example.coffee_shop.models

data class Item(
    val id: String,
    val nameResId: Int,
    val descResId: Int,
    val price: Double,
    val imageResId: Int, // or String if using URL or filename
    val category: ItemCategory
)