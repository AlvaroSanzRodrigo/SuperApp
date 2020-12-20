package com.sanzsoftware.superapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitRepository(){
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/v1/public/characters")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object{

        @Volatile private var instance: RetroFitRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: RetroFitRepository().also {
                    instance = it
                }
            }

    }
}