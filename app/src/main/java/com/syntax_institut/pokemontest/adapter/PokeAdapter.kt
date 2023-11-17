package com.syntax_institut.pokemontest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.syntax_institut.pokemontest.R
import com.syntax_institut.pokemontest.data.model.Pokemon
import com.syntax_institut.pokemontest.databinding.ItemPokeBinding
import com.syntax_institut.pokemontest.ui.PokeViewModel

// Adapter um die Pokemon in der RecyclerView anzuzeigen
class PokeAdapter(
    private val dataset: List<Pokemon>,
    private val viewModel: PokeViewModel
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
        holder.binding.ivPokeImage.load(item.image)

        // Beim Klick auf eine CardView wird zum DetailFragment navigiert
        // Davor wird dem ViewModel die Id übergeben um die Daten des richtigen Pokemon zu laden
        holder.binding.cvPoke.setOnClickListener {
            viewModel.loadPokemonDetails(item.id)
            holder.itemView.findNavController().navigate(R.id.fragmentPokeDetail)
        }
    }
}