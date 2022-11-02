package com.yamanf.shoppingapp.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

object Utils {
        fun ImageView.downloadFromUrl(url: String?) {
            Glide.with(context).load(url).into(this)
        }

}