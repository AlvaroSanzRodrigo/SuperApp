package com.sanzsoftware.superapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanzsoftware.superapp.api.RetroFitRepository
import com.sanzsoftware.superapp.models.Character
import com.sanzsoftware.superapp.models.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync


class MainActivity : AppCompatActivity(), LifecycleObserver {
    private lateinit var viewModel: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            for (character in RetroFitRepository.getInstance().getCharacters().value!!){
                character.name?.let { Log.i("APIMARVEL", it) }
            }
        }
    }

    private fun loadData(){
        viewModel = ViewModelProvider.AndroidViewModelFactory(this.application).create(CharacterViewModel::class.java)
        viewModel.getCharacters()?.observe(this@MainActivity, Observer<List<Character>> {

        })
    }

    private fun bindAdapter(){
        superHeroesRecyclerView.layoutManager = LinearLayoutManager(this);
    }
}