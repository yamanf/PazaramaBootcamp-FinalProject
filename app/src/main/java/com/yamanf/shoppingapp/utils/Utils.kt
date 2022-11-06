package com.yamanf.shoppingapp.utils

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException
import kotlin.math.round

object Utils {

    fun ImageView.downloadFromUrl(url: String?) {
        Glide.with(context).load(url).into(this)
    }
    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }

}