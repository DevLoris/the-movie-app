package com.gmail.eamosse.imdb.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.SimilarMovie
import com.gmail.eamosse.imdb.databinding.SimilarMovieListItemBinding

class SimilarMovieAdapter(private val items: List<SimilarMovie>, val handler: (discover:SimilarMovie) -> Unit) :
    RecyclerView.Adapter<SimilarMovieAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: SimilarMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SimilarMovie) {
            binding.item = item

            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(SimilarMovieListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}