package com.syntax_institut.pokemontest.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.syntax_institut.pokemontest.data.model.PokeResult
import com.syntax_institut.pokemontest.data.model.PokemonDetail
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://pokeapi.co/api/v2/"

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PokeApiService {

    @GET("pokemon")
    suspend fun getPokemon(@Query("limit") limit: Int = 151): PokeResult

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(@Path("name") name: String): PokemonDetail
}

object PokeApi {
    val retrofitService: PokeApiService by lazy { retrofit.create(PokeApiService::class.java) }
}