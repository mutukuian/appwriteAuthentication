package com.example.appwriteauth.domain.repository

import com.example.appwriteauth.domain.model.UserDomain



interface AuthRepository {


    suspend fun createUserWithEmailAndPassword(user:UserDomain)


    suspend fun createUserWithGoogle(user: UserDomain)
}