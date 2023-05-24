package com.example.cookiy.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay

const val TAG = "AppRepository"

class AppRepository (private val api: RecipeApi) {

    private val _imageList = MutableLiveData<List<String>>()
    val imageList: LiveData<List<String>>
        get() = _imageList

    suspend fun getImages() {
        delay(2000)
        _imageList.value = api.retrofitService.getImages().message
    }
}