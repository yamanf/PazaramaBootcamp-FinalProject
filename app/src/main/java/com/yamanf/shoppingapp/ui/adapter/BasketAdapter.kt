package com.yamanf.shoppingapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.yamanf.shoppingapp.R
import com.yamanf.shoppingapp.utils.BasketItemModel
import com.yamanf.shoppingapp.utils.FirebaseManager
import com.yamanf.shoppingapp.utils.Utils.downloadFromUrl


class BasketAdapter(private val basketList: List<BasketItemModel>) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>(){


    class BasketViewHolder(view: View): RecyclerView.ViewHolder(view){
        // Items initialize here
        private val ivProduct: ImageView = view.findViewById(R.id.ivProduct)
        private val tvTitle: TextView = view.findViewById(R.id.tvBasketTitle)
        private val tvPrice: TextView = view.findViewById(R.id.tvBasketPrice)
        private val tvAmount : TextView = view.findViewById(R.id.tvBasketAmount)
        private val container : ConstraintLayout = view.findViewById(R.id.basketContainer)
        private val btnDelete : ImageView = view.findViewById(R.id.btnDelete)

        @SuppressLint("SetTextI18n")
        fun bindItems(item: BasketItemModel) {
            ivProduct.downloadFromUrl(item.imageUrl)
            tvTitle.text = item.name
            tvPrice.text = item.price.toString() + " TL"
            tvAmount.text = item.amount.toString() + " pcs"

            container.setOnClickListener(){
                //Navigate to product detail fragment will be added here.
            }

            //Deleting single product from basket
            btnDelete.setOnClickListener(){
            FirebaseManager.deleteItemFromBasket(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket_list, parent, false)
        return BasketViewHolder(view)
    }

    override fun getItemCount(): Int {
        return basketList.size
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bindItems(basketList[position])
    }


}
