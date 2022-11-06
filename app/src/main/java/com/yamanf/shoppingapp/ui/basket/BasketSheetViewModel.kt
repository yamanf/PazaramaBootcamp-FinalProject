package com.yamanf.shoppingapp.ui.basket


import androidx.lifecycle.ViewModel
import com.yamanf.shoppingapp.utils.BasketItemModel
import com.yamanf.shoppingapp.utils.FirebaseManager



class BasketSheetViewModel(): ViewModel()  {

    fun getUserBasket(success: (List<BasketItemModel>) -> Unit){
        FirebaseManager.getUserBasket {
            success(it)
        }
    }
    fun calculateTotalPrice(totalPrice: (Double) -> Unit){
        FirebaseManager.getUserBasket { list ->
            var totalPrice = 0.0
            totalPrice = list.sumOf { it.price * it.amount }
            println(totalPrice)
            totalPrice(totalPrice)
        }
    }
}