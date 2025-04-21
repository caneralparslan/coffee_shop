package com.example.coffee_shop.screens.cart

import android.util.Log
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.models.ItemCategory
import com.example.coffee_shop.repository.CartRepository
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
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
class CartViewModelTest {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartRepository: CartRepository
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var cartItemsFlow: MutableStateFlow<List<Item>>
    private val BASE_URL = "https://amasmobile.com/caner/"

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        cartRepository = mockk(relaxed = true)
        val initialItems = listOf(
            Item(id = "0", nameResId = 0, descResId = 0, price = 4.99, imagePath = BASE_URL+"espresso.png", category = ItemCategory.HOT_DRINKS)
        )

        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0


        cartItemsFlow = MutableStateFlow(initialItems)

        every { cartRepository.cartItems } returns cartItemsFlow
        every { cartRepository.getTotalPrice() } returns 4.99 andThen 8.98

        cartViewModel = CartViewModel(cartRepository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkStatic(Log::class)
    }

    @Test
    fun `init should collect cart items from repository`() = runTest {
        val cartItems = listOf(
            Item(
                id = "0",
                nameResId = 0,
                descResId = 0,
                price = 4.99,
                imagePath = BASE_URL+"espresso.png",
                category = ItemCategory.HOT_DRINKS
            ),
            Item(
                id = "1",
                nameResId = 0,
                descResId = 0,
                price = 3.99,
                imagePath = BASE_URL+"latte.png",
                category = ItemCategory.HOT_DRINKS
            )
        )

        cartItemsFlow.value = cartItems
        testDispatcher.scheduler.advanceUntilIdle()

        val collectedItems = cartViewModel.cartList.first()
        val collectedTotal = cartViewModel.totalPrice.first()

        assertEquals("Cart items should match", cartItems, collectedItems)
        assertEquals("Total price should match", 8.98, collectedTotal, 0.01)
    }

    @Test
    fun `addItem should call repository addItem`() = runTest {
        val item = Item(id = "1", nameResId = 0, descResId = 0, price = 2.5, imagePath = "", category = ItemCategory.HOT_DRINKS)
        every { cartRepository.cartItems } returns MutableStateFlow(emptyList())
        cartViewModel = CartViewModel(cartRepository)

        cartViewModel.addItem(item)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { cartRepository.addItem(item) }
    }

    @Test
    fun `removeItem should call repository removeItem`() = runTest {
        val item = Item(id = "1", nameResId = 0, descResId = 0, price = 2.5, imagePath = "", category = ItemCategory.HOT_DRINKS)
        every { cartRepository.cartItems } returns MutableStateFlow(emptyList())
        cartViewModel = CartViewModel(cartRepository)

        cartViewModel.removeItem(item)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { cartRepository.removeItem(item) }
    }

    @Test
    fun `clearCart should call repository clearCart`() = runTest {
        every { cartRepository.cartItems } returns MutableStateFlow(emptyList())
        cartViewModel = CartViewModel(cartRepository)

        cartViewModel.clearCart()
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { cartRepository.clearCart() }
    }

    @Test
    fun `removeAll should call repository removeAllOf`() = runTest {
        val item = Item(id = "1", nameResId = 0, descResId = 0, price = 2.5, imagePath = "", category = ItemCategory.HOT_DRINKS)
        every { cartRepository.cartItems } returns MutableStateFlow(emptyList())
        cartViewModel = CartViewModel(cartRepository)

        cartViewModel.removeAll(item)
        testDispatcher.scheduler.advanceUntilIdle()

        coVerify { cartRepository.removeAllOf(item) }
    }
}