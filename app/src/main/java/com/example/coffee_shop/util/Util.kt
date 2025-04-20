package com.example.coffee_shop.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.example.coffee_shop.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Util {

    companion object {
        private val validDomains = listOf("gmail.com", "yahoo.com", "outlook.com", "hotmail.com")

        fun isValidEmail(email: String): Boolean {
            val parts = email.split("@")
            return parts.size == 2 && validDomains.contains(parts[1]) && parts[0].isNotEmpty()
        }

        fun isValidPassword(password: String): Boolean{
            return password.length >= 6
        }
    }

}

fun isCredentialsValid(
    context: Context,
    emailState: MutableState<String>,
    passwordState: MutableState<String>,
    rePasswordState: MutableState<String>
): Boolean {
    val email = emailState.value.trim()
    val password = passwordState.value

    val isEmailValid = Util.isValidEmail(email)
    val isPasswordValid = Util.isValidPassword(password)
    val passwordsMatch = passwordState.value == rePasswordState.value

    if (!isEmailValid || !isPasswordValid || !passwordsMatch) {
        val messageResource = when {
            !isEmailValid -> R.string.email_warning
            !isPasswordValid -> R.string.password_warning
            else -> R.string.passwords_match_warning
        }

        Toast.makeText(context, context.getString(messageResource), Toast.LENGTH_SHORT).show()
        Log.d("LoginValidation", context.getString(messageResource))

        return false
    }

    return true
}


object LanguagePreference {

    private const val PREF_NAME = "app_prefs"
    private const val LANGUAGE_KEY = "app_language"

    fun setLanguage(context: Context, languageCode: String) {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(LANGUAGE_KEY, languageCode).apply()
    }

    fun getLanguage(context: Context): String {
        val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(LANGUAGE_KEY, "English") ?: "English"
    }
}

fun formatDate(timeMillis: Long): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) // Customize the format as needed
    val date = Date(timeMillis)
    return dateFormat.format(date)
}
