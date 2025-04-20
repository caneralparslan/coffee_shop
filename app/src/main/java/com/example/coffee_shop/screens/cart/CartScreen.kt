package com.example.coffee_shop.screens.cart

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.ActionButton
import com.example.coffee_shop.components.CartItemTile
import com.example.coffee_shop.components.CommonAlertDialog
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.models.Order
import com.example.coffee_shop.screens.orders.OrderViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun CartScreen(
    navController: NavController,
    cartViewModel: CartViewModel = hiltViewModel(),
    orderViewModel: OrderViewModel = hiltViewModel()
){

    Scaffold(
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)
    ) {
        innerPadding ->
        CartContent(innerPadding, navController, cartViewModel, orderViewModel)
    }

}

@SuppressLint("DefaultLocale")
@Composable
fun CartContent(innerPadding: PaddingValues,
                navController: NavController,
                cartViewModel: CartViewModel,
                orderViewModel: OrderViewModel) {

    val cartItems = cartViewModel.cartList.collectAsState().value
    val totalPrice = cartViewModel.totalPrice.collectAsState().value


    Column (
        modifier = Modifier.fillMaxSize().padding(top = innerPadding.calculateTopPadding(), bottom = 105.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        if(cartItems.isEmpty()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(stringResource(R.string.start_shopping),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Red.copy(alpha = 0.9f)
                    )
                )
            }
        }else{
            val groupedItems = cartItems.groupingBy { it.id }.eachCount()
            val uniqueItems = cartItems.distinctBy { it.id }

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(uniqueItems) { item ->
                    val quantity = groupedItems[item.id] ?: 1
                    CartItemTile(
                        item = item,
                        itemCount = quantity,
                        navController = navController,
                        cartViewModel = cartViewModel
                    )
                }
            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = Color.LightGray
        )

        CartTotal(totalPrice)
        OrderButton( cartItems, totalPrice, cartViewModel, orderViewModel)

    }
}


@SuppressLint("DefaultLocale")
@Composable
fun CartTotal(totalPrice: Double){

    Row (
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(stringResource(R.string.cart_total),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
        Text(String.format("%.2f", totalPrice) + "$")
    }
}

@Composable
fun OrderButton(cartItems: List<Item>,
                totalPrice: Double,
                cartViewModel: CartViewModel,
                orderViewModel: OrderViewModel){

    val context = LocalContext.current

    val showDialog = remember { mutableStateOf(false) }

    ActionButton(
        text = stringResource(R.string.order),
        buttonColor = if(cartItems.isEmpty()) Color(0xFFFF742C).copy(alpha = 0.3f) else Color(0xFFFF742C)
    ) {
        if(cartItems.isNotEmpty()) showDialog.value = true
        else Toast.makeText(context, context.getString(R.string.cart_empty), Toast.LENGTH_SHORT).show()
    }

    if(showDialog.value) CommonAlertDialog(
        showDialog = showDialog,
        titleResId = R.string.place_order_title,
        questionResId = R.string.place_order_question
    ){
        showDialog.value = false

        val order = Order(
            items = cartItems,
            totalPrice = totalPrice
        )
        orderViewModel.placeOrder(order)
        Toast.makeText(context, context.getString(R.string.order_received), Toast.LENGTH_SHORT).show()

        cartViewModel.clearCart()
    }
}