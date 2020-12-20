package com.sanzsoftware.superapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Service {
    @GET
    fun getCharacters(@Url url:String): Call<CharacterResponse>

}