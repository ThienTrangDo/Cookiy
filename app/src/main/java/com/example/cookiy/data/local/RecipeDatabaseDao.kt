package com.example.cookiy.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookiy.data.datamodels.Recipe

@Dao
interface RecipeDatabaseDao {

    //Ein selbes Rezept wird nicht abgespeichert, es wird ignoriert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipes(recipeList: List<Recipe>)

    @Query("Select * From recipe_table")
    fun getAllRecipes(): LiveData<List<Recipe>>


}