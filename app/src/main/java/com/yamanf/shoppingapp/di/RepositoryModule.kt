package com.yamanf.shoppingapp.di

import com.yamanf.shoppingapp.data.api.ApiService
import com.yamanf.shoppingapp.data.repositories.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {
    @Provides
    fun getRepository(apiService: ApiService) = ApiRepository(apiService)
}
