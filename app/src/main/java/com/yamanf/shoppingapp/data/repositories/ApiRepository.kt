package com.yamanf.shoppingapp.data.repositories

import com.yamanf.shoppingapp.data.api.ApiService
import com.yamanf.shoppingapp.utils.FirebaseManager
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getProductList() = apiService.getAllProducts()
    suspend fun getProductDetail(id: String) = apiService.getProductDetail(id)
}