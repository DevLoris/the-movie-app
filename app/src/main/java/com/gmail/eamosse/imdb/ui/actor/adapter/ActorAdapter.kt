package com.gmail.eamosse.imdb.ui.actor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Actor
import com.gmail.eamosse.imdb.databinding.ActorListItemBinding


class ActorAdapter(private val items: List<Actor>, val handler: (person: Actor) -> Unit) :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ActorListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Actor) {
            binding.item = item

            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ActorListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}