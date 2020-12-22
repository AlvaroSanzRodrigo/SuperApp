package com.sanzsoftware.superapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanzsoftware.superapp.api.RetroFitRepository
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            for (character in RetroFitRepository.getInstance().getCharacters("characters", null).value!!){
                character.name?.let { Log.i("APIMARVEL", it) }
            }
        }

    }

    fun setRecyclerView() {
        superheroesRecyclerView.layoutManager = LinearLayoutManager(this)
        
    }
}