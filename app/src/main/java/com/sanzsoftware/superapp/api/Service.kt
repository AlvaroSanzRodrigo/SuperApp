package com.sanzsoftware.superapp.api

import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Service {
    @GET
    fun getCharacters(@Url url:String, @Query("nameStartsWith") search: String?, @Query("ts") ts: String, @Query("apikey") apiKey: String, @Query("hash") hash: String): Call<CharacterResponse>

}