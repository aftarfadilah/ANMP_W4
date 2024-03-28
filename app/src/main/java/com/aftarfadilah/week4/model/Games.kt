package com.aftarfadilah.week4.model

import com.google.gson.annotations.SerializedName

data class Games(
    val name:String?,
    val genre:String?,
    val platforms:List<String>?,
    val characters:List<String>?,
    val settings: GameSettings?
    )

data class GameSettings(
    val difficulty: String?,
    val playtime: String?,
    val gameSize: String?,
)