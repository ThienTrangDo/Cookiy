package com.example.cookiy.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Kategorien
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.data.remote.RecipeApi

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


    private val _kategorienList = MutableLiveData<List<Kategorien>>()
    val kategorienList: LiveData<List<Kategorien>>
        get() = _kategorienList
    fun loadFavorite(){
        val kategorienLists = listOf<Kategorien>(
            Kategorien(1, R.drawable.burgerbg),
            Kategorien(2, R.drawable.dessertbg),
            Kategorien(3, R.drawable.drinksbg),
            Kategorien(4, R.drawable.kuchenbg),
            Kategorien(5, R.drawable.pastabg),
            Kategorien(6, R.drawable.vegetarischbg)
        )
        _kategorienList.value = kategorienLists

    }

}