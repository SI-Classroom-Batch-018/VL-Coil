package com.syntax_institut.pokemontest.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.syntax_institut.pokemontest.data.model.Pokemon
import com.syntax_institut.pokemontest.data.model.PokemonDetail
import com.syntax_institut.pokemontest.data.model.Sprites
import com.syntax_institut.pokemontest.data.remote.PokeApi
import kotlinx.coroutines.delay
import java.util.Locale

class Repository(private val pokeApi: PokeApi) {

    // LiveData um eine Liste aus Pokemon zu halten
    private val _pokemon = MutableLiveData<List<Pokemon>>()
    val pokemon: LiveData<List<Pokemon>>
        get() = _pokemon

    // LiveData um ein einzelnes Pokemon zu halten
    private val _pokemonDetail = MutableLiveData<PokemonDetail>()
    val pokemonDetail: LiveData<PokemonDetail>
        get() = _pokemonDetail

    // LiveData die zeigt, ob die API gerade lädt
    private val _isApiLoading = MutableLiveData<Boolean>(false)
    val isApiLoading: LiveData<Boolean>
        get() = _isApiLoading

    // Funktion um alle Pokemon zu laden, dabei bekommen wir nur name und url des Pokemon
    suspend fun getPokemon() {
        val result = pokeApi.retrofitService.getPokemon(151, 0)

        // Schleife über alle Pokemon um die Werte id und image zu brechnen
        for (entry in result.results) {

            // Der Name wird verändert, der erste Buchstabe wird groß gemacht
            entry.name = entry.name.capitalize()

            // Die Id aus der url benutzt um daraus die Id zu axtrahieren
            val newId = entry.url.split("/").dropLast(1).last()

            // Die Werte für id und image werden gesetzt, dafür wird in der Url fürs Bild einfach die Id des Pokemon eingesetzt
            entry.id = newId.toInt()
            entry.image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$newId.png"
        }

        _pokemon.postValue(result.results)
    }

    // Funktion um Details eines einzelnen Pokemon zu laden
    suspend fun getPokemonDetails(id: Int) {

        // Es wird gesetzt, dass die Api jetzt lädt
        _isApiLoading.postValue(true)

        delay(2000)
        // Die Api Anfrage wird gestartet
        val result = pokeApi.retrofitService.getPokemonDetail(id)

        // Es wird gesetzt, dass die Api jetzt mit dem Laden fertig ist
        _isApiLoading.postValue(false)

        _pokemonDetail.postValue(result)
    }

    // Funktion um die Daten der LiveData auf Standardwerte zu setzen, damit beim längeren Laden nicht das alte Pokemon angezeigt wird
    fun dropData() {
        _pokemonDetail.value = PokemonDetail("", 0, 0, Sprites(""))
    }

}