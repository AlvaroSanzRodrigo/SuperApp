package com.sanzsoftware.superapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Character")
data class Character(
    @ColumnInfo( name = "name") var name: String? = "",
    @ColumnInfo( name = "description") var description: String? = "",
    @ColumnInfo( name = "thumbnail") var thumbnail: Thumbnail? = null,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)