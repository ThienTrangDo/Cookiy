package com.example.cookiy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.PrimaryKey
import coil.load
import com.example.cookiy.MainViewModel
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Recipe
import com.example.cookiy.ui.FavoriteFragmentDirections
import com.example.cookiy.ui.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView

class FavoriteAdapter(
    private val dataset: MutableList<Recipe>,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<FavoriteAdapter.ItemViewHolder>(){

    //viewholder weiß welche Teile des layouts beim recycling angepasst werden
    //verschiedene Views werden generiert
    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTV = view.findViewById<TextView>(R.id.tv_favoriteName)
        val recipeCardview: MaterialCardView = view.findViewById(R.id.favorite_card)
        val imageView = view.findViewById<ImageView>(R.id.iv_favorite)
        val favoriteButton = view.findViewById<ImageButton>(R.id.favortieBtn)
    }

    //hier werden neue Viewholder erstellt
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //das itemlayout wird gebaut
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_favorite, parent, false)

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

        //hier werden Daten des entsprechenden Rezepts aus der dataset abgerufen
        val item = dataset[position]

        val url = "https://public.syntax-institut.de/apps/batch6/Trang/images/" + item.image

        val imgUri = url.toUri().buildUpon().scheme("https").build()

        holder.nameTV.text = item.name

        holder.imageView.load(imgUri)


        //todo ändern
        holder.recipeCardview.setOnClickListener {
            holder.itemView.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.name))
        }

        //Favoriten Button um Rezepte aus den Favoriten zu löschen
        holder.favoriteButton.setOnClickListener {
            viewModel.deleteFavorite(item.name)
            dataset.removeAt(position)
            notifyItemChanged(position)
        }
    }
}