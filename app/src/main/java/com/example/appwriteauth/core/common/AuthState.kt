package com.example.appwriteauth.core.common

data class AuthState(
    val isLoading: Boolean = false,
    val isSuccess: String = "",
    val isError: String = ""
)
