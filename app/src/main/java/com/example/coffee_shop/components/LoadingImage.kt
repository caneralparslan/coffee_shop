package com.example.coffee_shop.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter

@Composable
fun ItemImageWithLoading(itemImageUrl: String, modifier: Modifier) {
    val painter = rememberAsyncImagePainter(model = itemImageUrl)

    Box(
        modifier = modifier
    ) {
        val state = painter.state.collectAsState().value

        // Show loading indicator while the image is loading
        if (state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(24.dp),
                strokeWidth = 2.dp
            )
        }

        // Handle error state
        if (state is AsyncImagePainter.State.Error) {
            Icon(imageVector = Icons.Default.Coffee,
                contentDescription = "Coffee Icon",
                modifier = Modifier.fillMaxSize())
        }

        // Display the image once it's loaded
        Image(
            painter = painter,
            contentDescription = "Cart Item Image",
            modifier = Modifier.fillMaxSize()
        )
    }
}


