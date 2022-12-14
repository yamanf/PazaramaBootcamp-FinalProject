package com.yamanf.shoppingapp.data.model

import com.google.gson.annotations.SerializedName


data class ProductsItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("title")
    val title: String,
    @SerializedName("rating")
    val rating: Rating
){
    data class Rating(
        @SerializedName("count")
        val count: Int,
        @SerializedName("rate")
        val rate: Double
    )
}