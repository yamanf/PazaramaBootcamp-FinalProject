package com.yamanf.shoppingapp.utils

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.yamanf.shoppingapp.data.model.ProductsItem
import com.yamanf.shoppingapp.ui.auth.signin.LoginModel
import com.yamanf.shoppingapp.ui.auth.signup.RegisterModel



data class UserModel(val uuid:String = "",val email:String = "",val username:String = "")
data class BasketItemModel(val amount: Double = 0.0, val name: String = "", val price: Double = 0.0, val imageUrl: String= "")

class FirebaseManager {

    companion object{

        // Authentication Operations

        fun signUp(registerModel: RegisterModel, success: (AuthResult) -> Unit, failure: (String) -> Unit) {
            if(registerModel.userName== ""||registerModel.eMail== ""||registerModel.password== ""||registerModel.passwordRepeat== ""){
                failure("Please fill the areas.")
            }else{
                if (registerModel.password == registerModel.passwordRepeat){
                    Firebase.auth.createUserWithEmailAndPassword(registerModel.eMail, registerModel.password).addOnSuccessListener {
                        success(it)
                    } .addOnFailureListener {
                        failure(it.localizedMessage!!.toString())
                    }
                }else failure ("Passwords should be same.")
            }
        }

        fun saveUsername(uuid: String, username: String, email: String, result: (Boolean) -> Unit) {
            Firebase.firestore.collection("users").document(uuid).set(
                mapOf(
                    "username" to username,
                    "uuid" to uuid,
                    "email" to email
                )
            ).addOnSuccessListener {
                result(true)
            }.addOnFailureListener {
                result(false)
                println("Username cannot saved.")
            }
        }

        fun getUserInformation(success: (UserModel) -> Unit){
            val uid = Firebase.auth.currentUser?.uid.toString()
            Firebase.firestore.collection("users").document(uid).get().addOnSuccessListener {
               val userModel = it.toObject<UserModel>()
                success(userModel!!)
            }.addOnFailureListener {
                println("FirebaseManager: ${it.localizedMessage}")
            }
        }


        fun signIn(loginModel: LoginModel, success: (AuthResult) -> Unit, failure: (String) -> Unit) {
            if(loginModel.eMail== ""||loginModel.password== ""){
                failure("Please fill the areas.")
            }else{
                Firebase.auth.signInWithEmailAndPassword(loginModel.eMail, loginModel.password).addOnSuccessListener {
                    success(it)
                } .addOnFailureListener {
                    failure(it.localizedMessage!!.toString())
                }
            }
        }

        fun isUserSignIn():Boolean{
            return Firebase.auth.currentUser != null
        }

        fun signOut(){
            Firebase.auth.signOut()
        }

        // Basket Operations
        fun saveToBasket(id: String, amount : Double , name: String , price: Double,imageUrl: String, result: (Boolean) -> Unit){
            val uid = Firebase.auth.currentUser?.uid.toString()
            Firebase.firestore.collection("users").document(uid).collection("basketItems").document(id).set(
                mapOf(
                    "amount" to amount,
                    "name" to name,
                    "price" to price,
                    "imageUrl" to imageUrl
                )
            ).addOnSuccessListener {
                result(true)
            }.addOnFailureListener {
                result(false)
                println("Item cannot saved to basket.")
            }
        }

        fun getUserBasket(basketList: (List<BasketItemModel>) -> Unit){
            val uid = Firebase.auth.currentUser?.uid.toString()
            Firebase.firestore.collection("users").document(uid).collection("basketItems").get()
                .addOnSuccessListener {
                    val docRef = it
                    val basketList = docRef.documents.map {
                        BasketItemModel(
                            amount = it.get("amount") as Double,
                            name = it.get("name") as String,
                            price = it.get("price") as Double,
                            imageUrl = it.get("imageUrl") as String
                        )
                    }
                    basketList(basketList)
            }.addOnFailureListener {
                println("FirebaseManager: ${it.localizedMessage}")
            }
        }

        fun deleteItemFromBasket(id:String){
            val uid = Firebase.auth.currentUser?.uid.toString()
            Firebase.firestore.collection("users").document(uid).collection("basketItems").document("id").delete()
        }





    }
}



