package com.yamanf.shoppingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yamanf.shoppingapp.databinding.ActivitySplashBinding
import com.yamanf.shoppingapp.ui.onboarding.OnboardingActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val onBoarding: SharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE)
        var isFirstTime = onBoarding.getBoolean("firstTime",true)
        supportActionBar?.hide()

        binding.apply {
            shopLogo.animate().setDuration(2500).alpha(1f).withEndAction{
                if (isFirstTime){
                    val editor = onBoarding.edit()
                    editor.putBoolean("firstTime",false)
                    editor.apply()
                    val intent = Intent(this@SplashActivity, OnboardingActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }else{
                    val intent = Intent(this@SplashActivity, MainActivity::class.java)
                    startActivity(intent)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
            }
        }

    }
}