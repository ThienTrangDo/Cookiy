package com.example.cookiy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat.getColor
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import coil.load
import com.example.cookiy.MainViewModel
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
   private var name = ""
   private lateinit var binding: FragmentDetailBinding
   private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = arguments?.getString("name")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            binding.backButton.findNavController().navigateUp()
        }

        val recipe = viewModel.recipes.value!!.find { it.name == name }

        binding.tvZutatenliste.text = recipe!!.ingredients

        val url = "https://public.syntax-institut.de/apps/batch6/Trang/images/" + recipe.image

        val imgUri = url.toUri().buildUpon().scheme("https").build()

        binding.ivRecipeImage.load(imgUri)
        binding.textViewRezeptname.text = recipe!!.name
        binding.tvInstructionsList.text = recipe!!.steps

        //todo Favoriten


    }

}