package com.example.cookiy.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Category
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.data.local.RecipeDatabase.Companion.getDatabase
import com.example.cookiy.data.remote.RecipeApi

class AppRepository (private val api: RecipeApi, private val application: Application) {


    var database = getDatabase(application)

    val recipes = database.recipeDatabaseDao.getAllRecipes()


    //es wird versucht eine Liste an Rezepten über retrofit zu laden und als LiveData bereitzustellen
    suspend fun getRecipe() {
        try {
            database.recipeDatabaseDao.insertRecipes(api.retrofitService.getRecipes())
        } catch (e: Exception) {
            Log.d("Repository", "API Call failed: $e")
        }
    }



    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>>
        get() = _categoryList

    init {
        loadCategory()
    }

    fun loadCategory(){
        val categoryList = listOf<Category>(
            Category(
                1,
                R.string.burger,
                R.drawable.burgerbg
            ),
            Category(
                2,
                R.string.pasta,
                R.drawable.pastabg
            ),
            Category(
                3,
                R.string.dessert,
                R.drawable.dessertbg
            ),
            Category(
                4,
                R.string.drinks,
                R.drawable.drinksbg
            ),
            Category(
                5,
                R.string.vegetarisch,
                R.drawable.vegetarischbg
            ),
            Category(
                6,
                R.string.kuchen,
                R.drawable.kuchenbg
            )
        )
        _categoryList.value = categoryList
    }


}