package com.example.cookiy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.data.remote.RecipeApi
import kotlinx.coroutines.delay

class AppRepository (private val api: RecipeApi) {

    private val _imageList = MutableLiveData<List<Recipe>>()
    val imageList: LiveData<List<Recipe>>
        get() = _imageList
}