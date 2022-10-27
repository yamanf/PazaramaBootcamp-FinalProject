package com.yamanf.shoppingapp.ui.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.yamanf.shoppingapp.MainActivity
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.databinding.ActivityOnboardingBinding
import com.yamanf.shoppingapp.ui.adapter.OnboardingAdapter

class OnboardingActivity : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager2
    private lateinit var btnBack: Button
    private lateinit var btnNext: Button
    private lateinit var tvSkip: TextView
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide() // Hiding SystemUI
        val dotsIndicator = findViewById<DotsIndicator>(R.id.pageIndicator)
        mViewPager = binding.viewPager
        mViewPager.adapter = OnboardingAdapter(this, this)
        mViewPager.offscreenPageLimit = 1
        btnBack = binding.btnPreviousStep
        btnNext = binding.btnNextStep
        tvSkip = binding.textSkip
        dotsIndicator.attachTo(mViewPager)
        binding.textSkip.setOnClickListener {
            finish()
            val intent =
                Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        mViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position==0){
                    btnBack.visibility = View.GONE
                }else{
                    btnBack.visibility = View.VISIBLE
                }

                if (position == 2) {
                    btnNext.text = getText(R.string.finish)
                    tvSkip.visibility = View.GONE
                } else {
                    btnNext.text = getText(R.string.next)
                    tvSkip.visibility = View.VISIBLE
                }
            }
            override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
            override fun onPageScrollStateChanged(arg0: Int) {}
        })

        btnNext.setOnClickListener {
            if (getItem() > mViewPager.childCount) {
                finish()
            } else {
                mViewPager.setCurrentItem(getItem() + 1, true)
            }
        }

        btnBack.setOnClickListener {
            if (getItem() == 0) {
                finish()
            } else {
                mViewPager.setCurrentItem(getItem() - 1, true)
            }
        }
    }

    private fun getItem(): Int {
        return mViewPager.currentItem
    }

}