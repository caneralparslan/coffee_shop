package com.example.coffee_shop.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coffee_shop.models.Favorite
import com.example.coffee_shop.models.Item
import kotlinx.coroutines.flow.Flow


@Dao
interface CoffeeShopDao {

    @Query("SELECT * FROM favorite_tbl")
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorite_tbl WHERE id=:id")
    suspend fun getFavoriteItemById(id: String): Favorite?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite_tbl")
    suspend fun deleteAllFavorites()

    @Query("DELETE FROM favorite_tbl WHERE id = :id")
    suspend fun removeFavoriteById(id: String)

}