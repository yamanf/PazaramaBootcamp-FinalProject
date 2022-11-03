package com.yamanf.shoppingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.yamanf.shoppingapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.customgradient))
        supportActionBar?.setLogo(R.drawable.shop_app_logo_white)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        navController=Navigation.findNavController(this,R.id.fragmentContainerView)
        setupWithNavController(binding.bottomNavigationView,navController)
        navController.addOnDestinationChangedListener{_,destination,_  ->
            if (destination.id == R.id.productDetailFragment ) {
                binding.bottomNavigationView.visibility = View.GONE
            } else binding.bottomNavigationView.visibility = View.VISIBLE
        }

    }



}