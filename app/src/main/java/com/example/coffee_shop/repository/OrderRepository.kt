package com.example.coffee_shop.repository

import android.content.SharedPreferences
import com.example.coffee_shop.models.Order
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class OrderRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) {
    private val ordersKey = "orders"

    fun saveOrder(order: Order) {
        val orders = getOrders().toMutableList()
        orders.add(order)
        val json = gson.toJson(orders)
        sharedPreferences.edit().putString(ordersKey, json).apply()
    }

    fun getOrders(): List<Order> {
        val json = sharedPreferences.getString(ordersKey, null) ?: return emptyList()
        val type = object : TypeToken<List<Order>>() {}.type
        return gson.fromJson(json, type)
    }

    fun clearOrders() {
        sharedPreferences.edit().remove(ordersKey).apply()
    }
}
