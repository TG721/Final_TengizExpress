package com.tengizMKCorp.tengizExpress.ui.element.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tengizMKCorp.tengizExpress.databinding.HomeItemBinding
import com.tengizMKCorp.tengizExpress.ui.element.model.HomeItem


class HomeItemAdapter : ListAdapter<HomeItem, HomeItemAdapter.HomeItemViewHolder>(ItemDiffCallback()) {

    inner class HomeItemViewHolder(private val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind() {

            val source = getItem(adapterPosition)
            binding.OvalItem.setImageResource(source.image)
            binding.ovalItemTitle.text = source.title

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HomeItemBinding.inflate(layoutInflater, parent, false)
        return HomeItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
        holder.bind()
    }


}

private class ItemDiffCallback : DiffUtil.ItemCallback<HomeItem>() {
    override fun areItemsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: HomeItem, newItem: HomeItem): Boolean =
        oldItem == newItem

}

