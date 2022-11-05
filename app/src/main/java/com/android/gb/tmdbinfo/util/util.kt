package com.android.gb.tmdbinfo.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .override(400, 400).centerInside()
        .skipMemoryCache(true)
        .transform(FitCenter())
        .into(this)
}