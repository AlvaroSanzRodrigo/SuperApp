package com.sanzsoftware.superapp.models

import androidx.room.PrimaryKey

data class Character(
    var name: String,
    var description: String,
    var thumbnail: String
){
    @PrimaryKey(autoGenerate = true)
    var glassId: Int? = null
}