package com.sanzsoftware.superapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanzsoftware.superapp.R
import com.sanzsoftware.superapp.models.Character
import kotlinx.android.synthetic.main.item_superhero.view.*

// Adaptador standar
class CharacterAdapter(var items: ArrayList<Character>) : RecyclerView.Adapter<CharacterAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    var mCallBack: OnClickedItemListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_superhero, parent, false).apply {
            mCallBack = parent.context as OnClickedItemListener
        } as View

        return MyViewHolder(view)
    }

    interface OnClickedItemListener {
        fun onItemSelected(character: Character)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            mCallBack?.onItemSelected(items[position])
        }
        holder.view.textView_character_name.text = items[position].name
    }

    override fun getItemCount() = items.size
}