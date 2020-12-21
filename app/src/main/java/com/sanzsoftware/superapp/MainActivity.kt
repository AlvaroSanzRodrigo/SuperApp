package com.sanzsoftware.superapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanzsoftware.superapp.adapters.CharacterAdapter
import com.sanzsoftware.superapp.api.RetroFitRepository
import com.sanzsoftware.superapp.models.Character
import com.sanzsoftware.superapp.models.CharacterViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.custom.onUiThread
import org.jetbrains.anko.doAsync


class MainActivity : AppCompatActivity(), CharacterAdapter.OnClickedItemListener {
    private lateinit var viewModel: CharacterViewModel
    private var items: ArrayList<Character> = ArrayList<Character>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        bindAdapter()
    }

    private fun loadData(){
        viewModel = ViewModelProvider.AndroidViewModelFactory(this.application).create(CharacterViewModel::class.java)

            viewModel.getCharacters()?.observe(this@MainActivity, Observer<List<Character>> {
                items.addAll(it)
            })

    }

    private fun bindAdapter(){
        superHeroesRecyclerView.layoutManager = LinearLayoutManager(this);
        superHeroesRecyclerView.adapter = CharacterAdapter(items)
    }

    override fun onItemSelected(character: Character) {
        character.name?.let { Log.i("ITEM", it) }
    }
}