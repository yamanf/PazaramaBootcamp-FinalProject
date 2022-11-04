package com.yamanf.shoppingapp.ui.profile

import androidx.lifecycle.ViewModel
import com.yamanf.shoppingapp.utils.FirebaseManager
import com.yamanf.shoppingapp.utils.UserModel




class ProfileViewModel: ViewModel() {

    fun getUserData(success: (UserModel) -> Unit) {
        FirebaseManager.getUserInformation {
            success(it)
        }
    }
}