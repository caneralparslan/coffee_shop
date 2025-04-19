package com.example.coffee_shop.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController,
    title: String,
    isMain: Boolean
){

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ){
                Text(title,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Red,
                    )
                )
            }
        },
        navigationIcon = {
            if (!isMain){
                Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back Arrow",
                    tint = Color.Red,
                    modifier = Modifier.size(24.dp).clickable {
                        navController.popBackStack()
                    })
            }
            else{
                Spacer(Modifier.width(24.dp))
            }
        },
        actions = {
            if(isMain){
                IconButton(
                    onClick = {
                        navController.navigate("settings_screen")
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings Icons"
                    )
                }
            }
        }

    )
}