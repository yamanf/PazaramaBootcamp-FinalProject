package com.yamanf.shoppingapp.ui.auth


import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.yamanf.shoppingapp.utils.FirebaseManager
import com.yamanf.shoppingapp.ui.auth.signin.LoginModel
import com.yamanf.shoppingapp.ui.auth.signup.RegisterModel

class AuthViewModel: ViewModel() {
    fun signUp(registerModel: RegisterModel, success: (AuthResult) -> Unit, failure: (String) -> Unit) {
        FirebaseManager.signUp(registerModel, {
            success(it)
        },{
            failure(it)
        })
    }
    fun signIn(loginModel: LoginModel, success: (AuthResult) -> Unit, failure: (String) -> Unit) {
        FirebaseManager.signIn(loginModel,{
            success(it)
        },{
            failure(it)
        })
    }

}

