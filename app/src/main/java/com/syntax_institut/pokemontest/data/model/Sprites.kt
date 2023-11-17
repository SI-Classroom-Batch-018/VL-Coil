package com.syntax_institut.pokemontest.data.model

import com.squareup.moshi.Json

// Klasse für die Unterklasse Sprites in PokemonDetail
data class Sprites(
    @Json(name = "front_default")
    val image: String
)
