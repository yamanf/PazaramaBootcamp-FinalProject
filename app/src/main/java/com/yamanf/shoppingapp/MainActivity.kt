package com.yamanf.shoppingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yamanf.shoppingapp.databinding.ActivityMainBinding
import com.yamanf.shoppingapp.ui.onboarding.OnboardingActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide() // Hiding SystemUI

    }
    fun onClick(view: View){
        when (view.id) {
            R.id.button -> {
                val intent =
                    Intent(applicationContext, OnboardingActivity::class.java)
                startActivity(intent)
            }
        }
    }
}