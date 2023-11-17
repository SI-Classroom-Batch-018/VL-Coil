package com.syntax_institut.pokemontest.data.model

// Klasse um die Werte eines Pokemon abzubilden
data class PokemonDetail(
    val name: String,
    val weight: Int,
    val height: Int,
    val sprites: Sprites,
)