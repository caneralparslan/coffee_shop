package com.example.coffee_shop

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.coffee_shop.models.Favorite
import com.example.coffee_shop.models.ItemCategory
import com.example.coffee_shop.repository.CoffeeShopDbRepository
import com.example.coffee_shop.screens.favorite.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@OptIn(ExperimentalCoroutinesApi::class)
class FavoriteViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var repository: CoffeeShopDbRepository

    val BASE_URL = "https://amasmobile.com/caner"

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mock()
        viewModel = FavoriteViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addFavorite triggers repository call`() = runTest {
        val fakeFavorite = Favorite(
            "1",
            0, // nameRes
            0, // descriptionRes
            19.99,
            "$BASE_URL/espresso.png",
            ItemCategory.HOT_DRINKS
        )
        viewModel.addFavorite(fakeFavorite)
        verify(repository).addFavorite(fakeFavorite)
    }
}