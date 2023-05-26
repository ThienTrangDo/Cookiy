package com.example.cookiy.data.remote

import com.example.cookiy.data.datamodels.RecipeImages
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://public.syntax-institut.de/apps/batch6/Trang/data.json"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//
interface ApiService {
    @GET("images")
    suspend fun getImages(): RecipeImages
}

object RecipeApi {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}