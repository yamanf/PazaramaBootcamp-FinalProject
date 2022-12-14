package com.yamanf.shoppingapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yamanf.shoppingapp.ui.auth.signin.SignInFragment
import com.yamanf.shoppingapp.ui.auth.signup.SignUpFragment

class AuthAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> { SignInFragment() }
            else -> { SignUpFragment() }
        }
    }
}