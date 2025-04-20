package com.example.coffee_shop.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector) {
    data object Home : BottomNavItem("home", Icons.Default.Home)
    data object Favorite : BottomNavItem("favorite", Icons.Default.Favorite)
    data object Cart : BottomNavItem("cart", Icons.Default.ShoppingCart)
}
