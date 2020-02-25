package com.gmail.eamosse.imdb.ui.trending.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Category
import com.gmail.eamosse.idbdata.data.Trending
import com.gmail.eamosse.imdb.databinding.TrendingCategoryListItemBinding
import com.gmail.eamosse.imdb.databinding.TrendingMovieListItemBinding

class TrendingMovieAdapter(private val items: List<Trending>, val handler: (trending:Trending) -> Unit) :
    RecyclerView.Adapter<TrendingMovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: TrendingMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Trending) {
            binding.item = item

            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(TrendingMovieListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}