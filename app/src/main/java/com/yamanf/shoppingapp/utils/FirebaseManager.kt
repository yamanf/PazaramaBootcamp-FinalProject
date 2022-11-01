package com.yamanf.shoppingapp.utils

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yamanf.shoppingapp.ui.auth.signin.LoginModel
import com.yamanf.shoppingapp.ui.auth.signup.RegisterModel


class FirebaseManager {

    companion object {

        fun signIn(loginModel: LoginModel, success: (AuthResult) -> Unit, failure: (String) -> Unit) {

            if(loginModel.eMail== ""||loginModel.password== ""){
                failure("Please fill the areas.")
            }else{
                Firebase.auth.signInWithEmailAndPassword(loginModel.eMail, loginModel.password).addOnSuccessListener {
                    success(it)
                } .addOnFailureListener {
                    failure(it.localizedMessage.toString())
                }
            }

        }

        fun signUp(registerModel: RegisterModel, success: (AuthResult) -> Unit, failure: (String) -> Unit) {
            if(registerModel.userName== ""||registerModel.eMail== ""||registerModel.password== ""||registerModel.passwordRepeat== ""){
                failure("Please fill the areas.")
            }else{
                if (registerModel.password == registerModel.passwordRepeat){
                    Firebase.auth.createUserWithEmailAndPassword(registerModel.eMail, registerModel.password).addOnSuccessListener {
                        success(it)
                    } .addOnFailureListener {
                        failure(it.localizedMessage.toString())
                    }
                }else failure ("Passwords should be same.")
            }

        }
        fun isUserSignIn():Boolean{
            return Firebase.auth.currentUser != null
        }

/*
        fun signUp(registerModel: RegisterModel) :String {
            var message = ""
            if(registerModel.userName== ""||registerModel.eMail== ""||registerModel.password== ""||registerModel.passwordRepeat== ""){
                message = "Please fill the areas."
            }else{
                if (registerModel.password == registerModel.passwordRepeat){
                    Firebase.auth.createUserWithEmailAndPassword(registerModel.eMail, registerModel.password)
                        .addOnFailureListener { message = it.localizedMessage?.toString() ?: "Test" }
                }else{
                    message ="Passwords should be same."
                }
            }

            return message
        }

 */



    }
}