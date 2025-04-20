package com.example.coffee_shop.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.screens.cart.CartViewModel

@SuppressLint("DefaultLocale")
@Composable
fun CartItemTile(
    item: Item,
    itemCount: Int,
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(horizontal = 10.dp)
            .clickable {
                navController.navigate("item_details_screen/${item.id}")
            },
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ItemImageWithLoading(
                    item.imagePath,
                    Modifier.size(100.dp)
                        .clip(RoundedCornerShape(18.dp)))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxHeight().width(130.dp)
                        .padding(vertical = 30.dp, horizontal = 6.dp)
                ) {
                    Text(
                        stringResource(item.nameResId),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyle(fontSize = 20.sp,
                            fontWeight = FontWeight.Medium)
                    )

                    Text(
                        String.format("%.2f", item.price * itemCount) + "$",
                        style = TextStyle(fontSize = 16.sp)
                    )
                }

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = { cartViewModel.removeAll(item) },
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Icon", tint = Color.Red)
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = {
                            cartViewModel.removeItem(item)
                        }) {
                            Icon(imageVector = Icons.Default.Remove, contentDescription = "Subtract Icon")
                        }

                        Text(
                            modifier = Modifier.width(10.dp),
                            text = itemCount.toString(),
                            style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        )

                        IconButton(onClick = {
                            cartViewModel.addItem(item)
                        }) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
                        }
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
