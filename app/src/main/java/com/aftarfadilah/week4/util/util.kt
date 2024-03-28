package com.aftarfadilah.week4.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.aftarfadilah.week4.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class util {

}

fun ImageView.loadImage(url: String?, progressBar: ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_android_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
                // Do something when Error
            }
        })

}