package com.yamanf.shoppingapp.ui.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.data.model.Products
import com.yamanf.shoppingapp.data.model.ProductsItem
import com.yamanf.shoppingapp.databinding.ItemProductListBinding
import com.yamanf.shoppingapp.ui.product.list.ProductFragmentDirections
import com.yamanf.shoppingapp.utils.Utils.downloadFromUrl

class ProductAdapter(val productList: Products) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(view: View): RecyclerView.ViewHolder(view){
        val ivProduct: ImageView = view.findViewById(R.id.ivProduct)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val container : ConstraintLayout = view.findViewById(R.id.container)

        @SuppressLint("SetTextI18n")
        fun bindItems(item: ProductsItem) {
            ivProduct.downloadFromUrl(item.image)
            tvTitle.text = item.title
            tvPrice.text = item.price.toString() + " TL"
            container.setOnClickListener{
                it.findNavController().navigate(
                    ProductFragmentDirections.actionProductFragmentToProductDetailFragment(item.id)
                )
                println("Adapter")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_list, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItems(productList[position])
    }
}



