package com.tengizMKCorp.tengizExpress.ui.element.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.databinding.CartItemBinding
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.viewmodel.HomeViewModel


class CartItemAdapter() :
    ListAdapter<NonDetailedProductInfo, CartItemAdapter.CartItemViewHolder>(ItemDiffCallback()) {

    inner class CartItemViewHolder(private val binding: CartItemBinding) :
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
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(layoutInflater, parent, false)
        return CartItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        return holder.bind()
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
