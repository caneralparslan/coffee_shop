package com.example.coffee_shop

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffee_shop.navigation.CoffeeShopNavigation
import com.example.coffee_shop.screens.MainScreen
import com.example.coffee_shop.ui.theme.CoffeeShopTheme
import com.example.coffee_shop.util.LanguagePreference
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun attachBaseContext(newBase: Context?) {
        val languageCode = LanguagePreference.getLanguage(newBase!!)
        val context = LanguagePreference.wrapContextWithLanguage(newBase, languageCode)
        super.attachBaseContext(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoffeeShopTheme {
                CoffeeShopNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoffeeShopTheme {
        CoffeeShopNavigation()
    }
}



@HiltAndroidApp
class CoffeeShopApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        val languageCode = LanguagePreference.getLanguage(this)
        LanguagePreference.applyAppLanguage(languageCode)

        FirebaseApp.initializeApp(this)
        MobileAds.initialize(this) {}

    }
}

