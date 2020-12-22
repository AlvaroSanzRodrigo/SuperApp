package com.sanzsoftware.superapp

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanzsoftware.superapp.adapters.CharacterAdapter
import com.sanzsoftware.superapp.api.RetroFitRepository
import com.sanzsoftware.superapp.models.Character
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), CharacterAdapter.OnClickedItemListener {

    var items: ArrayList<Character> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerView()
        setSearch()
        search(null)
    }

    private fun setRecyclerView() {
        superheroesRecyclerView.layoutManager = LinearLayoutManager(this)
        superheroesRecyclerView.adapter = CharacterAdapter(items)
    }

    private fun setSearch(){
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                items.clear()
                superheroesRecyclerView.adapter?.notifyDataSetChanged()
                search(query)
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                items.clear()
                superheroesRecyclerView.adapter?.notifyDataSetChanged()
                search(newText)
                return false
            }
        })
    }

    fun search(query: String?): Boolean {
        doAsync {
            for (character in RetroFitRepository.getInstance().getCharacters("characters", query).value!!){
                items.add(character)
                uiThread {
                    superheroesRecyclerView.adapter?.apply {
                        notifyItemChanged(this.itemCount)
                    }
                }
            }
        }
        return true
    }

    override fun onItemSelected(character: Character) {
        character.name?.let { Log.i("RecyclerView", it) }
    }

    override fun onLottieSelected(character: Character) {
        character.name?.let { Log.i("lottie", it) }
    }
}