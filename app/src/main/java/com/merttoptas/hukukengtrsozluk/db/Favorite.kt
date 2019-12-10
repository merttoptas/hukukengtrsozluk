package com.merttoptas.hukukengtrsozluk.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorite(
    @ColumnInfo(name = "favWordsEng")
    var favWordsEng:String,

    @ColumnInfo(name ="favWordsTr")
    var favWordsTr:String)
{
    @PrimaryKey(autoGenerate = true)
    var favId :Int =0
}