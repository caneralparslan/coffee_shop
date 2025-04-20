package com.example.coffee_shop.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.coffee_shop.data.CoffeeShopDao
import com.example.coffee_shop.data.CoffeeShopDatabase
import com.example.coffee_shop.repository.CartRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideCoffeeShopDao(coffeeShopDatabase: CoffeeShopDatabase): CoffeeShopDao
            = coffeeShopDatabase.coffeeShopDao()


    @Singleton
    @Provides
    fun provideCoffeeShopDatabase(@ApplicationContext context: Context): CoffeeShopDatabase
            = Room.databaseBuilder(
        context,
        CoffeeShopDatabase::class.java,
        "coffee_shop_database")
        .fallbackToDestructiveMigration(false)
        .build()

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("coffee_shop_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideCartRepository(sharedPreferences: SharedPreferences, gson: Gson): CartRepository {
        return CartRepository(sharedPreferences, gson)
    }
}