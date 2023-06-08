package com.example.cookiy.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "favorite_table")
data class Favorite (
    @PrimaryKey
    var recipeName : String
)