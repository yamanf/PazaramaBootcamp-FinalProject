package com.yamanf.shoppingapp.ui.product.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yamanf.shoppingapp.data.model.ProductsItem
import com.yamanf.shoppingapp.data.repositories.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {
    private val productDetailLiveData = MutableLiveData<ProductsItem>()
    val productDetail: LiveData<ProductsItem> = productDetailLiveData

    fun getProductDetail(productId: String) {
        viewModelScope.launch {
            val productDetail = apiRepository.getProductDetail(productId)
            productDetailLiveData.value = productDetail
        }
    }



}