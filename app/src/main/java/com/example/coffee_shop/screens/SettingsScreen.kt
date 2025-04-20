package com.example.coffee_shop.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.CommonAlertDialog
import com.example.coffee_shop.components.SettingsTile
import com.example.coffee_shop.components.TopBar
import com.example.coffee_shop.util.LanguagePreference
import com.google.firebase.auth.FirebaseAuth


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
            label = stringResource(R.string.order_history),
            settingsType = "order"
        ) {
            navController.navigate("orders_screen")
        }

        SettingsTile(
            label = stringResource(R.string.language),
            settingsType = "language",
            language = LanguagePreference.getLanguage(context = LocalContext.current)
        ) {
            //TODO change language
        }

        val showDialog = remember { mutableStateOf(false) }

        SettingsTile(
            label = stringResource(R.string.log_out_title),
            settingsType = "log_out",
        ) {
            showDialog.value = true
        }
        if(showDialog.value) CommonAlertDialog(
            showDialog = showDialog,
            titleResId = R.string.log_out_title,
            questionResId = R.string.log_out_question
        ){
            showDialog.value = false
            FirebaseAuth.getInstance().signOut()
            navController.navigate("login_screen"){
                popUpTo(0) {inclusive = true}
            }
        }


    }
}


