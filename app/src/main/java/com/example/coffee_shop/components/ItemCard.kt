package com.example.coffee_shop.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.screens.cart.CartViewModel


@Composable
fun ItemCard(
    item: Item,
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel()
){

    val context = LocalContext.current

    Box (
        modifier = Modifier.padding(15.dp)
            .clickable {
            navController.navigate("item_details_screen/${item.id}")
        },
    ){
        Card (
            modifier = Modifier.height(210.dp).width(170.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(width = 1.dp, color = Color.LightGray),
            elevation = CardDefaults.cardElevation(4.dp)
        ){
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ItemImageWithLoading(
                    item.imagePath,
                    Modifier.size(120.dp)
                        .clip(RoundedCornerShape(12.dp)))

                Text(stringResource(item.nameResId),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 15.sp))

                Row (
                    modifier = Modifier.fillMaxWidth().height(30.dp).padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(item.price.toString() + "$",
                        style = TextStyle(fontSize = 14.sp, color = Color.Black)
                    )
                    IconButton(
                        modifier = Modifier.size(36.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.LightGray
                        ),
                        onClick = {
                            Toast.makeText(context, context.getString(R.string.item_added), Toast.LENGTH_SHORT ).show()
                            cartViewModel.addItem(item)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.AddShoppingCart,
                            contentDescription = "Add Shopping Cart",
                            tint = Color.Black,
                            modifier = Modifier.size(15.dp))
                    }
                }
            }
        }
    }
}