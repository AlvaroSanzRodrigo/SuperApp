package com.sanzsoftware.superapp.database

import androidx.room.*
import com.sanzsoftware.superapp.models.Character
@Dao
interface CharacterDao {
        @Query("SELECT * FROM Character")
        fun getAll(): List<Character>

        @Query("SELECT * FROM Character WHERE id IN (:ids)")
        fun loadAllByIds(ids: IntArray): List<Character>

        @Query("SELECT * FROM Character WHERE id IN (:id)")
        fun isFavorite(id: Int): Character

        @Insert
        fun insert(vararg character: Character)

        @Update
        fun update(character: Character)

        @Delete
        fun delete(character: Character)
}
