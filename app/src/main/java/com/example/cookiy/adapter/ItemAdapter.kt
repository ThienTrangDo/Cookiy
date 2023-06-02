package com.example.cookiy.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.ui.HomeFragment
import com.example.cookiy.ui.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView

class ItemAdapter(
    private val dataset: List<Recipe>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    //viewholder weiß welche Teile des layouts beim recycling angepasst werden
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTV = view.findViewById<TextView>(R.id.recipe_text)
        val recipeCardview: ConstraintLayout = view.findViewById(R.id.item_layout)
        val detailNameTV = view.findViewById<TextView>(R.id.textViewRezeptname)
        val ingredientsTV = view.findViewById<TextView>(R.id.tvZutaten)
        val stepsTV = view.findViewById<TextView>(R.id.tvInstructionsList)
        val imageView = view.findViewById<ImageView>(R.id.recipe_image)
        val ivFavorite = view.findViewById<ImageView>(R.id.iv_favorite)
    }

    //hier werden neue Viewholder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //das itemlayout wird gebaut
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recipe, parent, false)

        //und in einem viewholder zurückgegeben
        return ItemViewHolder(adapterLayout)
    }

    //damit layoutmanager weiß wie lang die liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }

    //hier findet der recyclingsprozess statt
    //die vom Viewholder bereitgestellten Parameter werden verändert
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val item = dataset[position]

        holder.nameTV.text = item.name

        val url = "https://public.syntax-institut.de/apps/batch6/Trang/images/" + item.image

        val imgUri = url.toUri().buildUpon().scheme("https").build()

        holder.imageView.load(imgUri)

        holder.recipeCardview.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.name))
        }

       //todo Kategorien
    }
}

