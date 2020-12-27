package com.sanzsoftware.superapp.fragments

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sanzsoftware.superapp.R
import com.sanzsoftware.superapp.database.CharacterDatabase
import com.sanzsoftware.superapp.models.Character
import com.sanzsoftware.superapp.transforms.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_dialog.view.*
import kotlinx.android.synthetic.main.character_dialog.view.imageViewDialog
import kotlinx.android.synthetic.main.character_dialog.view.likeLottieAnimation
import kotlinx.android.synthetic.main.character_dialog.view.textView_SuperHero_name
import kotlinx.android.synthetic.main.item_superhero.view.*
import org.jetbrains.anko.doAsync

class CharacterDialog(private val character: Character,val mCallback: OnDismissDialog) : DialogFragment() {

    interface OnDismissDialog {
        fun onDismiss(character: Character)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.character_dialog ,container, false).apply {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));

            if (character.isFavorite!!){
                likeLottieAnimation.playAnimation()
            }

            textView_SuperHero_name.text = character.name
            textView_SuperHero_description.apply {
                text = character.description
                movementMethod = ScrollingMovementMethod()
            }
            Picasso.get()
                .load(character.thumbnail?.path + "."  + character.thumbnail?.extension)
                .resize(100, 100)
                .transform(CircleTransform())
                .centerCrop()
                .into(imageViewDialog)

            likeLottieAnimation.setOnClickListener {
                if (character.isFavorite!!) {
                    it.likeLottieAnimation.apply {
                        speed = -1.8f
                        playAnimation()
                    }
                    doAsync { CharacterDatabase.getInstance(context).characterDao().delete(character) }
                }
                else {
                    it.likeLottieAnimation.apply {
                        speed = 1.8f
                        playAnimation()
                    }
                    doAsync { CharacterDatabase.getInstance(context).characterDao().insert(character) }
                }
                character.isFavorite = !character.isFavorite!!
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mCallback.onDismiss(character)
    }

    override fun dismiss() {
        mCallback.onDismiss(character)
    }

}