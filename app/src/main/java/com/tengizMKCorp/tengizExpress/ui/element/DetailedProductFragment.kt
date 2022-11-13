package com.tengizMKCorp.tengizExpress.ui.element

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.databinding.FragmentDetailedProductBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.FeedbackAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.convertNonDetailedProductInfoToCartModel
import com.tengizMKCorp.tengizExpress.ui.viewmodel.DetailedViewModel
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
        loadingFeedbackData()
    }

    private fun loadingFeedbackData() {
        lifecycleScope.launch {
            val pagingData = viewModel.getFeedbackListData(args.product.id.toLong())
            pagingData.collect {
                feedbackAdapter.submitData(it)
            }
        }
    }

    override fun listeners() {
        feedbackAdapter.addLoadStateListener {
            binding.progressBar.isVisible = it.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = it.source.refresh is LoadState.Loading
        }
        binding.buttonAddToChart.setOnClickListener {
            viewModel.addProductToCart(convertNonDetailedProductInfoToCartModel(args.product))
            Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_LONG).show()
        }
    }
}