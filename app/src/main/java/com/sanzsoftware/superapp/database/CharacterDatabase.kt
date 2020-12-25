package com.sanzsoftware.superapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sanzsoftware.superapp.MainActivity
import com.sanzsoftware.superapp.models.Character
import org.jetbrains.anko.AnkoAsyncContext


@Database(entities = [Character::class], version = 1)
@TypeConverters(Converters::class)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object{

        @Volatile private var instance: CharacterDatabase? = null

        fun getInstance(context: Context): CharacterDatabase=
                instance ?: synchronized(this) {
                    instance ?: Room.databaseBuilder(context.applicationContext!!, CharacterDatabase::class.java, "super_database").build().apply {
                        instance = this
                    }
                }
    }
}