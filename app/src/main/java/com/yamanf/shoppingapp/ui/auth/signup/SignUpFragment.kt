package com.yamanf.shoppingapp.ui.auth.signup


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.yamanf.shoppingapp.MainActivity
import com.yamanf.shoppingapp.databinding.FragmentSignUpBinding
import com.yamanf.shoppingapp.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable


data class RegisterModel(val userName:String, val eMail:String,val password:String,val passwordRepeat:String): Serializable

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: AuthViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater,container,false)
        binding.btnSignUp.setOnClickListener{
            val registerModel = RegisterModel(
                binding.etUsername.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString(),
                binding.etPasswordRepeat.text.toString())
            sharedViewModel.signUp(registerModel, {
                startActivity(Intent(requireContext(),MainActivity::class.java))
            },{
                Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
            })

        }
        return binding.root
    }




}