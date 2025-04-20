package com.example.coffee_shop.screens.orders

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.OrderTile
import com.example.coffee_shop.components.TopBar

@Composable
fun OrdersScreen(navController: NavController,
                 orderViewModel: OrderViewModel = hiltViewModel()){

    Scaffold(
        topBar = {
            TopBar(
                navController,
                title = stringResource(R.string.order_history),
                isMain = false
            )
        }
    ) {
            innerPadding ->
        OrdersContent(innerPadding, orderViewModel)
    }

}

@Composable
fun OrdersContent(innerPadding: PaddingValues,
                  orderViewModel: OrderViewModel) {

    val orderList = orderViewModel.orderList.collectAsState().value.reversed()

    LazyColumn (
        modifier = Modifier.padding(innerPadding)
    ){
        items(orderList){
            order ->
            OrderTile(order)
        }
    }
}
