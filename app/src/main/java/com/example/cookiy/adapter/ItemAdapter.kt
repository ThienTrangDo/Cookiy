package com.example.cookiy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Recipe

//todo prüfen list recipe/String
class ItemAdapter(val dataset: List<Recipe>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){

    //class ItemViewHolder(val binding: ListRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    //viewholder weiß welche Teile des layouts beim recycling angepasst werden
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val nameTV = view.findViewById<TextView>(R.id.recipe_text)
        val detailNameTV = view.findViewById<TextView>(R.id.textViewRezeptname)
        val ingredientsTV = view.findViewById<TextView>(R.id.tvZutaten)
        val stepsTV = view.findViewById<TextView>(R.id.tvInstructionsList)
        val imageView = view.findViewById<ImageView>(R.id.recipe_image)
    }

    //hier werden neue Viewholder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        //das itemlayout wird gebaut
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_recipe, parent, false)

        //und in einem viewholder zurückgegeben
        return ItemViewHolder(adapterLayout)
    }

    //hier findet der recyclingsprozess statt
    //die vom Viewholder bereitgestellten Parameter werden verändert
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val recipe = dataset[position]

        val imgUri = recipe.toUri().buildUpon().scheme("https").build()

        holder.imageView.load(imgUri)

        holder.nameTV.text = recipe.name
        holder.detailNameTV.text = recipe.name
        holder.ingredientsTV.text = recipe.ingredients
        holder.stepsTV.text = recipe.steps
        }

    override fun getItemCount(): Int {
        return dataset.size
    }
    }
