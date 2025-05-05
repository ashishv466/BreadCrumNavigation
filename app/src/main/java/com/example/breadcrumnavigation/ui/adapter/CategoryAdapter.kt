package com.example.breadcrumnavigation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.breadcrumnavigation.data.model.Category
import com.example.breadcrumnavigation.data.model.Item
import com.example.breadcrumnavigation.data.model.SubCategory
import com.example.breadcrumnavigation.R


class CategoryAdapter(
    private val onItemClick: (Any) -> Unit
) : ListAdapter<Any, CategoryAdapter.CategoryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Any) {
            val name = when (data) {
                is Category -> data.name
                is SubCategory -> data.name
                is Item -> data.name
                else -> ""
            }

            itemView.findViewById<TextView>(R.id.itemName).text = name
            itemView.setOnClickListener { onItemClick(data) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any) = oldItem == newItem
        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Any, newItem: Any) = oldItem == newItem
    }
}