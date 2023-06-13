package com.example.cookiy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cookiy.R
import com.example.cookiy.data.datamodels.Category
import com.example.cookiy.databinding.ListCategoryBinding

//Hier wird eine Liste von Kategorien in einem Recyclerview angezeigt

class CategoryAdapter (
    private val context: Context,
    private val categoryList: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>(){

    class ItemViewHolder(val binding: ListCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val catgory = categoryList[position]

        holder.binding.categoryText.text = context.resources.getText(catgory.nameCategory)
        holder.binding.categoryImage.setImageResource(catgory.imageCategory)
    }

}