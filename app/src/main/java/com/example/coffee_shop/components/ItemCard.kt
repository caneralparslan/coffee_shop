package com.example.coffee_shop.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.rememberAsyncImagePainter
import com.example.coffee_shop.data.items
import com.example.coffee_shop.models.Item


@Preview
@Composable
fun ItemCard(
    item: Item = items[0],
    navController: NavController = rememberNavController()
){

    Box (
        modifier = Modifier.padding(15.dp)
    ){
        Card (
            modifier = Modifier.height(180.dp).width(150.dp)
                .clickable {
                    navController.navigate("item_details_screen/${item.id}")
                },
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(width = 1.dp, color = Color.LightGray),
            elevation = CardDefaults.cardElevation(4.dp)
        ){
            Column(
                modifier = Modifier.fillMaxSize().padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = rememberAsyncImagePainter(item.imagePath),
                    contentDescription = "Item Image",
                    modifier = Modifier.size(120.dp)
                        .clip(RoundedCornerShape(12.dp)))

                Text(item.name, style = TextStyle(fontSize = 12.sp))

                Row (
                    modifier = Modifier.fillMaxWidth().height(18.dp).padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(item.price.toString() + "$",
                        style = TextStyle(fontSize = 10.sp, color = Color.Black)
                    )
                    IconButton(
                        modifier = Modifier.size(22.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.LightGray
                        ),
                        onClick = {}
                    ) {
                        Icon(imageVector = Icons.Default.AddShoppingCart,
                            contentDescription = "Add Shopping Cart",
                            tint = Color.Black,
                            modifier = Modifier.size(10.dp))
                    }
                }
            }
        }
    }
}