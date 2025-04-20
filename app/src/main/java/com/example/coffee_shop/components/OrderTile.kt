package com.example.coffee_shop.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.models.Order
import com.example.coffee_shop.util.formatDate


@SuppressLint("DefaultLocale")
@Composable
fun OrderTile(
    order: Order
){
    val context = LocalContext.current

    // Group items by their nameResId and count occurrences
    val itemCounts = order.items.groupingBy { it.nameResId }
        .eachCount()

    Box(
        modifier = Modifier.fillMaxWidth().height(140.dp).padding(horizontal = 15.dp, vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            shape = RoundedCornerShape(10.dp),
            border = BorderStroke(width = 1.dp, color = Color.LightGray)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp, vertical = 10.dp)
            ) {
                Text(String.format("%.2f", order.totalPrice) + "$",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                )

                // Join items with their counts
                Text(
                    text = itemCounts.entries.joinToString(separator = ", ") {
                        val itemName = context.getString(it.key)
                        "$itemName x${it.value}"
                    },
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(formatDate(order.timestamp),
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color.Gray
                        )
                    )
                }
            }
        }
    }
}
