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

        // Validate the email address
        if (!isValidEmail(user.email)) {
            throw IllegalArgumentException("Invalid email address")
        }

         appWriteAccount.create(ID.unique(),user.email,user.password,user.userName)
    }

    override suspend fun createUserWithGoogle(user: UserDomain) {
//        appWriteAccount.createOAuth2Session()
    }


    // Function to validate the email address using Appwrite's rules
    private fun isValidEmail(email: String): Boolean {
        // Implement the email validation logic here.
        // You can use regular expressions or other methods to check if the email is valid.
        // Example regular expression for email validation (not necessarily Appwrite's exact rules):
        val emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
        return email.matches(Regex(emailRegex))
    }
}