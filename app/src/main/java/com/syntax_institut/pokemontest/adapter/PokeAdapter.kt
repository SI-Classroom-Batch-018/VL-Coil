package com.syntax_institut.pokemontest.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.syntax_institut.pokemontest.R
import com.syntax_institut.pokemontest.data.model.Pokemon
import com.syntax_institut.pokemontest.databinding.ItemPokeBinding
import com.syntax_institut.pokemontest.getColor
import com.syntax_institut.pokemontest.ui.PokemonViewModel

class PokeAdapter(
    private val dataset: List<Pokemon>,
    private val viewModel: PokemonViewModel
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

        viewModel.loadDetailPokemon(item.name).observe(holder.itemView.context as LifecycleOwner) {
            holder.binding.tvName.text = it.name.capitalize()
            val type = it.types.first().type.name.capitalize()
            holder.binding.tvType.text = type
            holder.binding.tvNumber.text = (position + 1).toString()
            holder.binding.ivPokemon.load(it.sprites.image) {
                placeholder(R.drawable.ic_launcher_background)
            }
            holder.binding.cvPokemon.setCardBackgroundColor(getColor(type))

            holder.binding.cvPokemon.setOnClickListener { click ->
                viewModel.setDetailViewPokemon(it)
                holder.itemView
                    .findNavController()
                    .navigate(R.id.fragmentPokeDetail)
            }
        }


    }
}