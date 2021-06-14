package com.example.foodapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.core.R
import com.example.foodapp.core.databinding.ItemRowBinding
import com.example.foodapp.core.domain.model.Recipe

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.ListViewHolder>() {
    private var listData = ArrayList<Recipe>()
    var onItemClick:((Recipe) -> Unit)? = null

    fun setData(newListData: List<Recipe>?){
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false))


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = ItemRowBinding.bind(itemView)
        fun bind(data: Recipe) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivImage)
                tvName.text = data.meal
                tvCategory.text = data.category
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}