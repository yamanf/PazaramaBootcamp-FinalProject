package com.yamanf.shoppingapp.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.ui.onboarding.OnboardingFragment

class OnboardingAdapter(
    fragmentActivity: FragmentActivity,
    private val context: Context) : FragmentStateAdapter(fragmentActivity)  {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.onboarding1_title),
                context.resources.getString(R.string.onboarding1_desc),
                R.raw.onboarding1
            )
            1 -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.onboarding2_title),
                context.resources.getString(R.string.onboarding2_desc),
                R.raw.onboarding2
            )
            else -> OnboardingFragment.newInstance(
                context.resources.getString(R.string.onboarding3_title),
                context.resources.getString(R.string.onboarding3_desc),
                R.raw.onboarding3
            )
        }
    }

    override fun getItemCount(): Int {
        return 3
    }




}