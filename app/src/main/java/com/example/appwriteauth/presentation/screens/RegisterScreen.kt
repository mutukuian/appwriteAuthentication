package com.example.appwriteauth.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel()// Use viewModel() to get the RegisterViewModel instance


){

    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }

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
        Button(onClick = { viewModel.createUserWithEmailAndPassword(email, password, "") }) {
            Text(text = "Register")
        }
    }




    val authResult by viewModel.authResultLiveData.observeAsState()
    authResult?.let { result ->
        when (result) {
            is RegisterViewModel.AuthResult.Success -> {
                // Show a SnackBar with a success message
                Snackbar {
                    Text(text = "Registration successful!")
                }
            }
            is RegisterViewModel.AuthResult.Error -> {
                // Show a SnackBar with an error message
                Snackbar {
                    Text(text = "Registration error: ${result.message}")
                }
            }
        }
    }
}