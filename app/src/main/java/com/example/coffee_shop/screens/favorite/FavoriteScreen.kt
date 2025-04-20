package com.example.coffee_shop.screens.favorite

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.FavoriteItemTile
import com.example.coffee_shop.components.TopBar

@Composable
fun FavoriteScreen(navController: NavController,
                   favoriteViewModel: FavoriteViewModel = hiltViewModel()){

    Scaffold(
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)
    ) {
        innerPadding ->
        FavoriteContent(innerPadding, navController, favoriteViewModel)
    }
}

@Composable
fun FavoriteContent(innerPadding: PaddingValues,
                    navController: NavController,
                    favoriteViewModel: FavoriteViewModel) {

    val favItems = favoriteViewModel.favList.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding(), bottom = 105.dp)
    ) {
        items(favItems.value){
            favItem ->
            FavoriteItemTile(
                favoriteItem = favItem,
                favoriteViewModel = favoriteViewModel,
                navController = navController
            )
        }
    }
}
