package com.example.cookiy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cookiy.data.datamodels.Recipe

@Database(entities = [Recipe::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun DatabaseDao
}

