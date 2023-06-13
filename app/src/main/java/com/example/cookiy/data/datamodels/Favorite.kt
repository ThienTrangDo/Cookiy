package com.example.cookiy.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

//wird verwendet um ein favoriten in der datenbank zu repräsentieren
//um Favoriten zu speichern und abzurufen
@Entity (tableName = "favorite_table")
data class Favorite (
    @PrimaryKey
    var recipeName : String
)