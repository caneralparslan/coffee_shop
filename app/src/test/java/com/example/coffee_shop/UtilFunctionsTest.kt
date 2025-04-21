package com.example.coffee_shop

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.mutableStateOf
import com.example.coffee_shop.util.LanguagePreference
import com.example.coffee_shop.util.Util
import com.example.coffee_shop.util.getLanguageNameFromCode
import com.example.coffee_shop.util.isCredentialsValid
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UtilFunctionsTest {

    private val mockContext: Context = mockk(relaxed = true)
    private val mockSharedPreferences: SharedPreferences = mockk(relaxed = true)

    @Before
    fun setUp() {
        mockkStatic("android.widget.Toast")
        every { Toast.makeText(any(), any<CharSequence>(), any()) } returns mockk(relaxed = true) {
            every { show() } returns Unit
        }
        every { mockContext.getSharedPreferences(any(), any()) } returns mockSharedPreferences
        every { mockContext.getString(R.string.email_warning) } returns "Invalid email"
        every { mockContext.getString(R.string.password_warning) } returns "Invalid password"
        every { mockContext.getString(R.string.passwords_match_warning) } returns "Passwords don't match"
        every { mockContext.getString(R.string.turkish) } returns "Turkish"
        every { mockContext.getString(R.string.english) } returns "English"

        mockkStatic("android.util.Log")
        every { Log.d(any(), any()) } returns 0

    }

    @After
    fun tearDown() {
        unmockkStatic("android.widget.Toast")
        unmockkStatic("android.util.Log")
    }

    @Test
    fun `isValidEmail should return true for valid emails`() {
        assertTrue(Util.isValidEmail("test@gmail.com"))
        assertTrue(Util.isValidEmail("user.name@yahoo.com"))
        assertTrue(Util.isValidEmail("another_user@outlook.com"))
        assertTrue(Util.isValidEmail("myemail@hotmail.com"))
    }

    @Test
    fun `isValidEmail should return false for invalid emails`() {
        assertFalse(Util.isValidEmail("invalid"))
        assertFalse(Util.isValidEmail("test@invalid"))
        assertFalse(Util.isValidEmail("test@com"))
        assertFalse(Util.isValidEmail("@gmail.com"))
        assertFalse(Util.isValidEmail("test@.com"))
    }

    @Test
    fun `isValidPassword should return true for valid password`() {
        assertTrue(Util.isValidPassword("123456"))
        assertTrue(Util.isValidPassword("password123"))
    }

    @Test
    fun `isValidPassword should return false for invalid password`() {
        assertFalse(Util.isValidPassword("12345"))
        assertFalse(Util.isValidPassword("pass"))
    }

    @Test
    fun `isCredentialsValid should return true for valid credentials`() {
        val emailState = mutableStateOf("test@gmail.com")
        val passwordState = mutableStateOf("123456")
        val rePasswordState = mutableStateOf("123456")

        assertTrue(isCredentialsValid(mockContext, emailState, passwordState, rePasswordState))
        verify(exactly = 0) { Toast.makeText(any(), any<CharSequence>(), any()) }
    }

    @Test
    fun `isCredentialsValid should return false and show toast for invalid email`() {
        val emailState = mutableStateOf("invalid")
        val passwordState = mutableStateOf("123456")
        val rePasswordState = mutableStateOf("123456")

        assertFalse(isCredentialsValid(mockContext, emailState, passwordState, rePasswordState))
        verify { Toast.makeText(mockContext, "Invalid email", Toast.LENGTH_SHORT) }
    }

    @Test
    fun `isCredentialsValid should return false and show toast for invalid password`() {
        val emailState = mutableStateOf("test@gmail.com")
        val passwordState = mutableStateOf("12345")
        val rePasswordState = mutableStateOf("12345")

        assertFalse(isCredentialsValid(mockContext, emailState, passwordState, rePasswordState))
        verify { Toast.makeText(mockContext, "Invalid password", Toast.LENGTH_SHORT) }
    }

    @Test
    fun `isCredentialsValid should return false and show toast for non-matching passwords`() {
        val emailState = mutableStateOf("test@gmail.com")
        val passwordState = mutableStateOf("123456")
        val rePasswordState = mutableStateOf("different")

        assertFalse(isCredentialsValid(mockContext, emailState, passwordState, rePasswordState))
        verify { Toast.makeText(mockContext, "Passwords don't match", Toast.LENGTH_SHORT) }
    }

    @Test
    fun `setLanguage should set language preference`() {
        val editor: SharedPreferences.Editor = mockk(relaxed = true)
        every { mockSharedPreferences.edit() } returns editor
        every { editor.putString(any(), any()) } returns editor
        every { editor.apply() } returns Unit

        LanguagePreference.setLanguage(mockContext, "tr")

        verify { editor.putString("app_language", "tr") }
        verify { editor.apply() }
    }

    @Test
    fun `getLanguage should return stored language or default`() {
        every { mockSharedPreferences.getString("app_language", "en") } returns "tr"
        assertEquals("tr", LanguagePreference.getLanguage(mockContext))

        every { mockSharedPreferences.getString("app_language", "en") } returns null
        assertEquals("en", LanguagePreference.getLanguage(mockContext))
    }

    @Test
    fun `getLanguageNameFromCode should return correct language name`() {
        assertEquals("Turkish", getLanguageNameFromCode("tr", mockContext))
        assertEquals("English", getLanguageNameFromCode("en", mockContext))
        assertEquals("English", getLanguageNameFromCode("other", mockContext))
    }

}