package com.yamanf.shoppingapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.databinding.ActivityAuthBinding
import com.yamanf.shoppingapp.ui.adapter.AuthAdapter

class AuthActivity : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager2
    private lateinit var authText: TextView
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide() // Hiding SystemUI
        mViewPager = binding.authViewPager
        val adapter = AuthAdapter(supportFragmentManager,lifecycle)
        binding.authViewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.authViewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Sign In"
                }
                1 -> {
                    tab.text = "Sign Up"
                }
            }
        }.attach()
        authText = binding.authText
        mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position==0){
                    authText.text = "Sign In"
                }else{
                    authText.text = "Sign Up"
                }
            }
            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(arg0: Int) {}
        })
    }
}