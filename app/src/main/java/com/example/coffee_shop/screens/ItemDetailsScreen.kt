package com.example.coffee_shop.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.coffee_shop.data.items
import com.example.coffee_shop.models.Item


@Composable
fun ItemDetailsScreen(
    navController: NavController,
    itemId: String
){

    val item = items.filter{ item-> item.id == itemId }[0]

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(item.name)
    }
}