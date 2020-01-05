package com.merttoptas.hukukengtrsozluk.db.model

data class Words(
    val turkish : String = "",
    val english: String = "",
    var id : Int = 0,
    var fav : Boolean = false
)