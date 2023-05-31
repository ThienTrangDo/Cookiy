package com.example.cookiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiy.adapter.ItemAdapter
import com.example.cookiy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //stellt den viewmodel zur verfügung
    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    //binding wird benutzt
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setzen das layout mit hilfe von binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        //bottomNavbar
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = binding.bottomNavBar

        setupWithNavController(bottomNavigationView, navController)


        //API Rezepte
        viewModel.recipe.observe(this, {
            findViewById<RecyclerView>(R.id.rvRezepte).adapter = ItemAdapter(it)
            Log.d("Error", it.toString())
        })
    }
}