package com.yamanf.shoppingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.google.firebase.ktx.Firebase
import com.yamanf.shoppingapp.databinding.ActivityMainBinding
import com.yamanf.shoppingapp.ui.auth.AuthActivity
import com.yamanf.shoppingapp.ui.auth.AuthViewModel
import com.yamanf.shoppingapp.ui.basket.BasketSheetFragment
import com.yamanf.shoppingapp.ui.basket.BasketSheetViewModel
import com.yamanf.shoppingapp.utils.FirebaseManager
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val MainViewModel: MainViewModel by viewModels()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        navController=Navigation.findNavController(this,R.id.fragmentContainerView)
        setupWithNavController(binding.bottomNavigationView,navController)
        navController.addOnDestinationChangedListener{_,destination,_  ->
            if (destination.id == R.id.productDetailFragment ) {
                binding.bottomNavigationView.visibility = View.GONE
            } else binding.bottomNavigationView.visibility = View.VISIBLE
        }

        navController.addOnDestinationChangedListener{_,destination,_  ->
            if (destination.id == R.id.profileFragment ) {
                binding.btnSignOut.visibility = View.VISIBLE
                binding.btnBasket.visibility = View.GONE
            } else {
                binding.btnSignOut.visibility = View.GONE
                binding.btnBasket.visibility = View.VISIBLE
            }
        }

        navController.addOnDestinationChangedListener{_,destination,_  ->
            if (destination.id == R.id.productDetailFragment ) {
                binding.btnBack.visibility = View.VISIBLE
            } else binding.btnBack.visibility = View.GONE
        }

        binding.btnBack.setOnClickListener(){
            navController.navigate(R.id.productFragment)
        }

        binding.btnSignOut.setOnClickListener(){
            signOut()
        }

        binding.btnBasket.setOnClickListener(){
            BasketSheetFragment().show(supportFragmentManager, "basketTag")
        }

    }


    private fun signOut(){
        val builder: AlertDialog.Builder? = this.let {
            AlertDialog.Builder(it)
        }

        builder!!.setMessage("Are you sure you want to sign out?")
            .setTitle("Sign out")

        builder.apply {
            setPositiveButton("No") { dialog, id ->
                println("Sign out cannot performed")
            }
            setNegativeButton("Sign out") { dialog, id ->
                FirebaseManager.signOut()
                startActivity(Intent(this@MainActivity,AuthActivity::class.java))
            }
        }
        val dialog: AlertDialog? = builder.create()

        dialog!!.show()

    }



}