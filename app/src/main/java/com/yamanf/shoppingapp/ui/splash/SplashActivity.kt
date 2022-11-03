package com.yamanf.shoppingapp.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yamanf.shoppingapp.MainActivity
import com.yamanf.shoppingapp.databinding.ActivitySplashBinding
import com.yamanf.shoppingapp.ui.auth.AuthActivity
import com.yamanf.shoppingapp.ui.onboarding.OnboardingActivity
import com.yamanf.shoppingapp.utils.FirebaseManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val onBoarding: SharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE)
        val isFirstTime = onBoarding.getBoolean("firstTime",true)
        supportActionBar?.hide()

        binding.apply {
            shopLogo.animate().setDuration(500).alpha(1f).withEndAction{
                if (FirebaseManager.isUserSignIn()&&isFirstTime==false){
                    startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    finish()
                }else if(FirebaseManager.isUserSignIn()==false && isFirstTime==false){
                    startActivity(Intent(this@SplashActivity,AuthActivity::class.java))
                    finish()
                }else if (isFirstTime==true){
                    val editor = onBoarding.edit()
                    editor.putBoolean("firstTime",false)
                    editor.apply()
                    startActivity(Intent(this@SplashActivity, OnboardingActivity::class.java))
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }

            }
        }

    }
}