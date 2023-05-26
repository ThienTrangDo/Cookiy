package com.example.cookiy.data.remote

import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.data.datamodels.RecipeResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://public.syntax-institut.de/apps/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//
interface ApiService {
    @GET("batch6/Trang/data.json")
    suspend fun getRecipes(): RecipeResponse
}

object RecipeApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}