package com.yamanf.shoppingapp.data.api

import com.yamanf.shoppingapp.data.model.Products
import com.yamanf.shoppingapp.data.model.ProductsItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getAllProducts():Products

    @GET("products/{product_id}")
    suspend fun getProductDetail(@Path("product_id") id: String): ProductsItem

}