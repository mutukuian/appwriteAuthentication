package com.example.appwriteauth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwriteauth.core.common.Resource

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
}


sealed class AuthResult {
    object StandBy: AuthResult()
    object Loading: AuthResult()
    object Success : AuthResult()
    data class Error(val msg: String) : AuthResult()
}