package com.yamanf.shoppingapp.ui.product.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.databinding.FragmentProductDetailBinding
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
        productDetailViewModel.productDetail.observe(viewLifecycleOwner) {
            binding.ivProduct.downloadFromUrl(it.image)
            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.description
            binding.productRating.rating = it.rating.rate.toFloat()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}