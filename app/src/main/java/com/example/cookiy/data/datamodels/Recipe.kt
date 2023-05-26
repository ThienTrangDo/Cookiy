package com.example.cookiy.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


data class Recipe (
   val id: Int,
   val name: String,
   val image: String,
   val ingredients: List<String>,
   val steps: List<String>
    )

