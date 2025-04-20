package com.example.coffee_shop.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.example.coffee_shop.R
import com.example.coffee_shop.components.TopBar
import com.example.coffee_shop.data.itemsList
import com.example.coffee_shop.models.Favorite
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.screens.favorite.FavoriteViewModel


@Preview
@Composable
fun ItemDetailsScreen(
    navController: NavController = rememberNavController(),
    itemId: String = "1",
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
){

    Scaffold(
        topBar = {
            TopBar(navController = navController,
                title = stringResource(R.string.item_details),
                isMain = false)
        }
    ) {
        innerPadding ->
        DetailsContent(innerPadding, itemId, favoriteViewModel)
    }



}

@Composable
fun DetailsContent(innerPadding: PaddingValues, itemId: String, favoriteViewModel: FavoriteViewModel) {

    // Since id is unique getting first item of the filtered list
    val item = itemsList.first { it.id == itemId }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(20.dp))
        FavContent(itemId, favoriteViewModel)

        Text(stringResource(item.nameResId), style = TextStyle(fontSize = 24.sp))
        Spacer(Modifier.height(20.dp))
        Image(painter = rememberAsyncImagePainter(item.imageResId),
            contentDescription = "Item Image",
            modifier = Modifier
                .size(250.dp)
                .clip(RoundedCornerShape(18.dp)))


        Spacer(Modifier.height(50.dp))
        DescriptionContent(item)

        Spacer(Modifier.height(50.dp))
        PriceAndCartContent(item)

    }
}

@Composable
fun DescriptionContent(item: Item){
    Text(stringResource(R.string.description),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            stringResource(item.descResId),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
    }
}

@Composable
fun FavContent(itemId: String, favoriteViewModel: FavoriteViewModel) {
    val favList by favoriteViewModel.favList.collectAsState()
    val item = itemsList.firstOrNull { it.id == itemId } ?: return

    val favItem = remember(item) {
        Favorite(
            id = item.id,
            nameResId = item.nameResId,
            descResId = item.descResId,
            price = item.price,
            imageResId = item.imageResId,
            category = item.category
        )
    }

    // Local state to reflect the change instantly
    var isFavorite by remember { mutableStateOf(favList.any { it.id == itemId }) }

    // Sync local state with actual DB state when favList updates
    LaunchedEffect(favList) {
        isFavorite = favList.any { it.id == itemId }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = {
                if (isFavorite) {
                    favoriteViewModel.removeFavorite(itemId)
                    isFavorite = false
                    Log.d("Removed", "FavContent: Item Removed")
                } else {
                    favoriteViewModel.addFavorite(favItem)
                    isFavorite = true
                    Log.d("Added", "FavContent: Item Added")
                }
            }
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Fav Icon",
                tint = Color.Red,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}


@Composable
fun PriceAndCartContent(item: Item){
    val context = LocalContext.current

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(stringResource(R.string.price),
                style = TextStyle(fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium)
            )
            Text(item.price.toString() + "$",
                style = TextStyle(fontSize = 20.sp)
            )
        }


        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(stringResource(R.string.add_to_cart),
                style = TextStyle(fontSize = 22.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium))

            IconButton(
                modifier = Modifier.size(36.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.LightGray
                ),
                onClick = {
                    Toast.makeText(context, context.getString(R.string.item_added), Toast.LENGTH_SHORT ).show()

                    //TODO add to cart
                }
            ) {
                Icon(imageVector = Icons.Default.AddShoppingCart,
                    contentDescription = "Add Shopping Cart",
                    tint = Color.Black,
                    modifier = Modifier.size(22.dp))
            }
        }
    }
}