package com.example.appwriteauth.data.repository

import com.example.appwriteauth.core.common.Resource
import com.example.appwriteauth.domain.model.UserDomain
import com.example.appwriteauth.domain.repository.AuthRepository
import io.appwrite.ID
import io.appwrite.services.Account
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val appWriteAccount: Account
):AuthRepository{
    override suspend fun createUserWithEmailAndPassword(email: String, password: String, userName: String) = flow {
        emit(Resource.Loading)

        // Validate the email address
        if (!isValidEmail(email)) {
            emit(Resource.Error("Invalid Email : ${email}"))
        } else {
            try {
                val userUniqueId = ID.unique()
                appWriteAccount.create(userUniqueId,email,password, when(userName) {
                    "" -> null
                    else -> userName
                })
                emit(Resource.Success(UserDomain(userUniqueId, email, password, userName)))
            } catch (error: Exception) {
                emit(Resource.Error(error.message.toString()))
            }

        }

    }

    override suspend fun createUserWithGoogle(user: UserDomain) = flow<Resource<UserDomain>> {
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