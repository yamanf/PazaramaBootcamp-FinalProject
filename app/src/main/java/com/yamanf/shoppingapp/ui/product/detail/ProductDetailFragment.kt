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
import com.yamanf.shoppingapp.utils.Constants
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

            // Quantity Picker
            var amount = 1
            binding.etAmount.text = amount.toString()
            binding.btnMinus.setOnClickListener(){
                if (amount>Constants.minItemAmount){
                    amount -= 1
                }
                binding.etAmount.text = amount.toString()
            }
            binding.btnPlus.setOnClickListener(){
                if (amount<Constants.maxItemAmount){
                    amount += 1
                }
                binding.etAmount.text = amount.toString()
            }

            // Adding to basket
            binding.btnAdd.setOnClickListener(){
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