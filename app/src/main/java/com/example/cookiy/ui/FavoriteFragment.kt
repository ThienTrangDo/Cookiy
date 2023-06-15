package com.example.cookiy.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.cookiy.MainViewModel
import com.example.cookiy.R
import com.example.cookiy.TAG
import com.example.cookiy.adapter.FavoriteAdapter
import com.example.cookiy.data.datamodels.Favorite
import com.example.cookiy.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var navController: NavController  //todo neu

/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }*/

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

        navController = findNavController()

        //ruft alle Fav aus der Datenbank ab, gespeichert in einer Variable
        var favorites = viewModel.getAllFavorites()
        Log.e(TAG, "ALl Favorites are get from database!")
        var favoriteNames = mutableListOf<String>()

        for(fav in favorites) {
            favoriteNames.add(fav.recipeName)
        }

        //gibt die Liste der verfügbaren Rezepten zurück
        //filter, hier wird die liste der rezepte gefiltert, deren namen in der favoritenames liste enthalten sind
        var favoritesAsFavorite = viewModel.recipes.value!!.filter { it.name in favoriteNames  }.toMutableList()

        binding.rvFavorite.adapter = FavoriteAdapter(favoritesAsFavorite, viewModel, navController) //todo neu

    }
}