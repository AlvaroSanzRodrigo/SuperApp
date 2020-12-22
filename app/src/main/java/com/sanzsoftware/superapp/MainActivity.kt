package com.sanzsoftware.superapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
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
        doAsync {
            for (character in RetroFitRepository.getInstance().getCharacters("characters", null).value!!){
                items.add(character)
                uiThread {
                    superheroesRecyclerView.adapter?.apply {
                        notifyItemChanged(this.itemCount)
                    }
                }

            }
        }

    }

    fun setRecyclerView() {
        superheroesRecyclerView.layoutManager = LinearLayoutManager(this)
        superheroesRecyclerView.adapter = CharacterAdapter(items)
    }

    override fun onItemSelected(character: Character) {
        character.name?.let { Log.i("RecyclerView", it) }
    }

    override fun onLottieSelected(character: Character) {
        character.name?.let { Log.i("lottie", it) }


    }
}