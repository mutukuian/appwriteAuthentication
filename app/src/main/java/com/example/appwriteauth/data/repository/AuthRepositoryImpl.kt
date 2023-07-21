package com.example.appwriteauth.data.repository

import com.example.appwriteauth.domain.model.UserDomain
import com.example.appwriteauth.domain.repository.AuthRepository
import io.appwrite.ID
import io.appwrite.services.Account
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val appWriteAccount: Account
):AuthRepository{
    override suspend fun createUserWithEmailAndPassword(user: UserDomain) {
         appWriteAccount.create(ID.unique(),user.email,user.password,user.userName)
    }

    override suspend fun createUserWithGoogle(user: UserDomain) {
//        appWriteAccount.createOAuth2Session()
    }
}