package com.yamanf.shoppingapp.di

import com.yamanf.shoppingapp.data.api.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun getAPI() = ApiClient.getApiService()
}
