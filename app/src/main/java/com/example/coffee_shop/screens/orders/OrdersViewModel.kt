package com.example.coffee_shop.screens.orders

import androidx.lifecycle.ViewModel
import com.example.coffee_shop.models.Order
import com.example.coffee_shop.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {

    private val _orderList = MutableStateFlow<List<Order>>(emptyList())
    val orderList = _orderList.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        _orderList.value = orderRepository.getOrders()
    }

    fun placeOrder(order: Order) {
        orderRepository.saveOrder(order)
    }

    fun clearOrders(){
        orderRepository.clearOrders()
    }
}
