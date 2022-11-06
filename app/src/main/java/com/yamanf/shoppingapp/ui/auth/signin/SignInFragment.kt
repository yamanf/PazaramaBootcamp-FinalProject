package com.yamanf.shoppingapp.ui.auth.signin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.yamanf.shoppingapp.MainActivity
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.databinding.FragmentSignInBinding
import com.yamanf.shoppingapp.databinding.FragmentSignUpBinding
import com.yamanf.shoppingapp.ui.auth.AuthActivity
import com.yamanf.shoppingapp.ui.auth.AuthViewModel
import com.yamanf.shoppingapp.ui.auth.signup.RegisterModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable

data class LoginModel(val eMail:String,val password:String): Serializable

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater,container,false)
        binding.btnSignIn.setOnClickListener{
            val loginModel = LoginModel(
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString())
            sharedViewModel.signIn(loginModel,{
                startActivity(Intent(requireContext(),MainActivity::class.java))
            },{
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }


}