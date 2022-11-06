package com.tengizMKCorp.tengizExpress.ui.element.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.databinding.CartItemBinding


class CartItemAdapter() :
    ListAdapter<CartModel, CartItemAdapter.CartItemViewHolder>(ItemDiffCallback()) {

    inner class CartItemViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            val source = getItem(absoluteAdapterPosition)
            binding.apply {
                productName.text = source.productName
                productPriceDiscounted.text = source.discountedPrice.toString()
                Glide.with(ProductImage)
                    .load(source.productPicture)
                    .into(ProductImage)
                productID.text = source.id
                productURI.text = source.productPicture
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


    private class ItemDiffCallback : DiffUtil.ItemCallback<CartModel>() {
        override fun areItemsTheSame(
            oldItem: CartModel,
            newItem: CartModel,
        ): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: CartModel,
            newItem: CartModel,
        ): Boolean =
            oldItem == newItem

    }
}
