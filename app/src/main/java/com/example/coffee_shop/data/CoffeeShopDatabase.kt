package com.example.coffee_shop.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.coffee_shop.models.Favorite
import com.example.coffee_shop.util.Converters

@Database(entities = [Favorite::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CoffeeShopDatabase: RoomDatabase() {
    abstract fun coffeeShopDao(): CoffeeShopDao
}