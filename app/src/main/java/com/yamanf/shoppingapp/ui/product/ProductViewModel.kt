package com.yamanf.shoppingapp.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yamanf.shoppingapp.data.model.Products
import com.yamanf.shoppingapp.data.repositories.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val apiRepository: ApiRepository): ViewModel() {
    private val resultLiveData = MutableLiveData<Products>()
    val result: LiveData<Products> = resultLiveData

    init {
        println("ProductViewModel init")
        viewModelScope.launch {
            println("ProductViewModel viewModelScope.launch run")
            val resultList = apiRepository.getProductList()
            delay(1000)
            resultLiveData.value = resultList
        }
    }
}