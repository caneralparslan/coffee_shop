package com.example.coffee_shop.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.SettingsTile
import com.example.coffee_shop.components.TopBar
import com.example.coffee_shop.util.LanguagePreference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun SettingsScreen(navController: NavController){

    Scaffold(
        topBar = {
            TopBar(
                navController,
                title = stringResource(R.string.settings),
                isMain = false
            )
        }
    ) {
        innerPadding ->
        SettingsContent(innerPadding, navController)
    }
}

@Composable
fun SettingsContent(innerPadding: PaddingValues, navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize().padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SettingsTile(
            label = "Language",
            settingsType = "language",
            language = LanguagePreference.getLanguage(context = LocalContext.current)
        ) {
            //TODO change language
        }

        val logOutDialog = remember { mutableStateOf(false) }

        SettingsTile(
            label = "Log Out",
            settingsType = "log_out",
        ) {
            logOutDialog.value = true
        }
        if(logOutDialog.value) LogOutAlertDialog(logOutDialog, navController )
    }
}


@Composable
fun LogOutAlertDialog(logOutDialog: MutableState<Boolean>,
                          navController: NavController) {

    if(logOutDialog.value){
        AlertDialog(
            onDismissRequest = {
                logOutDialog.value = false
            },

            confirmButton = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    ElevatedButton(onClick = {
                        // Yes action
                        logOutDialog.value = false
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("login_screen"){
                            popUpTo(0) {inclusive = true}
                        }

                    }) {
                        Text("Yes")
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    ElevatedButton(onClick = {
                        // Cancel
                        logOutDialog.value = false
                    }) {
                        Text("Cancel")
                    }
                }
            },
            title = { Text("Log Out",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
            },
            text = { Text("Are you sure to log out?",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth())
            },
            containerColor = Color.LightGray
        )
    }
}