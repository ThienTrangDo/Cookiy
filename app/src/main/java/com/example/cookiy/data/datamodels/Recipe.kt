package com.example.cookiy.data.datamodels

import com.squareup.moshi.Json

data class Recipe (
   val id: Int = 0,


   @Json(name = "Rezeptname")
   val name: String,


   @Json(name = "Bild")
   val image: String,


   @Json(name = "Zutaten")
   val ingredients: String,


   @Json(name= "Zubereitung ")
   val steps: String

    )

