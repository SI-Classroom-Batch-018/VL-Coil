package com.syntax_institut.pokemontest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.syntax_institut.pokemontest.data.model.Pokemon
import com.syntax_institut.pokemontest.data.model.PokemonDetail
import com.syntax_institut.pokemontest.data.remote.PokeApi

class Repository(private val pokeApi: PokeApi) {
    private val _pokemon = MutableLiveData<List<Pokemon>>()
    val pokemon: LiveData<List<Pokemon>>
        get() = _pokemon

    suspend fun getPokemon() {
        val result = pokeApi.retrofitService.getPokemon(151)
        _pokemon.postValue(result.results)
    }

    suspend fun loadDetailPokemon(name: String): PokemonDetail {
        val result = pokeApi.retrofitService.getPokemonDetail(name)
        return result
    }
}