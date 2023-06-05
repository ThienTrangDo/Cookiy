package com.example.cookiy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cookiy.data.datamodels.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class RecipeDatabase: RoomDatabase() {

    abstract val recipeDatabaseDao : RecipeDatabaseDao

    companion object {
        private lateinit var dbInstance: RecipeDatabase

        fun getDatabase(context: Context): RecipeDatabase {
            synchronized(Recipe::class.java) {
                if (!::dbInstance.isInitialized) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_table"
                    ).build()
                }
            }
            return dbInstance
        }
    }
}