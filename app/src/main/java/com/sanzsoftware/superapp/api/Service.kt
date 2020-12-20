package com.sanzsoftware.superapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Service {
    @GET
    fun getCharacters(@Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("Hash") hash: String): Call<CharacterResponse>

}