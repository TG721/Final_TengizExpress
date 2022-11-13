package com.tengizMKCorp.tengizExpress.ui.element.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.data.remote.model.feedback.FeedbackDoc
import com.tengizMKCorp.tengizExpress.databinding.FeedbackItemBinding

class FeedbackAdapter() :
    PagingDataAdapter<FeedbackDoc, FeedbackAdapter.FeedbackItemViewHolder>(ItemDiffCallback()) {

    inner class FeedbackItemViewHolder(private val binding: FeedbackItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {

            val source = getItem(absoluteAdapterPosition)
            binding.apply {
                userName.text = source!!.name
                date.text = source.date.subSequence(0, 7)
                feedbackContent.text = source.content
                when (source.rating) {
                    1 -> {
                        changeToGold(binding.star1)
                    }
                    2 -> {
                        changeToGold(binding.star1)
                        changeToGold(binding.star2)
                    }
                    3 -> {
                        changeToGold(binding.star1)
                        changeToGold(binding.star2)
                        changeToGold(binding.star3)
                    }
                    4 -> {
                        changeToGold(binding.star1)
                        changeToGold(binding.star2)
                        changeToGold(binding.star3)
                        changeToGold(binding.star4)
                    }
                    5 -> {
                        changeToGold(binding.star1)
                        changeToGold(binding.star2)
                        changeToGold(binding.star3)
                        changeToGold(binding.star4)
                        changeToGold(binding.star5)
                    }
                }
            }
        }

        private fun changeToGold(x: ImageView) {
            Glide.with(x)
                .load(R.drawable.star_regular)
                .into(x)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FeedbackItemBinding.inflate(layoutInflater, parent, false)
        return FeedbackItemViewHolder(binding)

    }

    override fun onBindViewHolder(holder: FeedbackItemViewHolder, position: Int) {
        return holder.bind()
    }


    private class ItemDiffCallback : DiffUtil.ItemCallback<FeedbackDoc>() {
        override fun areItemsTheSame(
            oldItem: FeedbackDoc,
            newItem: FeedbackDoc,
        ): Boolean =
            oldItem.name == newItem.name

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: FeedbackDoc,
            newItem: FeedbackDoc,
        ): Boolean =
            oldItem == newItem

    }
}
