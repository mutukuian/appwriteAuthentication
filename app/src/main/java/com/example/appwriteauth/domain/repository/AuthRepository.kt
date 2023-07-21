package com.example.appwriteauth.domain.repository

import com.example.appwriteauth.core.common.Resource
import com.example.appwriteauth.domain.model.UserDomain
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    suspend fun createUserWithEmailAndPassword(email: String, password: String, userName: String): Flow<Resource<Any>>
    suspend fun createUserWithGoogle(user: UserDomain): Flow<Resource<Any>>
}