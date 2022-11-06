package com.yamanf.shoppingapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.data.model.Products
import com.yamanf.shoppingapp.data.model.ProductsItem
import com.yamanf.shoppingapp.ui.product.list.ProductFragmentDirections
import com.yamanf.shoppingapp.ui.search.SearchFragmentDirections
import com.yamanf.shoppingapp.utils.Utils.downloadFromUrl

class SearchAdapter(private var searchList: Products) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(),
    Filterable {

    class SearchViewHolder(view: View): RecyclerView.ViewHolder(view){
        // Items initialize here
        private val ivProduct: ImageView = view.findViewById(R.id.ivSearchProduct)
        private val tvTitle: TextView = view.findViewById(R.id.tvSearchTitle)
        private val tvPrice: TextView = view.findViewById(R.id.tvSearchPrice)
        private val container: ConstraintLayout = view.findViewById(R.id.searchContainer)

        @SuppressLint("SetTextI18n")
        fun bindItems(item: ProductsItem) {
            ivProduct.downloadFromUrl(item.image)
            tvTitle.text = item.title
            tvPrice.text = item.price.toString() + " TL"
            container.setOnClickListener{
                it.findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToProductDetailFragment(item.id))
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search_list, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bindItems(searchList[position])
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}