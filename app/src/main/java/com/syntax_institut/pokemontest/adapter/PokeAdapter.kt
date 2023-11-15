package com.syntax_institut.pokemontest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.syntax_institut.pokemontest.data.model.Pokemon
import com.syntax_institut.pokemontest.databinding.ItemPokeBinding

class PokeAdapter(
    private val dataset: List<Pokemon>
): RecyclerView.Adapter<PokeAdapter.PokeViewHolder>() {

    inner class PokeViewHolder(val binding: ItemPokeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeViewHolder {
        val binding = ItemPokeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: PokeViewHolder, position: Int) {
        val item = dataset[position]

        holder.binding.tvPokeName.text = item.name
    }
}