package com.example.cookiy

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookiy.data.AppRepository
import com.example.cookiy.data.remote.RecipeApi
import kotlinx.coroutines.launch

const val TAG = "MainViewModel"

enum class ApiStatus{ LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private val repository = AppRepository(RecipeApi)
    val recipe = repository.imageList

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus>
        get() = _loading

    val images = repository.imageList

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADING
            try {
                repository.getImages()
                _loading.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.e(TAG, "Error loading data from api: $e")
                _loading.value = ApiStatus.ERROR
            }
        }
    }
}