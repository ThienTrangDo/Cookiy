package com.example.cookiy.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class Recipe (
   val id: Long,
   val name: String,
   val image: String
    )

