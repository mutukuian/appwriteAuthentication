package com.example.appwriteauth.presentation.mapper

import com.example.appwriteauth.domain.model.UserDomain
import com.example.appwriteauth.presentation.model.UserPresentation

fun UserDomain.toPresentation() {
    UserPresentation(userId, email, userName?:"RandomUser")
}