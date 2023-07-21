package com.example.appwriteauth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appwriteauth.core.common.AuthState

import com.example.appwriteauth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
):ViewModel(){
    private val _registerState = Channel<AuthState>()
    val registerState = _registerState.receiveAsFlow()

    fun registerUser(email:String,password:String) = viewModelScope.launch {
//        repository.createUserWithEmailAndPassword()
    }
}