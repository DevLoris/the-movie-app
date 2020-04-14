package com.gmail.eamosse.imdb.ui.actor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.ActorMovies
import com.gmail.eamosse.imdb.databinding.ActorMoviesListItemBinding


class ActorMoviesListAdapter(private val items: List<ActorMovies>, val handler: (trending:ActorMovies) -> Unit) :
    RecyclerView.Adapter<ActorMoviesListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ActorMoviesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ActorMovies) {
            binding.item = item

            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ActorMoviesListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
