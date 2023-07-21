package com.example.appwriteauth.core.di

import android.content.Context
import com.example.appwriteauth.core.common.Constants.DEV_ENV
import com.example.appwriteauth.core.common.Constants.END_POINT
import com.example.appwriteauth.core.common.Constants.PROJECT_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.appwrite.Client
import io.appwrite.services.Account
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun providesAppWriteClient(@ApplicationContext context: Context):Client{
        return Client(context)
            .setEndpoint(END_POINT)
            .setProject(PROJECT_ID)
            .setSelfSigned(DEV_ENV)
    }

    @Provides
    @Singleton
    fun providesAppWriteAccount(appWrite:Client):Account{
        return Account(appWrite)
    }

}