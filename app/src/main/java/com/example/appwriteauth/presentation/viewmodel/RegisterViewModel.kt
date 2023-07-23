package com.example.appwriteauth.presentation.viewmodel

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwriteauth.core.common.Resource

import com.example.appwriteauth.domain.model.UserDomain

import com.example.appwriteauth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.appwrite.services.Account

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val account: Account
):ViewModel(){

    private val _authResultLiveData = MutableLiveData<AuthResult>(AuthResult.StandBy)
    val authResultLiveData: LiveData<AuthResult>
        get() = _authResultLiveData

    fun createUserWithEmailAndPassword(email: String, password: String,userName:String) = viewModelScope.launch {
        repository.createUserWithEmailAndPassword(email, password, userName).collect {
            when(it) {
                is Resource.Loading -> _authResultLiveData.postValue(AuthResult.Loading)
                is Resource.Success -> _authResultLiveData.postValue(AuthResult.Success)
                is Resource.Error ->
                    _authResultLiveData.postValue(AuthResult.Error(it.message ?: "An unknown Error has occurred"))
            }
        }
    }


    fun authenticateWithGoogle(context: Context) = viewModelScope.launch {
        account.createOAuth2Session(
            activity = context as ComponentActivity,
            provider = "google",
            success = "https://jhunt.online/auth/oauth2/success",
            failure = "https://jhunt.online/auth/oauth2/failure"
        )
    }
}


sealed class AuthResult {
    object StandBy: AuthResult()
    object Loading: AuthResult()
    object Success : AuthResult()
    data class Error(val msg: String) : AuthResult()
}