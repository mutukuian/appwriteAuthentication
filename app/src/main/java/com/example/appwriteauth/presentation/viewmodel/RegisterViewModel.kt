package com.example.appwriteauth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.appwriteauth.domain.model.UserDomain

import com.example.appwriteauth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
):ViewModel(){

    sealed class AuthResult {
        object Success : AuthResult()
        data class Error(val message: String) : AuthResult()
    }

    private val _authResultLiveData = MutableLiveData<AuthResult>()
    val authResultLiveData: LiveData<AuthResult>
        get() = _authResultLiveData

    fun createUserWithEmailAndPassword(email: String, password: String,userName:String) {
        val user = UserDomain(email, password, userName,userName)
        GlobalScope.launch(Dispatchers.IO) {
            try {
               repository.createUserWithEmailAndPassword(user)
                _authResultLiveData.postValue(AuthResult.Success)
            } catch (e: Exception) {
                _authResultLiveData.postValue(AuthResult.Error(e.message ?: "Unknown error"))
            }
        }
    }
}
