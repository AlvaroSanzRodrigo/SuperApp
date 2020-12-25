package com.sanzsoftware.superapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanzsoftware.superapp.R
import com.sanzsoftware.superapp.database.CharacterDatabase
import com.sanzsoftware.superapp.models.Character
import com.sanzsoftware.superapp.transforms.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_superhero.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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
        var context: Context
        fun onItemSelected(character: Character)
        fun onLottieSelected(character: Character)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            mCallBack?.onItemSelected(items[position])
        }
        doAsync {
            val favCharacter = mCallBack?.context?.let { items[position].id?.let { it1 -> CharacterDatabase.getInstance(it).characterDao().isFavorite(it1) } }
            if (favCharacter != null)
                uiThread {
                    holder.view.likeLottieAnimation.playAnimation()
                    // items[position].isFavorite = !items[position].isFavorite!!
                }
        }
        if (items[position].isFavorite!!){
            holder.view.likeLottieAnimation.playAnimation()
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

            mCallBack?.onLottieSelected(items[position])

            items[position].isFavorite = !items[position].isFavorite!!
        }
        Picasso.get()
            .load(items[position].thumbnail?.path + "."  + items[position].thumbnail?.extension)
            .resize(100, 100)
            .transform(CircleTransform())
            .centerCrop()
            .into(holder.view.imageViewDialog)
        holder.view.textView_SuperHero_name.text = items[position].name
    }

    override fun getItemCount() = items.size
}