package com.example.cookiy.adapter

import android.content.ClipData.Item
import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.cookiy.R

class ItemAdapter (
    private val context: Context,
    private val dataset: List<String>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.findViewById<ImageView>(R.id.recipe_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recipe, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        val imgUri = item.toUri().buildUpon().scheme("https").build()

        holder.imageView.load(imgUri) {
            error(R.drawable.baseline_arrow_back_24)
            transformations(RoundedCornersTransformation(10f))
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
    }