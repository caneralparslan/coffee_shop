package com.example.coffee_shop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coffee_shop.screens.ItemDetailsScreen
import com.example.coffee_shop.screens.MainScreen
import com.example.coffee_shop.screens.SettingsScreen
import com.example.coffee_shop.screens.SplashScreen
import com.example.coffee_shop.screens.login_signup.LoginScreen
import com.example.coffee_shop.screens.login_signup.SignUpScreen


@Composable
fun CoffeeShopNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash_screen",
    ) {
        composable("splash_screen"){ SplashScreen(navController)}
        composable("login_screen"){ LoginScreen(navController) }
        composable("signup_screen"){ SignUpScreen(navController) }
        composable("main_screen"){ MainScreen(navController) }
        composable("settings_screen"){ SettingsScreen(navController) }

        composable("item_details_screen/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            ItemDetailsScreen(
                navController = navController,
                itemId = itemId ?: "")
        }
    }
}