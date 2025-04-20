package com.example.coffee_shop.components

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.example.coffee_shop.data.itemsList
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.screens.cart.CartViewModel

@Composable
fun CartItemTile(
    item: Item,
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
){
    Box(
        modifier = Modifier.fillMaxWidth().height(140.dp)
            .padding(horizontal = 20.dp)
            .clickable {
                navController.navigate("item_details_screen/${item.id}")
            },
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxSize().padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = rememberAsyncImagePainter(item.imageResId),
                    contentDescription = "Cart Item Image",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(18.dp)))

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(item.nameResId), style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Medium))

                    Spacer(Modifier.height(20.dp))

                    Text(item.price.toString() + "$", style = TextStyle(fontSize = 16.sp))
                }

                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End
                ) {
                    IconButton(
                        onClick = {
                            cartViewModel.removeItem(item)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Icon", tint = Color.Red)
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color.Gray
            )
        }
    }
}