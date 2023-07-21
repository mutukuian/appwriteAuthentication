package com.example.appwriteauth.data.mapper

import com.example.appwriteauth.data.model.UserDto
import com.example.appwriteauth.domain.model.UserDomain


fun UserDomain.toDto():UserDto{
    return UserDto(
        userId, email, password, userName
    )
}