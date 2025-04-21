package com.example.coffee_shop.screens.login_signup

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.models.MUser
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginSignupViewModel: ViewModel(){

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        navController: NavController,
        context: Context
    ) {
        _loading.value = true

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _loading.value = false

                if (task.isSuccessful) {
                    Log.d("User Login Successful", "loginWithEmailAndPassword: ${task.result}")
                    navController.navigate("main_screen")
                } else {
                    Log.d("Login Unsuccessful", "loginWithEmailAndPassword: ${task.exception?.message}")
                }
            }
            .addOnFailureListener { exception ->
                _loading.value = false
                Toast.makeText(context, context.getString(R.string.incorrect_credentials), Toast.LENGTH_SHORT).show()
                Log.e("Login Error", "Exception: ${exception.localizedMessage}")
            }
    }

    fun createUserWithEmailAndPassword(email: String,
                                       password: String,
                                       navController: NavController,
                                       context: Context) {
        _loading.value = true

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _loading.value = false

                if (task.isSuccessful) {
                    val userName = task.result.user?.email?.split("@")?.get(0)
                    createUserToFirestoreDb(userName!!)

                    Log.d("User Create Successful", "createUserWithEmailAndPassword: ${task.result}")
                    navController.navigate("main_screen")
                }
                else {
                    Log.d("User Create Unsuccessful", "createUserWithEmailAndPassword: ${task.exception?.message}")
                }
            }
            .addOnFailureListener { exception ->
                _loading.value = false
                Toast.makeText(context, exception.localizedMessage, Toast.LENGTH_SHORT).show()
                Log.e("Signup Error", "Exception: ${exception.localizedMessage}")
            }
    }


    fun createUserToFirestoreDb(userName: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            val user = MUser(
                userId = currentUser.uid,
                userName = userName
            ).toMap()

            FirebaseFirestore.getInstance()
                .collection("users")
                .document(currentUser.uid)
                .set(user)
                .addOnSuccessListener {
                    Log.d("Firestore", "User successfully written!")
                }
                .addOnFailureListener { e ->
                    Log.w("Firestore", "Error writing user", e)
                }

        } else {
            Log.e("Firestore", "createUserToFirestoreDb: currentUser is null")
        }
    }

}

