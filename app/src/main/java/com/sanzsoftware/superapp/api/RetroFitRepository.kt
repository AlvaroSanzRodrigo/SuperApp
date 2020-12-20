package com.sanzsoftware.superapp.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sanzsoftware.superapp.models.Character
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetroFitRepository(){
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://gateway.marvel.com/v1/public/characters")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCharacters(){
        var timeStamp = System.currentTimeMillis().toString()
        getRetrofit().create(Service::class.java).getCharacters(timeStamp, Auth.PUBLIC_KEY, Auth.getHash(timeStamp))
        MutableLiveData<List<Character>>(mutableList) as LiveData<List<Character>>
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