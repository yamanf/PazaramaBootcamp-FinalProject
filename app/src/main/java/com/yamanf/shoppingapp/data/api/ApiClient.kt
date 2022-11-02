package com.yamanf.shoppingapp.data.api

import com.yamanf.shoppingapp.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object{
    private lateinit var apiService: ApiService
    fun getApiService(): ApiService{
        println("getApiService run")
        if (!::apiService.isInitialized){
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }
    }
}