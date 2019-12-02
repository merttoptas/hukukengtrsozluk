package com.merttoptas.hukukengtrsozluk.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavDao {
    @Query("Select * From favorite_table")
    fun getWordsFavorites():List<Favorite>

    @Insert
    fun instertWord(vararg favorite:Favorite)

    @Query("Select favId from FAVORITE_TABLE where favWords = favWords")
    fun getId(vararg favWors :String) : Int

    @Query("DELETE from FAVORITE_TABLE where favId = :favId")
    fun deleteById(vararg favId :Int) :Int
}