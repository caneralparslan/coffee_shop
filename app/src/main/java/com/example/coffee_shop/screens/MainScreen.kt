package com.example.coffee_shop.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.TopBar
import com.example.coffee_shop.navigation.BottomNavItem
import com.example.coffee_shop.navigation.BottomNavigationBar
import com.example.coffee_shop.screens.favorite.FavoriteScreen

@Composable
fun MainScreen(navController: NavController) {
    val bottomNavController = rememberNavController()


    // Observe current route
    val currentBackStackEntry = bottomNavController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route

    // Set dynamic title based on route
    val title = when (currentDestination) {
        BottomNavItem.Home.route -> stringResource(R.string.home)
        BottomNavItem.Favorite.route -> stringResource(R.string.favorites)
        BottomNavItem.Cart.route -> stringResource(R.string.shopping_cart)
        else -> ""
    }

    Scaffold(
        topBar = {
            TopBar(
                navController = navController,
                title = title,
                isMain = true
                )
        },
        bottomBar = {
            BottomNavigationBar(bottomNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        ) {
            composable(BottomNavItem.Home.route) { HomeScreen(navController) }
            composable(BottomNavItem.Favorite.route) { FavoriteScreen(navController) }
            composable(BottomNavItem.Cart.route) { CartScreen() }
        }
    }
}
