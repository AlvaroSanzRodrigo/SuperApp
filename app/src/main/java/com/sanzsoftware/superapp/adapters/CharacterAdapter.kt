package com.sanzsoftware.superapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanzsoftware.superapp.R
import com.sanzsoftware.superapp.models.Character
import com.sanzsoftware.superapp.transforms.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_superhero.view.*
import kotlin.math.log

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
        fun onLottieSelected(character: Character)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mCallBack?.onItemSelected(items[position])
        }

        holder.view.likeLottieAnimation.setOnClickListener {
            if (items[position].isFavorite!!)
                it.likeLottieAnimation.apply {
                    speed = -1.8f
                    playAnimation()
                }
            else
                it.likeLottieAnimation.apply {
                    speed = 1.8f
                    playAnimation()
                }
            items[position].isFavorite = !items[position].isFavorite!!
            mCallBack?.onLottieSelected(items[position])
        }
        var url = items[position].thumbnail?.path + items[position].thumbnail?.extension
        Picasso.get()
            .load(items[position].thumbnail?.path + "."  + items[position].thumbnail?.extension)
            .resize(100, 100)
            .transform(CircleTransform())
            .centerCrop()
            .into(holder.view.imageView)
        holder.view.textView_SuperHero_name.text = items[position].name
    }

    override fun getItemCount() = items.size
}