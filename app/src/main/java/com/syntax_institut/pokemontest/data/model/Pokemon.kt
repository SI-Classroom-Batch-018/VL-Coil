package com.syntax_institut.pokemontest.data.model

// Klasse um ein einfaches Pokemon abzubilden
data class Pokemon (

    // Diese beiden Werte finden wir in der Response
    var name: String,
    val url: String,

    // Diese beiden Werte finden sich nicht in der Response sondern werden von uns nachträglich von uns befüllt
    var id: Int = 0,
    var image: String = "",
)