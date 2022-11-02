package com.yamanf.shoppingapp.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.databinding.FragmentProductBinding
import com.yamanf.shoppingapp.ui.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment(R.layout.fragment_product) {
    lateinit var ProductAdapter: ProductAdapter
    private val viewModel: ProductViewModel by viewModels()
    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductBinding.bind(view)
        println("ProductFragment run")


        viewModel.result.observe(viewLifecycleOwner) {
            println("ProductFragment viewModel.result.observe run")
            binding.rvProductList.layoutManager = GridLayoutManager(context, 2)
            binding.rvProductList.adapter = ProductAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}