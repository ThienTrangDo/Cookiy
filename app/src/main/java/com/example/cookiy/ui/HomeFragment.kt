package com.example.cookiy.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.cookiy.ApiStatus
import com.example.cookiy.MainViewModel
import com.example.cookiy.R
import com.example.cookiy.adapter.CategoryAdapter
import com.example.cookiy.adapter.ItemAdapter
import com.example.cookiy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.category.observe(viewLifecycleOwner){ category ->
            binding.rvKategorie.adapter = CategoryAdapter(requireContext(),category)
        }

        viewModel.recipes.observe(
            viewLifecycleOwner,
            Observer {
                binding.rvRezepte.adapter = ItemAdapter(it)
            }
        )

        viewModel.loading.observe(
            viewLifecycleOwner,
            Observer {
                when (it) {
                    ApiStatus.LOADING -> binding.progressBar.visibility = View.VISIBLE
                    ApiStatus.DONE -> binding.progressBar.visibility = View.GONE
                    ApiStatus.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        )

    }
}