package com.yamanf.shoppingapp.ui.basket

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.databinding.FragmentBasketSheetBinding
import com.yamanf.shoppingapp.ui.adapter.BasketAdapter
import com.yamanf.shoppingapp.ui.product.detail.ProductDetailFragmentArgs
import com.yamanf.shoppingapp.utils.FirebaseManager
import com.yamanf.shoppingapp.utils.Utils.round
import kotlin.math.roundToLong


class BasketSheetFragment : BottomSheetDialogFragment(R.layout.fragment_basket_sheet) {
    private  var _binding : FragmentBasketSheetBinding? = null
    private val binding get() = _binding!!
    private val basketSheetViewModel: BasketSheetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBasketSheetBinding.bind(view)

        configureRecyclerView()

        binding.btnClose.setOnClickListener(){
            dismiss()
        }

        basketSheetViewModel.calculateTotalPrice {
            val totalPrice = it.round(2)
            binding.tvTotalPrice.text = "Total Price: $totalPrice TL"
        }

        binding.btnPurchase.setOnClickListener(){
            FirebaseManager.clearBasket()
            Toast.makeText(requireContext(),"Congrats, order completed!",Toast.LENGTH_LONG).show()
            dismiss()
        }
    }

    private fun configureRecyclerView(){
        binding.rvBasketList.layoutManager = LinearLayoutManager(context)
        basketSheetViewModel.getUserBasket(){
            binding.rvBasketList.adapter= BasketAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}