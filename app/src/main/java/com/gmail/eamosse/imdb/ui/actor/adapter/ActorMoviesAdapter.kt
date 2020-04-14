package com.gmail.eamosse.imdb.ui.actor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Discover
import com.gmail.eamosse.imdb.databinding.ActorMovieListItemBinding

class ActorMoviesAdapter(private val items: List<Discover>, val handler: (trending:Discover) -> Unit) :
    RecyclerView.Adapter<ActorMoviesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ActorMovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Discover) {
            binding.item = item

            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ActorMovieListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}