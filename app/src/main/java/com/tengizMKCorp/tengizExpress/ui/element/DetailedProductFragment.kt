package com.tengizMKCorp.tengizExpress.ui.element

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.databinding.FragmentDetailedProductBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.CategoryUIItemAdapter
import com.tengizMKCorp.tengizExpress.ui.element.adapter.FeedbackAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.*
import com.tengizMKCorp.tengizExpress.ui.viewmodel.DetailedViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailedProductFragment :
    BaseFragment<FragmentDetailedProductBinding>(FragmentDetailedProductBinding::inflate) {
    private val args by navArgs<DetailedProductFragmentArgs>()
    private val viewModel: DetailedViewModel by viewModels()
    private lateinit var feedbackAdapter: FeedbackAdapter
    override fun setup() {
        binding.apply {
            productName.text = args.product.productName
            productPriceOriginal.text = args.product.originalPrice.toString() + "$"
            productPriceDiscounted.text = args.product.discountedPrice.toString() + "$"
            percentSaleText.text = "-" + args.product.discountPercentage.toString() + "%"
            Glide.with(ProductImage)
                .load(args.product.productPicture)
                .into(ProductImage)
        }
        setupFeedbackRecycler()
    }

    private fun setupFeedbackRecycler() {
        feedbackAdapter = FeedbackAdapter()
        val recycler = binding.feedbackRecycler
        recycler.adapter = feedbackAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun observers() {
        viewModel.readAllDataFromLastViewedTableUseCase()
        lifecycleScope.launch {
            viewModel.products.collect { it ->
                val lastViewedItemsSize = it.size
                if (lastViewedItemsSize == 3) {
                    viewModel.deleteProductFromLastViewedTable(it.elementAt(0))
                }
            }
        }
        viewModel.addProductToLastViewedTable((NonDetailedProductDataBaseModel(args.product.id,
            args.product.originalPrice,
            args.product.discountedPrice,
            args.product.discountPercentage,
            args.product.productName,
            args.product.productPicture))
        )
        viewModel.getInfo(args.product.id.toLong())

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myState.collect {
                    when (it) {
                        is ResponseState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is ResponseState.Error -> {
//                            binding.messageText.text = "could not get the items"
                            binding.messageText.text = it.message
                            binding.messageText.visibility = View.VISIBLE
                            binding.progressBar.visibility = View.GONE
                        }
                        is ResponseState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val uiList = mutableListOf<FeedbackUI>()
                            for (i in 0 until it.items.docs.size) {
                                uiList.add(convertFeedbackDocToFeedbackUI(it.items.docs.elementAt(i)))
                            }
                            feedbackAdapter.submitList(uiList)
                            if (feedbackAdapter.currentList.isEmpty()) {
                                binding.messageText.text = getString(R.string.no_feedback)
                                binding.messageText.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.VISIBLE
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    override fun listeners() {
        binding.buttonAddToChart.setOnClickListener {
            viewModel.addProductToCart(convertNonDetailedProductInfoToCartModel(args.product))
            Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_LONG).show()
        }
    }
}