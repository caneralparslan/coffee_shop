package com.example.coffee_shop.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.coffee_shop.R

@Composable
fun CommonAlertDialog(showDialog: MutableState<Boolean>,
                      titleResId: Int,
                      questionResId: Int,
                      confirmResId : Int = R.string.yes,
                      cancelResId: Int = R.string.cancel,
                      onConfirm: () -> Unit
) {

    if(showDialog.value){
        AlertDialog(
            onDismissRequest = {
                showDialog.value = false
            },

            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {

                    ElevatedButton(onClick = {
                        // Cancel
                        showDialog.value = false
                    }) {
                        Text(stringResource(cancelResId))
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    ElevatedButton(onClick = {
                        // Yes action
                        onConfirm.invoke()

                    }) {
                        Text(stringResource(confirmResId))
                    }
                }
            },
            title = { Text(
                stringResource(titleResId),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
            },
            text = { Text(
                stringResource(questionResId),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
            },
            containerColor = Color.LightGray
        )
    }
}