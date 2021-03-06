package com.sanzsoftware.superapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanzsoftware.superapp.adapters.CharacterAdapter
import com.sanzsoftware.superapp.api.RetroFitRepository
import com.sanzsoftware.superapp.database.CharacterDatabase
import com.sanzsoftware.superapp.fragments.CharacterDialog
import com.sanzsoftware.superapp.models.Character
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), CharacterAdapter.OnClickedItemListener, CharacterDialog.OnDismissDialog {

    var items: ArrayList<Character> = ArrayList()
    private var favs = false


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

        floatingActionButton.setOnClickListener{
            items.clear()
            if (favs) {
                floatingActionButton.setImageDrawable(
                        resources.getDrawable(
                                R.drawable.ic_outline_favorite_border_24,
                                theme
                        )
                )
                search(null)

                favs = !favs
            } else {
                floatingActionButton.setImageDrawable(
                        resources.getDrawable(
                                R.drawable.ic_baseline_favorite_full_24, theme
                        )
                )
                getRoomCharacter()
                favs = !favs
            }

        }
    }

    private fun getRoomCharacter(){
        items.clear()
        doAsync {
            for (character in CharacterDatabase.getInstance(context).characterDao().getAll()){
                items.add(character)
            }
            uiThread {
                superheroesRecyclerView.adapter?.notifyDataSetChanged()
            }
        }
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

    override var context: Context
        get() = this
        set(value) { context = value }

    override fun onItemSelected(character: Character) {
        character.name?.let { Log.i("RecyclerView", it) }
        CharacterDialog(character, this).show(this.supportFragmentManager, "Character")
    }

    override fun onLottieSelected(character: Character) {
        character.name?.let { Log.i("lottie", it) }
        if (character.isFavorite!!)
            doAsync { CharacterDatabase.getInstance(context).characterDao().delete(character) }
        else
            doAsync { CharacterDatabase.getInstance(context).characterDao().insert(character) }
    }

    override fun onDismiss(character: Character) {
        items[items.indexOf(character)] = character
        superheroesRecyclerView.adapter?.notifyDataSetChanged()
    }
}