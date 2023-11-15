package com.syntax_institut.pokemontest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntax_institut.pokemontest.data.Repository
import com.syntax_institut.pokemontest.data.remote.PokeApi
import kotlinx.coroutines.launch

class PokeViewModel: ViewModel() {

    private val repository = Repository(PokeApi)

    val pokemon = repository.pokemon

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        viewModelScope.launch {
            repository.getPokemon()
        }
    }

}