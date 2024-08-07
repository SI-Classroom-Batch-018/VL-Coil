package com.syntax_institut.pokemontest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.syntax_institut.pokemontest.adapter.PokeAdapter
import com.syntax_institut.pokemontest.databinding.FragmentPokemonListBinding

class PokemonListFragment: Fragment() {

    private lateinit var binding: FragmentPokemonListBinding
    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.pokemon.observe(viewLifecycleOwner) {
            binding.rvPoke.adapter = PokeAdapter(it, viewModel)
        }
    }
}