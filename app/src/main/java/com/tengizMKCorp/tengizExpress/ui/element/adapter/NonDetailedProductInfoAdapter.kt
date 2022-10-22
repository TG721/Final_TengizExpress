package com.tengizMKCorp.tengizExpress.ui.element.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.HomeBestSalesItemBinding
import com.tengizMKCorp.tengizExpress.databinding.HomeItemBinding
import com.tengizMKCorp.tengizExpress.ui.element.model.HomeItem
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo

class NonDetailedProductInfoAdapter : ListAdapter<NonDetailedProductInfo, NonDetailedProductInfoAdapter.ProductViewHolder>(ItemDiffCallback()) {
    inner class ProductViewHolder(private val binding: HomeBestSalesItemBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind() {

            val source = getItem(absoluteAdapterPosition)
            binding.apply {
                productName.text = source.productName
                productPriceOriginal.text = source.originalPrice.toString()
                productPriceDiscounted.text =  source.discountedPrice.toString()
                percentSaleText.text = "-"+source.discountPercentage.toString()+"%"
                Glide.with(ProductImage)
                    .load(source.productPicture)
                    .into(ProductImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HomeBestSalesItemBinding.inflate(layoutInflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind()
    }
    private class ItemDiffCallback : DiffUtil.ItemCallback<NonDetailedProductInfo>() {
        override fun areItemsTheSame(oldItem: NonDetailedProductInfo, newItem: NonDetailedProductInfo): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NonDetailedProductInfo, newItem: NonDetailedProductInfo): Boolean =
            oldItem == newItem

    }
}
