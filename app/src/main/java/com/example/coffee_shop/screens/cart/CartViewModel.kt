package com.example.coffee_shop.screens.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.models.Order
import com.example.coffee_shop.repository.CartRepository
import com.example.coffee_shop.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository) : ViewModel() {

    private val _cartList = MutableStateFlow<List<Item>>(emptyList()) // local state
    val cartList: StateFlow<List<Item>> = _cartList

    init {
        // Collect cart items when the ViewModel is initialized
        viewModelScope.launch(Dispatchers.IO) {
            cartRepository.cartItems
                .collect { listOfCartItems ->
                    _cartList.value = listOfCartItems // update local state
                    Log.d("CartViewModel", "Updated Cart: $listOfCartItems")
                }
        }
    }

    fun addItem(item: Item) {
        viewModelScope.launch { cartRepository.addItem(item) }
    }

    fun removeItem(item: Item) {
        viewModelScope.launch { cartRepository.removeItem(item) }
    }

    fun getTotalPrice(): Double = cartRepository.getTotalPrice()

    fun clearCart() {
        viewModelScope.launch { cartRepository.clearCart() }
    }
}
