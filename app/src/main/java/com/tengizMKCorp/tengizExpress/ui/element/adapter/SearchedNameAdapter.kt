package com.tengizMKCorp.tengizExpress.ui.element.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tengizMKCorp.tengizExpress.data.local.source.product.NameModel
import com.tengizMKCorp.tengizExpress.databinding.LastSearchNameBinding

class SearchedNameAdapter(val onDelete: (x: NameModel )->Unit, val onClick: (x: NameModel)->Unit) : ListAdapter<NameModel, SearchedNameAdapter.SearchedNameHolder>(ItemDiffCallback()) {
    inner class SearchedNameHolder(private val binding: LastSearchNameBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind() {

            val source = getItem(absoluteAdapterPosition)
            binding.apply {
                itemNameTV.text = source.name
                xButtonIV.setOnClickListener {
                    onDelete(source)
                }
                itemNameTV.setOnClickListener {
                    onClick(source)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedNameHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LastSearchNameBinding.inflate(layoutInflater, parent, false)
        return SearchedNameHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchedNameHolder, position: Int) {
        holder.bind()
    }
    private class ItemDiffCallback : DiffUtil.ItemCallback<NameModel>() {
        override fun areItemsTheSame(oldItem: NameModel, newItem: NameModel): Boolean =
            oldItem.name == newItem.name

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NameModel, newItem: NameModel): Boolean =
            oldItem == newItem

    }
}
