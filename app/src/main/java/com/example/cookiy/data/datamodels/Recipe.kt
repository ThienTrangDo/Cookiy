package com.example.cookiy.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

//definiert ein Rezeptobjekt, welches in einer datenbanktabelle gespeichert werden kann
@Entity (tableName = "recipe_table")
data class Recipe (

   @PrimaryKey
   @Json(name = "Rezeptname")
   val name: String,


   @Json(name = "Bild")
   val image: String,


   @Json(name = "Zutaten")
   val ingredients: String,


   @Json(name= "Zubereitung ")
   val steps: String

   //json name = um json schlüsselnamen anzugeben mit denen die entsprechenden daten verknüpft sind

    )

