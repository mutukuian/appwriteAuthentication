package com.example.appwriteauth.presentation


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.appwriteauth.presentation.screens.RegisterScreen
import com.example.appwriteauth.presentation.ui.theme.AppWriteAuthTheme
import com.example.appwriteauth.presentation.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel:RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AppWriteAuthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {
                   RegisterScreen(onRegisterClick = {email, password ->
                       viewModel.createUserWithEmailAndPassword(email,password,"")
                   }
                       )
                }
            }
        }
    }
}


