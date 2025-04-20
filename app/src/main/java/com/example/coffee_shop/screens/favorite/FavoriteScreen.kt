package com.example.coffee_shop.screens.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    val favItems = favoriteViewModel.favList.collectAsState().value

    if (favItems.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(stringResource(R.string.start_adding_fav),
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Red.copy(alpha = 0.9f)
                )
            )
        }
    }else{
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding(), bottom = 105.dp)
        ) {
            items(favItems){
                    favItem ->
                FavoriteItemTile(
                    favoriteItem = favItem,
                    favoriteViewModel = favoriteViewModel,
                    navController = navController
                )
            }
        }
    }
}