package com.example.cookiy.adapter

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.databinding.ListRecipeBinding

class ItemAdapter (
    private val context: Context,
    private val dataset: List<Recipe>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(val binding: ListRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recipe, parent, false)

        return ItemViewHolder()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        }
    }
