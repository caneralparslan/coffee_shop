package com.example.coffee_shop.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.CategoryChip
import com.example.coffee_shop.components.ItemCard
import com.example.coffee_shop.data.itemsList
import com.example.coffee_shop.models.Item
import com.example.coffee_shop.models.ItemCategory

@Composable
fun HomeScreen(navController: NavController){

    Surface (
        modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp)
    ){
        HomeContent(navController)
    }

}

@Preview
@Composable
fun HomeContent(navController: NavController = rememberNavController()) {

    val searchQuery = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val selectedCategory = remember { mutableStateOf(ItemCategory.ALL) }
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 105.dp)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SearchBar(searchQuery)

        Spacer(Modifier.height(10.dp))
        Categories(selectedCategory)

        Spacer(Modifier.height(10.dp))
        ItemsContent(selectedCategory, navController)

    }
}

@Composable
fun ItemsContent(selectedCategory: MutableState<ItemCategory>,
                 navController: NavController) {


    val items = if (selectedCategory.value == ItemCategory.ALL) {
        itemsList.sortedBy { it.category.ordinal }
    } else {
        itemsList.filter { it.category == selectedCategory.value }
    }

    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("${items.size} ${stringResource(R.string.number_of_items)}",
            style = TextStyle(color = Color.Gray,
                fontSize = 13.sp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
        ) {
            items(items) { item ->
                ItemCard(item, navController)
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun Categories(selectedCategory: MutableState<ItemCategory>) {

    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(ItemCategory.entries.toTypedArray()) { category ->
            val isSelected = category == selectedCategory.value
            CategoryChip(
                category = category,
                isSelected = isSelected,
                onClick = {selectedCategory.value = category}
            )
        }
    }
}

@Composable
fun SearchBar(searchQuery: MutableState<String>){

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
        shape = RoundedCornerShape(20.dp),
        value = searchQuery.value,
        onValueChange = {
                changedValue ->
            searchQuery.value = changedValue
        },
        placeholder = {
            Text(stringResource(R.string.search_item), style = TextStyle(color = Color.LightGray))
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        trailingIcon = {
            Icon(imageVector = Icons.Default.Search, tint = Color.LightGray, contentDescription = "Search Icon")
        }

    )
}
