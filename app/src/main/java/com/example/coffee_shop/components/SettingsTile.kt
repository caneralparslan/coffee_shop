package com.example.coffee_shop.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffee_shop.R


@Composable
fun SettingsTile(
    label: String,
    settingsType: String,
    language: String? = stringResource(R.string.english),
    onClick: () -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth().height(90.dp)
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .clickable {
                onClick.invoke()
            },
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {

        Row (
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(label,
                style = TextStyle(
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    color = Color(0xFF2C2C2C)
                )
            )

            when (settingsType) {
                "log_out" ->
                    Icon(imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                        contentDescription = "Log Out Icon",
                        tint = Color(0xFF2C2C2C))

                "language" ->
                    Text(language.toString())

                "order" ->
                    Icon(imageVector = Icons.Default.Coffee,
                        contentDescription = "Coffee Icon",
                        tint = Color(0xFF2C2C2C))
            }
        }
    }
}