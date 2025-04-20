package com.example.coffee_shop.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coffee_shop.models.Favorite
import com.example.coffee_shop.screens.favorite.FavoriteViewModel


@Composable
fun FavoriteItemTile(
    favoriteItem: Favorite,
    favoriteViewModel: FavoriteViewModel,
    navController: NavController
){

    Log.d("Fav Item", "FavoriteItemTile: ${favoriteItem}")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable {
                navController.navigate("item_details_screen/${favoriteItem.id}")
            },
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            shape = RoundedCornerShape(18.dp),
            border = BorderStroke(width = 1.dp, color = Color.LightGray),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItemImageWithLoading(
                    favoriteItem.imagePath,
                    Modifier.size(100.dp)
                        .clip(RoundedCornerShape(18.dp)))

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(favoriteItem.nameResId), style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium))

                    Text("[${stringResource(favoriteItem.category.displayNameResId)}]",
                        style = TextStyle(fontSize = 14.sp, color = Color.Gray))

                    Spacer(Modifier.height(20.dp))

                    Text(favoriteItem.price.toString() + "$")
                }

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = {
                            favoriteViewModel.removeFavorite(favoriteItem.id)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Favorite,
                            contentDescription = "Fav Icon", tint = Color.Red)
                    }
                }
            }
        }
    }
}