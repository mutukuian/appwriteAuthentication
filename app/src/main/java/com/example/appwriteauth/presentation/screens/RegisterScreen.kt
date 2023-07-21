package com.example.appwriteauth.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appwriteauth.presentation.viewmodel.RegisterViewModel
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.material3.Snackbar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.appwriteauth.presentation.viewmodel.AuthResult
import javax.annotation.meta.When


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()
){

    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }

    val loaderVisible by remember {
        mutableStateOf(false)
    }

    val authResult by viewModel.authResultLiveData.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email")}
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password")},
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))


        when(authResult!!) {
            is AuthResult.Success -> {
                MessageText(error = false, text = "Successfully Registered User")
            }
            is AuthResult.Loading -> {
                CircularProgressIndicator(
                    color = Color.LightGray,
                    strokeWidth = 5.dp
                )
            }
            is AuthResult.Error -> {
                val result = authResult as AuthResult.Error
                MessageText(error = true, text = result.msg)
                Button(
                    onClick = {
                        viewModel.createUserWithEmailAndPassword(email, password, "") })
                {
                    Text(text = "Retry")
                }
            }

            is AuthResult.StandBy -> {
                Button(
                    onClick = {
                        viewModel.createUserWithEmailAndPassword(email, password, "") })
                {
                    Text(text = "Register")
                }
            }
        }

    }

}

@Preview
@Composable
fun MessageText(
    error: Boolean = false,
    text: String = "Success"
) {
    Text(
        modifier = Modifier
            .background(
                when (error) {
                    true -> Color(68, 0, 0, 255)
                    false -> Color(0, 82, 13, 255)
                }
            )
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        text = text,
        textAlign = TextAlign.Center,
        color = when(error) {
            true -> Color(255, 194, 194, 255)
            false -> Color(193, 255, 203, 255)
        }
    )
}

@Preview
@Composable
fun RegistrationPreview() {
    RegisterScreen()
}