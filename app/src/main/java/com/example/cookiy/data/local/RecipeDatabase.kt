package com.example.cookiy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cookiy.data.datamodels.Favorite
import com.example.cookiy.data.datamodels.Recipe

//Datanbankklasse, die von Room verwendet wird um den Datenbankzugriff zu verwalten
//dient als Schnittstelle für den Zugriff auf die Datenbank

@Database(entities = [Recipe::class, Favorite::class], version = 1, exportSchema = false)
//erbt von Klasse Roomdatabase
abstract class RecipeDatabase: RoomDatabase() {

    //gibt ein Objekt zurück, welches den Zugriff auf die Dao Methoden ermöglicht
    abstract val recipeDatabaseDao : RecipeDatabaseDao

    //enthält Methode 'getdatabase' um eine Instanz Recipedatabase zu erhalten,
    //und erstellt die Datenbank
    companion object {
        private lateinit var dbInstance: RecipeDatabase

        fun getDatabase(context: Context): RecipeDatabase {     //context um Anwendungskontext zu erhalten
            synchronized(Recipe::class.java) {                  //prüft ob Initialisierung der Datenbankinstanz unteilbar erfolgt
                if (!::dbInstance.isInitialized) {
                    dbInstance = Room.databaseBuilder(          //Parameter sind context, klasse der datenbank und datenbanktabellenname
                        context.applicationContext,
                        RecipeDatabase::class.java,
                        "recipe_table"
                    ).allowMainThreadQueries()      //todo neu
                        .build()
                }
            }
            return dbInstance                                   //Datenbankinstanz wird an die Klasse zurückgegeben
        }
    }
}