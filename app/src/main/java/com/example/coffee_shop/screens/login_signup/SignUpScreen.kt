package com.example.coffee_shop.screens.login_signup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coffee_shop.R
import com.example.coffee_shop.components.ActionButton
import com.example.coffee_shop.components.EmailTextField
import com.example.coffee_shop.components.PasswordTextField
import com.example.coffee_shop.util.isCredentialsValid

@Composable
fun SignUpScreen(navController: NavController,
                 loginSignupViewModel: LoginSignupViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        SignUpContent(navController, loginSignupViewModel)
    }
}


@Composable
fun SignUpContent(navController: NavController,
                  loginSignupViewModel: LoginSignupViewModel) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val emailState = rememberSaveable {
        mutableStateOf("")
    }
    val passwordState = rememberSaveable{
        mutableStateOf("")
    }
    val rePasswordState = rememberSaveable{
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        // Logo
        Text(stringResource(R.string.app_name),
            style = TextStyle(
                fontSize = 48.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Red.copy(alpha = 0.8f)
            ),
            modifier = Modifier.padding(vertical = 70.dp)
        )

        // Email Field
        EmailTextField(focusManager, emailState)

        Spacer(Modifier.height(20.dp))

        // Password Field
        PasswordTextField(passwordState, focusManager)

        Spacer(Modifier.height(20.dp))

        // Re-Password Field
        PasswordTextField(rePasswordState, focusManager, imeAction = ImeAction.Done, isRePassword = true)

        Spacer(Modifier.height(20.dp))

        // Sign Up Button
        ActionButton(
            stringResource(R.string.sign_up),
        ) {
            keyboardController?.hide()

            if(isCredentialsValid(context, emailState, passwordState, rePasswordState )){
                loginSignupViewModel.createUserWithEmailAndPassword(
                    emailState.value,
                    passwordState.value,
                    navController,
                    context)
            }
        }

        Spacer(Modifier.height(80.dp))

        AlreadyMemberRow(navController)

        if(loginSignupViewModel.loading.collectAsState().value){
            CircularProgressIndicator()
        }

    }

}

@Composable
fun AlreadyMemberRow(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.already_registered),
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Black
            )
        )

        Spacer(Modifier.width(20.dp))

        Text(stringResource(R.string.login),
            style = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color(0xFF14A6F5)
            ),
            modifier = Modifier.clickable {
                navController.popBackStack()
            })
    }
}
