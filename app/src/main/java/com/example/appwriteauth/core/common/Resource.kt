package com.example.appwriteauth.core.common

sealed class Resource<out T: Any?>(val data:T? = null, val message:String? = null){
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data, message)
    object Loading : Resource<Nothing>()
}