package com.gmail.eamosse.imdb.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object BidingAdapters {
    @BindingAdapter("app:bindImage")
    @JvmStatic
    fun changeImage(view: AppCompatImageView, url: String) {
        Glide.with(view).load("https://image.tmdb.org/t/p/w500/$url").into(view)
    }
}