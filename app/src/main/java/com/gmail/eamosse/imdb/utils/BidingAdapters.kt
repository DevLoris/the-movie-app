package com.gmail.eamosse.imdb.utils

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.gmail.eamosse.idbdata.data.Movie


object BidingAdapters {
    @BindingAdapter("app:bindImage")
    @JvmStatic
    fun changeImage(view: AppCompatImageView, url: String?) {
        if(url == null) return
        Glide.with(view).load("https://image.tmdb.org/t/p/w500/$url")
            .centerCrop()
            .into(view)
    }
    @SuppressLint("SetTextI18n")
    @BindingAdapter("app:voteText")
    @JvmStatic
    fun voteText(view: TextView, movie: Movie?) {
        if(movie == null) return
        view.text = "${movie.vote_average} / 10 (${movie.vote_count} votes)"
    }
}