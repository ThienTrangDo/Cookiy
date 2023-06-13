package com.example.cookiy.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Category
import com.example.cookiy.data.datamodels.Favorite
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.data.local.RecipeDatabase.Companion.getDatabase
import com.example.cookiy.data.remote.RecipeApi
//Verwaltung der Daten, Abrufs von Rezepten über die Api,
//Speicherung der Rezepte in der lokalen Datenbank und Bereitstellung von Kategorien für die Benutzeroberfläche

//Vermittler zwischen Datenquelle(Api und lokale Datenbank) und der Benutzeroberfläche

class AppRepository (private val api: RecipeApi, private val application: Application) {

    //Datenbankobjekt, um auf die Dao Methoden zuzugreifen und Daten aus der lokalen Datenbank abzurufen
    var database = getDatabase(application)

    //LiveDataObjekt das die Liste der Rezepten aus der Datenbank darstellt
    //getAllRecipes um die Rezepte abzurufen
    val recipes = database.recipeDatabaseDao.getAllRecipes()

    fun getAllFavorites() : List<Favorite> {
        return database.recipeDatabaseDao.getAllFavorites()
    }

    fun checkFavorite(favName: String) : Int {
        return database.recipeDatabaseDao.checkFavorite(favName)
    }

    //es wird versucht eine Liste an Rezepten über api zu laden und in die Datenbank einzufügen
    suspend fun getRecipe() {
        try {
            database.recipeDatabaseDao.insertRecipes(api.retrofitService.getRecipes())
        } catch (e: Exception) {
            Log.d("Repository", "API Call failed: $e")      //Falls Fehler auftritt, wird Fehlermeldung in den Logcat geschrieben
        }
    }

    //Funktionen für Favoriten

    //Funktion um in die Datenbank Favoriten einzufügen, mit Parameter favoriteName
    //Objekt mit dem übergebenen Namen wird erstellt & mit insert methode des dao in die datenbank rein
    suspend fun insertFavorite(favoriteName: String){
        try {
            var favorite = Favorite(favoriteName)
            database.recipeDatabaseDao.insertFavorite(favorite)
        } catch (e: Exception) {
            Log.d("Repository","Fehler vom Erstellen von Favorite /n  ${e.message}")
        }
    }


    //ebenfalls mit gleichen Parameter, wird die delete methode im dao aufgerufen um favoriten
    //aus der datenbank zu löschen
    suspend fun deleteFavorite(favoriteName: String){
        try {
            database.recipeDatabaseDao.deleteFavorite(favoriteName)
        } catch (e: Exception) {
            Log.d("Repository","Fehler beim Löschen von Favorite")
        }
    }



    //MutableLiveData speichert Kategorie Liste ab
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