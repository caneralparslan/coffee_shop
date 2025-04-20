package com.example.coffee_shop.repository

import android.content.SharedPreferences
import com.example.coffee_shop.models.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {
    private val cartKey = "cart"

    private val _cartItems = MutableStateFlow(loadCartFromPrefs())
    val cartItems: StateFlow<List<Item>> = _cartItems

    private fun loadCartFromPrefs(): List<Item> {
        val cartJson = sharedPreferences.getString(cartKey, null)
        val type = object : TypeToken<List<Item>>() {}.type
        return if (!cartJson.isNullOrEmpty()) {
            gson.fromJson(cartJson, type)
        } else {
            emptyList()
        }
    }

    // Save the updated cart back to SharedPreferences
    private fun saveCart(cart: List<Item>) {
        val cartJson = gson.toJson(cart)
        sharedPreferences.edit().putString(cartKey, cartJson).apply()
        _cartItems.value = loadCartFromPrefs()
    }

    // Add item to cart
    fun addItem(item: Item) {
        val updatedCart = _cartItems.value.toMutableList().apply { add(item) }
        saveCart(updatedCart)
    }

    // Remove the last occurrence of the item from the cart
    fun removeItem(item: Item) {
        val updatedCart = _cartItems.value.toMutableList().apply {
            val lastIndex = indexOfLast { it.id == item.id }
            if (lastIndex != -1) {
                removeAt(lastIndex)
            }
        }
        saveCart(updatedCart)
    }


    // Get total price of items in the cart
    fun getTotalPrice(): Double {
        return _cartItems.value.sumOf { it.price }
    }

    // Clear cart
    fun clearCart() {
        sharedPreferences.edit().remove(cartKey).apply()
        _cartItems.value = emptyList()
    }

    // Clear all the occurrences of given item
    fun removeAllOf(item: Item) {
        val currentList = _cartItems.value.toMutableList().apply {
            removeAll { it.id == item.id }
        }
        saveCart(currentList)
    }

}
