package com.syntax_institut.pokemontest

import android.graphics.Color

fun getColor(type: String): Int {
    return when(type) {
        "Grass" -> Color.argb(255, 99, 186, 110)
        "Fire" -> Color.argb(255, 219, 90, 94)
        "Water" -> Color.argb(255, 111, 143, 232)
        "Bug" -> Color.argb(255, 120, 220, 120)
        "Normal" -> Color.GRAY
        "Electric" -> Color.argb(255, 255, 252, 163)
        "Poison" -> Color.argb(255, 255, 43, 230)
        "Ground" -> Color.argb(255, 179, 103, 16)
        "Fairy" -> Color.argb(255, 250, 190, 243)
        "Psychic" -> Color.argb(255, 153, 59, 219)
        "Fighting" -> Color.argb(255, 240, 226, 213)
        "Rock" -> Color.argb(255, 184, 102, 26)
        "Ghost" -> Color.argb(255, 153, 59, 219)
        "Ice" -> Color.argb(255, 82, 200, 247)
        "Dragon" -> Color.argb(255, 247, 99, 82)
        else -> Color.BLACK
    }
}