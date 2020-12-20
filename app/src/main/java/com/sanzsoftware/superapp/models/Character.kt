package com.sanzsoftware.superapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Character(
    var name: String,
    var description: String,
    var thumbnail: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}