package com.example.cookiy.data.remote

import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.data.datamodels.RecipeImages
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//das ist die adresse von der API
//todo prüfen
const val BASE_URL = "https://public.syntax-institut.de/apps/batch6/Trang/data.json"

//empfangen daten aus dem Internet, und wird mit retrofit übersetzt

//um Antworten direkt zu übersetzen
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit wird gebaut mit moshi und baseurl
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//bestimmt wie mit dem Server kommuniziert wird
interface RecipeApiService {

    //todo prüfen
    //Get Request am Endpunkt recipe welcher eine Rezepteliste zurückliefert
    @GET("Rezeptname")
    suspend fun getRecipes(): List<Recipe>

    //mit get laden wir Bilder von der API
    @GET("Bild")
    suspend fun getImages(): RecipeImages
}

//dient als Zugangspunkt für den Rest der App und stellt das Interface als retrofitservice zur Verfügung
object RecipeApi {
    val retrofitService: RecipeApiService by lazy { retrofit.create(RecipeApiService::class.java) }
}