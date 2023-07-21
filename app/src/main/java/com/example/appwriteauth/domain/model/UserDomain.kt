package com.example.appwriteauth.domain.model

data class UserDomain(
    val userId:String,
    val email:String,
    val password:String,
    val userName:String?
)
