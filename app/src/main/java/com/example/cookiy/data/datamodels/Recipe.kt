package com.example.cookiy.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class Recipe (

    @PrimaryKey
    @Json(name = "idRezept")
    val id: Long,

    )

