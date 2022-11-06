package com.yamanf.shoppingapp.ui.product.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.databinding.FragmentProductDetailBinding
import com.yamanf.shoppingapp.utils.FirebaseManager
import com.yamanf.shoppingapp.utils.Utils.downloadFromUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {
    private val productDetailViewModel: ProductDetailViewModel by viewModels()
    private val args: ProductDetailFragmentArgs by navArgs()
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProductDetailBinding.bind(view)
        productDetailViewModel.getProductDetail(args.productId.toString())
        productDetailViewModel.productDetail.observe(viewLifecycleOwner) { ProductsItem ->
            val productsItem = ProductsItem
            binding.ivProduct.downloadFromUrl(ProductsItem.image)
            binding.tvTitle.text = ProductsItem.title
            binding.tvDescription.text = ProductsItem.description
            binding.productRating.rating = ProductsItem.rating.rate.toFloat()

            // Num picker
            binding.numberPicker.maxValue = 10
            binding.numberPicker.minValue = 1
            binding.btnAdd.setOnClickListener(){
                val amount = binding.numberPicker.value
                FirebaseManager.saveToBasket(productsItem.id.toString(),amount.toDouble(),productsItem.title,productsItem.price,productsItem.image){
                    if (it){
                        Toast.makeText(requireContext(),"$amount item added to basket.",Toast.LENGTH_SHORT).show()
                    }else Toast.makeText(requireContext(),"Upps! Something goes wrong.",Toast.LENGTH_SHORT).show()
                }
                val navController= Navigation.findNavController(requireActivity(),R.id.fragmentContainerView)
                navController.navigate(R.id.productFragment)
            }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}