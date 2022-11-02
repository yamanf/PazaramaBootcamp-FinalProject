package com.yamanf.shoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yamanf.shoppingapp.databinding.ActivityMainBinding
import com.yamanf.shoppingapp.ui.auth.AuthActivity
import com.yamanf.shoppingapp.ui.onboarding.OnboardingActivity
import com.yamanf.shoppingapp.ui.product.ProductFragment
import com.yamanf.shoppingapp.ui.profile.ProfileFragment
import com.yamanf.shoppingapp.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide() // Hiding SystemUI
        navController=Navigation.findNavController(this,R.id.fragmentContainerView)
        setupWithNavController(binding.bottomNavigationView,navController)
    }



}