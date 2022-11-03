package com.yamanf.shoppingapp.ui.product.list

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
        viewModelScope.launch {
            val resultList = apiRepository.getProductList()
            resultLiveData.value = resultList
        }
    }
}