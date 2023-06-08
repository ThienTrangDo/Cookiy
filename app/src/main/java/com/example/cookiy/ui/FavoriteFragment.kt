package com.example.cookiy.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.cookiy.MainViewModel
import com.example.cookiy.R
import com.example.cookiy.TAG
import com.example.cookiy.adapter.FavoriteAdapter
import com.example.cookiy.data.datamodels.Favorite
import com.example.cookiy.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var favorites = viewModel.getAllFavorites()
        Log.e(TAG, "ALl Favorites are get from database!")
        var favoriteNames = mutableListOf<String>()

        for(fav in favorites) {
            favoriteNames.add(fav.recipeName)
        }

        var favoritesAsFavorite = viewModel.recipes.value!!.filter { it.name in favoriteNames  }.toMutableList()

        binding.rvFavorite.adapter = FavoriteAdapter(favoritesAsFavorite, viewModel)

    }


}