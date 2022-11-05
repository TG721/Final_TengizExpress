package com.tengizMKCorp.tengizExpress.ui.element.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.databinding.NonDetailedProductItemGridBinding
import com.tengizMKCorp.tengizExpress.databinding.NonDetailedProductItemListBinding
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo

class NonDetailedProductInfoAdapter(
    private val gridLayoutManager: GridLayoutManager,
    val onClick: (product: NonDetailedProductInfo) -> Unit,
) : ListAdapter<NonDetailedProductInfo, RecyclerView.ViewHolder>(ItemDiffCallback()) {
    companion object Const {
        const val VIEW_TYPE_1 = 1
        const val VIEW_TYPE_2 = 2
    }

    inner class ProductGridViewHolder(private val binding: NonDetailedProductItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            val source = getItem(absoluteAdapterPosition)
            binding.apply {
                productName.text = source.productName
                productPriceOriginal.text = source.originalPrice.toString()
                productPriceDiscounted.text = source.discountedPrice.toString()
                percentSaleText.text = "-" + source.discountPercentage.toString() + "%"
                Glide.with(ProductImage)
                    .load(source.productPicture)
                    .into(ProductImage)

                cardView.setOnClickListener {
                    val pos = absoluteAdapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        onClick(source)
                    }
                }
            }
        }
    }

    inner class ProductListViewHolder(private val binding: NonDetailedProductItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            val source = getItem(absoluteAdapterPosition)
            binding.apply {
                productName.text = source.productName
                productPriceOriginal.text = source.originalPrice.toString()
                productPriceDiscounted.text = source.discountedPrice.toString()
                percentSaleText.text = "-" + source.discountPercentage.toString() + "%"
                Glide.with(ProductImage)
                    .load(source.productPicture)
                    .into(ProductImage)
                cardView.setOnClickListener {
                    val pos = absoluteAdapterPosition
                    if (pos != RecyclerView.NO_POSITION) {
                        onClick(source)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_2) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = NonDetailedProductItemGridBinding.inflate(layoutInflater, parent, false)
            return ProductGridViewHolder(binding)
        } else {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = NonDetailedProductItemListBinding.inflate(layoutInflater, parent, false)
            return ProductListViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_2) return (holder as ProductGridViewHolder).bind()
        else return (holder as ProductListViewHolder).bind()
    }

    override fun getItemViewType(position: Int): Int {
        return if (gridLayoutManager.spanCount == 2) {
            VIEW_TYPE_2
        } else {
            VIEW_TYPE_1
        }
    }

    private class ItemDiffCallback : DiffUtil.ItemCallback<NonDetailedProductInfo>() {
        override fun areItemsTheSame(
            oldItem: NonDetailedProductInfo,
            newItem: NonDetailedProductInfo,
        ): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: NonDetailedProductInfo,
            newItem: NonDetailedProductInfo,
        ): Boolean =
            oldItem == newItem

    }
}
