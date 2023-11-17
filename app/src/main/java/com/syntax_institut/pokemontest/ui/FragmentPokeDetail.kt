package com.syntax_institut.pokemontest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.syntax_institut.pokemontest.databinding.FragmentPokeDetailBinding

class FragmentPokeDetail: Fragment() {

    private lateinit var binding: FragmentPokeDetailBinding
    private val viewModel: PokeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokeDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Die PokemonDetails werden beobachtet
        viewModel.pokemonDetail.observe(viewLifecycleOwner) {
            binding.tvPokeDetailName.text = it.name
            binding.tvPokeHeigt.text = it.height.toString()
            binding.tvPokeWeight.text = it.weight.toString()
            binding.ivPokeDetailImage.load(it.sprites.image)
        }

        // Die LiveData, ob die Api aktuell läft wird beobachtet
        // Wenn die API lädt werden alle Views versteckt und die PrograssBar angezeigt
        // Wenn die API nicht mehr lädt werden alle Views angezeigt und die ProgressBar versteckt
        viewModel.isApiLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.tvPokeDetailName.visibility = View.INVISIBLE
                binding.tvPokeHeigt.visibility = View.INVISIBLE
                binding.tvPokeWeight.visibility = View.INVISIBLE
                binding.ivPokeDetailImage.visibility = View.INVISIBLE
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.tvPokeDetailName.visibility = View.VISIBLE
                binding.tvPokeHeigt.visibility = View.VISIBLE
                binding.tvPokeWeight.visibility = View.VISIBLE
                binding.ivPokeDetailImage.visibility = View.VISIBLE
                binding.pbLoading.visibility = View.GONE
            }
        }

    }

}