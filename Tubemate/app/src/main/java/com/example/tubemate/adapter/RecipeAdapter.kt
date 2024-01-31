package com.example.tubemate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tubemate.entity.RecipeItem
import com.example.tubemate.databinding.RecipeShortItemRowBinding
import com.example.tubemate.utils.setGlide

class RecipeAdapter :ListAdapter<RecipeItem, RecipeAdapter.ViewHolder>(DiffCallBack()) {

    inner class ViewHolder(private val binding: RecipeShortItemRowBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(recipeItem: RecipeItem){
            binding.apply {
              tvShortTitle.text =  recipeItem.title
              ivShortThumbnail.setGlide(itemView.context,recipeItem.thumbnailUrl)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecipeShortItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallBack: DiffUtil.ItemCallback<RecipeItem>(){
        override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}