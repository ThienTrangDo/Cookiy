package com.example.cookiy.data.remote

import com.example.cookiy.data.datamodels.Recipe
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
//Hier werden Daten aus der Api abgerufen

//Basis Url der Api, von der die Daten abgerufen werden
const val BASE_URL = "https://public.syntax-institut.de/apps/batch6/"

//um Antworten direkt zu übersetzen
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//empfangen daten aus dem Internet, und wird mit retrofit übersetzt
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))   //moshi als JSON Konverter
    .baseUrl(BASE_URL)
    .build()

//bestimmt wie mit dem Server kommuniziert wird
interface RecipeApiService {

    //gibt eine Liste von Recipe Objekten zurück
    //@Get definiert den Endpunkt der Api und den Weg zur Json
    @GET("Trang/data.json")
    suspend fun getRecipes(): List<Recipe>

}

//dient als Zugangspunkt für den Rest der App und stellt das Interface als retrofitservice zur Verfügung
object RecipeApi {
    val retrofitService: RecipeApiService by lazy { retrofit.create(RecipeApiService::class.java) }
}

//Nun kann man RecipeApi.retrofitService instamz verwenden um die gewünschten Daten von der Api abzurufen
//Bsp RecipeApi.retrofitService.getRecipes() um Liste der Rezepte von der API abzurufen