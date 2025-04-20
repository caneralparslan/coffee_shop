package com.example.coffee_shop.models

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.coffee_shop.R

enum class ItemCategory(val displayNameResId: Int) {
    ALL(R.string.all_items),
    HOT_DRINKS(R.string.hot_drinks),
    COLD_DRINKS(R.string.cold_drinks),
    FOODS(R.string.foods)
}
