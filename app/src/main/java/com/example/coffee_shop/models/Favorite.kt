package com.example.coffee_shop.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_tbl")
data class Favorite(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "nameResId")
    val nameResId: Int,

    @ColumnInfo(name = "descResId")
    val descResId: Int,

    @ColumnInfo(name = "price")
    val price: Double,

    @ColumnInfo(name = "imagePath")
    val imagePath: String,

    @ColumnInfo(name = "category")
    val category: ItemCategory

)