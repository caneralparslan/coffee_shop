package com.example.coffee_shop

import com.example.coffee_shop.models.Item
import com.example.coffee_shop.models.ItemCategory
import com.example.coffee_shop.models.Order
import com.example.coffee_shop.repository.OrderRepository
import com.example.coffee_shop.screens.orders.OrderViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class OrderViewModelTest {

    private lateinit var orderViewModel: OrderViewModel
    private lateinit var orderRepository: OrderRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        orderRepository = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `init should load orders from repository`() = runTest {

        val orders = listOf(
            Order(
                items = listOf(
                    Item(id = "0", nameResId = 0, descResId = 0, price = 4.99, imagePath = "", category = ItemCategory.HOT_DRINKS)
                ),
                totalPrice = 4.99
            ),
            Order(
                items = listOf(
                    Item(id = "1", nameResId = 0, descResId = 0, price = 3.99, imagePath = "", category = ItemCategory.HOT_DRINKS)
                ),
                totalPrice = 3.99
            )
        )
        every { orderRepository.getOrders() } returns orders

        orderViewModel = OrderViewModel(orderRepository)

        val collectedOrders = orderViewModel.orderList.first()
        assertEquals(orders, collectedOrders)
        verify(exactly = 1) { orderRepository.getOrders() }
    }

    @Test
    fun `placeOrder should call repository saveOrder`() = runTest {
        val order = Order(
            items = listOf(
                Item(id = "2", nameResId = 0, descResId = 0, price = 2.50, imagePath = "", category = ItemCategory.HOT_DRINKS)
            ),
            totalPrice = 2.50
        )

        orderViewModel = OrderViewModel(orderRepository)
        orderViewModel.placeOrder(order)

        verify(exactly = 1) { orderRepository.saveOrder(order) }
    }

    @Test
    fun `clearOrders should call repository clearOrders`() = runTest {
        orderViewModel = OrderViewModel(orderRepository)
        orderViewModel.clearOrders()

        verify(exactly = 1) { orderRepository.clearOrders() }
    }
}