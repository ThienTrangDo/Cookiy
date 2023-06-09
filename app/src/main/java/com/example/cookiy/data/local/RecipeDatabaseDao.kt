package com.example.cookiy.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cookiy.data.datamodels.Favorite
import com.example.cookiy.data.datamodels.Recipe

//Databasedao ermöglicht das einfügen von rezepten und abrufen aller rezepte
//ebenso abrufen, überprüfen, einfügen und löschen von Favoriten

//@Dao es handelt sich um ein Data Access Object
@Dao
interface RecipeDatabaseDao {

    //ermöglicht das einfügen einer liste von Rezepten in die Datenbank
    //ein bereits vorhandenes Rezept wird ignoriert und nicht erneut eingefügt
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipes(recipeList: List<Recipe>)

    //Ruft alle Rezepte aus der Datenbank auf, alle aus der Tabelle recipe_table
    //LiveData kann die Liste automatisch aktualisieren, wenn sich die Daten in der Datenbank ändern
    @Query("Select * From recipe_table")
    fun getAllRecipes(): LiveData<List<Recipe>>

    //Ruft alle Favoriten aus der Datenbank ab
    @Query("Select * From favorite_table")
    fun getAllFavorites(): List<Favorite>

    //überprüft ob ein Rezept bereits als Favorit markiert ist, schaut ob recipename mit favname übereinstimmt
    @Query("Select count(*) From favorite_table where recipeName = :favName")
    fun checkFavorite(favName: String): Int

    //einfügen eines Favoriten in die Datenbank, ignoriert bereits vorhandene Favoriten
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(favorite: Favorite)

    //löscht favrotien aus der Datenbank
    @Query("Delete From favorite_table Where recipeName = :favoriteName")
    suspend fun deleteFavorite(favoriteName: String)
}

//SQL ist eine Anweisung um Daten aus einer Datenbank abzurufen, zu aktualisieren, einzufügen oder zu löschen