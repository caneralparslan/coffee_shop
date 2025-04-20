package com.example.coffee_shop.repository

import com.example.coffee_shop.data.CoffeeShopDao
import com.example.coffee_shop.models.Favorite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class CoffeeShopDbRepository @Inject constructor(private val coffeeShopDao: CoffeeShopDao) {

    fun getFavorites(): Flow<List<Favorite>> = coffeeShopDao.getFavorites()

    suspend fun getFavoriteItemById(id: String) : Favorite? = coffeeShopDao.getFavoriteItemById(id)
    suspend fun addFavorite(favorite: Favorite) = coffeeShopDao.addFavorite(favorite)
    suspend fun updateFavorite(favorite: Favorite) = coffeeShopDao.updateFavorite(favorite)
    suspend fun removeFavorite(id: String) = coffeeShopDao.removeFavoriteById(id)
    suspend fun deleteAllFavorites() = coffeeShopDao.deleteAllFavorites()
}