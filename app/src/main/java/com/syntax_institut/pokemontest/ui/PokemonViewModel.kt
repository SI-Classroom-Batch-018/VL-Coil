package com.syntax_institut.pokemontest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntax_institut.pokemontest.data.Repository
import com.syntax_institut.pokemontest.data.model.Pokemon
import com.syntax_institut.pokemontest.data.model.PokemonDetail
import com.syntax_institut.pokemontest.data.remote.PokeApi
import kotlinx.coroutines.launch

class PokemonViewModel: ViewModel() {
    private val repository = Repository(PokeApi)
    val pokemon = repository.pokemon

    private val _detailPokemon = MutableLiveData<PokemonDetail>()
    val detailPokemon: LiveData<PokemonDetail>
        get() = _detailPokemon

    init {
        viewModelScope.launch {
            repository.getPokemon()
        }
    }

    fun loadDetailPokemon(name: String): LiveData<PokemonDetail> {
        val detailPokemon = MutableLiveData<PokemonDetail>()
        viewModelScope.launch {
            val result = repository.loadDetailPokemon(name.lowercase())
            detailPokemon.postValue(result)
        }
        return detailPokemon
    }

    fun setDetailViewPokemon(pokemon: PokemonDetail) {
        _detailPokemon.value = pokemon
    }
}