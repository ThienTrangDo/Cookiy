package com.example.cookiy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiy.adapter.ItemAdapter
import com.example.cookiy.databinding.ActivityMainBinding

//'Host' für die ganzen Fragmente und verwaltet die Navigation zw den Fragmenten
class MainActivity : AppCompatActivity() {

    //stellt den viewmodel zur verfügung
    private val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setzen das layout mit hilfe von binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //ermöglicht die navigation
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController

        //bottomNavbar
        val bottomNavigationView = binding.bottomNavBar

        setupWithNavController(bottomNavigationView, navController)

        //API Rezepte
        viewModel.recipes.observe(this) {
            findViewById<RecyclerView>(R.id.rvRezepte).adapter = ItemAdapter(it)
            Log.d("Error", it.toString())
        }
    }
}