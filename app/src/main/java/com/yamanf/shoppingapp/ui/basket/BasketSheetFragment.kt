package com.yamanf.shoppingapp.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.databinding.FragmentBasketSheetBinding
import com.yamanf.shoppingapp.databinding.FragmentProductBinding
import com.yamanf.shoppingapp.ui.adapter.BasketAdapter
import com.yamanf.shoppingapp.utils.FirebaseManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

class BasketSheetFragment : BottomSheetDialogFragment(R.layout.fragment_basket_sheet) {
    private  var _binding : FragmentBasketSheetBinding? = null
    private val binding get() = _binding!!
    private val basketSheetViewModel: BasketSheetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBasketSheetBinding.bind(view)
        binding.btnClose.setOnClickListener(){
            dismiss()
        }
        binding.btnPurchase.setOnClickListener(){
            //FirebaseManager.clearAllBasket()
        }
        configureRecyclerView()


        basketSheetViewModel.calculateTotalPrice {
            binding.tvTotalPrice.text = "Total Price: $it TL"

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