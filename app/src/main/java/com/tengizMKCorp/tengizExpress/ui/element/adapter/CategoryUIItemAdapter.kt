package com.tengizMKCorp.tengizExpress.ui.element.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tengizMKCorp.tengizExpress.databinding.CategoryItemBinding
import com.tengizMKCorp.tengizExpress.ui.element.model.CategoryUIItem


class CategoryUIItemAdapter : ListAdapter<CategoryUIItem, CategoryUIItemAdapter.CategoryViewHolder>(ItemDiffCallback()) {
    inner class CategoryViewHolder(private val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind() {

            val source = getItem(absoluteAdapterPosition)
            binding.apply {
                categoryName.text = source.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind()
    }
    private class ItemDiffCallback : DiffUtil.ItemCallback<CategoryUIItem>() {
        override fun areItemsTheSame(oldItem: CategoryUIItem, newItem: CategoryUIItem): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CategoryUIItem, newItem: CategoryUIItem): Boolean =
            oldItem == newItem

    }
}
