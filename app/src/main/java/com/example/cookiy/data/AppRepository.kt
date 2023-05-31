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




    //Livedata für die Liste an Bilder, falls sich was im Server ändert, passt es sich direkt an
    //String?? todo prüfen
    private val _imageList = MutableLiveData<List<String>>()
    val imageList: LiveData<List<String>>
        get() = _imageList

    //pausierbare funktion, wert wird im main thread gesetzt
    //delay um zu zeigen dass susfun funktioniert/pausiert werden kann
    suspend fun getImages() {
        delay(2000)
        _imageList.value = api.retrofitService.getImages().message
    }
}