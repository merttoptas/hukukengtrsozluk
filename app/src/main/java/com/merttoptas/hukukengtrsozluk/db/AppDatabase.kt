package com.merttoptas.hukukengtrsozluk.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 2)
abstract class AppDatabase :RoomDatabase(){
    abstract fun favdao():FavDao
}