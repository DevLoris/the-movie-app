package com.gmail.eamosse.imdb.ui.trending.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.Person
import com.gmail.eamosse.imdb.databinding.TrendingActorListItemBinding

class TrendingPeopleAdapter(private val items: List<Person>, val handler: (person:Person) -> Unit) :
    RecyclerView.Adapter<TrendingPeopleAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: TrendingActorListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Person) {
            binding.item = item

            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(TrendingActorListItemBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}