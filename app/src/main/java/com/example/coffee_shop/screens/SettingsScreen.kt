package com.example.coffee_shop.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.CommonAlertDialog
import com.example.coffee_shop.components.SettingsTile
import com.example.coffee_shop.components.TopBar
import com.example.coffee_shop.util.LanguagePreference
import com.example.coffee_shop.util.getLanguageNameFromCode
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SettingsScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopBar(
                navController,
                title = stringResource(R.string.settings),
                isMain = false
            )
        }
    ) { innerPadding ->
        SettingsContent(innerPadding, navController)
    }
}

@Composable
fun SettingsContent(
    innerPadding: PaddingValues,
    navController: NavController
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Order History
        OrderHistoryTile(navController)

        // Language
        ChangeLanguageTile()

        // Log out
        LogOutTile(navController)


    }
}

@Composable
fun ChangeLanguageTile(){
    val context = LocalContext.current
    val selectedLanguage = remember { mutableStateOf(getLanguageNameFromCode(LanguagePreference.getLanguage(context), context)) }
    val showLanguageDialog = remember { mutableStateOf(false) }

    SettingsTile(
        label = stringResource(R.string.language),
        settingsType = "language",
        language = selectedLanguage.value
    ) {
        showLanguageDialog.value = true
    }
    if (showLanguageDialog.value) {
        LanguageSelectionDialog(
            onDismiss = { showLanguageDialog.value = false },
            onLanguageSelected = { languageName, languageCode ->
                LanguagePreference.setLanguage(context, languageCode) // Save selected language
                selectedLanguage.value = languageName // Update displayed language
                showLanguageDialog.value = false

                LanguagePreference.applyAppLanguage(languageCode)

                // restart activity
                val activity = (context as? android.app.Activity)
                activity?.recreate()

            }
        )
    }
}

@Composable
fun OrderHistoryTile(navController: NavController){
    SettingsTile(
        label = stringResource(R.string.order_history),
        settingsType = "order"
    ) {
        navController.navigate("orders_screen")
    }
}

@Composable
fun LogOutTile(navController: NavController){
    val showDialog = remember { mutableStateOf(false) }

    SettingsTile(
        label = stringResource(R.string.log_out_title),
        settingsType = "log_out",
    ) {
        showDialog.value = true
    }

    if (showDialog.value) {
        CommonAlertDialog(
            showDialog = showDialog,
            titleResId = R.string.log_out_title,
            questionResId = R.string.log_out_question
        ) {
            showDialog.value = false
            FirebaseAuth.getInstance().signOut()
            navController.navigate("login_screen") {
                popUpTo(0) { inclusive = true }
            }
        }
    }
}

@Composable
fun LanguageSelectionDialog(
    onDismiss: () -> Unit,
    onLanguageSelected: (String, String) -> Unit
) {
    val languages = listOf(
        stringResource(R.string.english) to "en",
        stringResource(R.string.turkish) to "tr")
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.select_language),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center) },
        text = {
            Column {
                languages.forEach { (languageName, languageCode) ->
                    Text(
                        text = languageName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onLanguageSelected(languageName, languageCode)
                            }
                            .padding(16.dp)
                    )
                }
            }
        },
        confirmButton = { },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
        }
    )
}

