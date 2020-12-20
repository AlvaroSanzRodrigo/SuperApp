package com.sanzsoftware.superapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sanzsoftware.superapp.api.RetroFitRepository
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            for (character in RetroFitRepository.getInstance().getCharacters().value!!){
                character.name?.let { Log.i("APIMARVEL", it) }
            }
        }
    }
}