package com.yamanf.shoppingapp.utils

import android.widget.ImageView

import com.bumptech.glide.Glide
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

object Utils {
        fun ImageView.downloadFromUrl(url: String?) {
            Glide.with(context).load(url).into(this)
        }

    suspend fun <T> Task<T>.await(): T {
        return suspendCancellableCoroutine { cont ->
            addOnCompleteListener {
                if (it.exception != null) {
                    cont.resumeWithException(it.exception!!)
                } else {
                    cont.resume(it.result, null)
                }
            }
        }
    }

}