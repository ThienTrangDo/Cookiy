package com.example.cookiy.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cookiy.data.datamodels.Recipe

@Dao
interface DatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    fun getAll(): LiveData<List<Recipe>>

    @Query("DELETE FROM Recipe")
    fun deleteAll()
}