package com.syntax_institut.pokemontest.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import coil.size.Precision
import coil.size.Scale
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import coil.transform.Transformation
import coil.transition.Transition
import com.syntax_institut.pokemontest.R
import com.syntax_institut.pokemontest.databinding.FragmentPokeDetailBinding
import com.syntax_institut.pokemontest.getColor
import kotlin.math.round

class FragmentPokeDetail: Fragment() {

    private lateinit var binding: FragmentPokeDetailBinding
    private val viewModel: PokemonViewModel by activityViewModels()

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

        viewModel.detailPokemon.observe(viewLifecycleOwner) {
            binding.tvNameDetail.text = it.name.capitalize()
            binding.tvHeightDetail.text = it.height.toString()
            binding.tvWeigtDetail.text = it.weight.toString()
            val type = it.types.first().type.name.capitalize()
            binding.tvTypeDetail.text = type
            binding.ivDetail.load(it.sprites.image) {
                crossfade(1500)
                precision(Precision.EXACT)
            }
            binding.root.setBackgroundColor(getColor(type))
        }


    }

}