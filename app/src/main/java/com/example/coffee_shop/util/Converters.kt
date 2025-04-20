package com.example.coffee_shop.util

import androidx.room.TypeConverter
import com.example.coffee_shop.models.ItemCategory

class Converters {
    @TypeConverter
    fun fromItemCategory(category: ItemCategory): String = category.name

    @TypeConverter
    fun toItemCategory(value: String): ItemCategory = ItemCategory.valueOf(value)
}
