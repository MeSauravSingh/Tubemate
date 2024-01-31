package com.example.tubemate.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tubemate.databinding.ProductItemRowBinding
import com.example.tubemate.entity.ProductItem
import com.example.tubemate.utils.setGlide

class ProductAdapter :ListAdapter<ProductItem, ProductAdapter.ViewHolder>(DiffCallBack()) {

    lateinit var onItemClick:() -> Unit
    inner class ViewHolder(private val binding: ProductItemRowBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(productItem: ProductItem){
            binding.apply {
                tvTitle.text = productItem.title
                tvDesc.text = productItem.description
                ivThumbnail.setGlide(itemView.context,productItem.thumbnailUrl)
                ivThumbnail.setOnClickListener {
                    onItemClick.invoke()
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class DiffCallBack: DiffUtil.ItemCallback<ProductItem>(){
        override fun areItemsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductItem, newItem: ProductItem): Boolean {
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