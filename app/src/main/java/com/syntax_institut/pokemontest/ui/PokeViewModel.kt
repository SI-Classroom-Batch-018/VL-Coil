package com.syntax_institut.pokemontest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syntax_institut.pokemontest.data.Repository
import com.syntax_institut.pokemontest.data.remote.PokeApi
import kotlinx.coroutines.launch

class PokeViewModel: ViewModel() {

    // Instanz des Repository wird erstellt
    private val repository = Repository(PokeApi)

    // Die LiveData Objekte aus dem Repository werden übernommen
    val pokemon = repository.pokemon
    val pokemonDetail = repository.pokemonDetail
    val isApiLoading = repository.isApiLoading

    // Beim Starten der App werden direkt die Pokemon geladen
    init {
        loadPokemon()
    }

    // Funktion um alle Pokemon zu laden
    private fun loadPokemon() {
        viewModelScope.launch {
            repository.getPokemon()
        }
    }

    // Funktion um Details eines Pokemon zu laden
    fun loadPokemonDetails(id: Int) {

        // Vor dem Laden werden die alten Daten gelöscht, damit nicht das letzte Pokemon angezeigt wird
        repository.dropData()

        viewModelScope.launch {
            repository.getPokemonDetails(id)
        }
    }

}