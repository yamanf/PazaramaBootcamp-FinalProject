package com.yamanf.shoppingapp.ui.auth


import androidx.lifecycle.ViewModel
import com.google.firebase.auth.AuthResult
import com.yamanf.shoppingapp.utils.FirebaseManager
import com.yamanf.shoppingapp.ui.auth.signin.LoginModel
import com.yamanf.shoppingapp.ui.auth.signup.RegisterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.UUID

class AuthViewModel: ViewModel() {
    fun signUp(registerModel: RegisterModel, success: (Boolean) -> Unit, failure: (String) -> Unit) {
        FirebaseManager.signUp(registerModel, {
            saveUsername(it.user!!.uid,registerModel.userName,registerModel.eMail)
            success(true)
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

    fun saveUsername(uuid: String,username: String, email: String) {
        FirebaseManager.saveUsername(uuid,username,email) {
            println("AuthViewModel saveUsername: $it")
        }
    }

}

