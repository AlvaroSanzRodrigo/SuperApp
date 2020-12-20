package com.sanzsoftware.superapp.database

import androidx.room.TypeConverter
import com.sanzsoftware.superapp.models.Thumbnail
import java.util.*

class Converters {
    @TypeConverter
    fun fromString(value: String): Thumbnail {
        return Thumbnail(value.split(' ')[0], value.split(' ')[1]);
    }

    @TypeConverter
    fun thumbnailToString(thumbnail: Thumbnail): String{
        return thumbnail.path + " " + thumbnail.extension;
    }
}