package com.example.cookiy.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.data.remote.RecipeApi
import kotlinx.coroutines.delay

class AppRepository (private val api: RecipeApi) {

    private val _recipe = MutableLiveData<List<Recipe>>()
    val recipe: LiveData<List<Recipe>>
        get() = _recipe
    //es wird versucht eine Liste an Rezepten über retrofit zu laden und als LiveData bereitzustellen
    suspend fun getRecipe() {
        try {
            _recipe.value = api.retrofitService.getRecipes()
        } catch (e: Exception) {
            Log.d("Repository", "API Call failed: $e")
        }
    }
}