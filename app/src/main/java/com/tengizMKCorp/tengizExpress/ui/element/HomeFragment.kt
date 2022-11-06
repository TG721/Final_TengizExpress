package com.tengizMKCorp.tengizExpress.ui.element

import android.annotation.SuppressLint
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.FragmentHomeBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.HomeItemAdapter
import com.tengizMKCorp.tengizExpress.ui.element.adapter.NonDetailedProductInfoAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.HomeItemList
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.element.model.convertBestSalesSortedByNestToNonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.viewmodel.HomeViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeItemAdapter: HomeItemAdapter
    private lateinit var nonDetailedProductAdapter: NonDetailedProductInfoAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    override fun setup() {
        setupHomeItemRecycler()
        setupNonDetailedProductRecycler()
        setupImageSlider()
    }


    override fun observers() {
//        viewModel.getInfo()
//        viewModel.readAllDataFromLastViewedTableUseCase()

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
                            val uiList = mutableListOf<NonDetailedProductInfo>()
                            for (i in 0 until it.items.size) {
                                uiList.add(convertBestSalesSortedByNestToNonDetailedProductInfo(it.items.elementAt(
                                    i)))
                            }
                            nonDetailedProductAdapter.submitList(uiList)
                            if (nonDetailedProductAdapter.currentList.isEmpty()) {
                                binding.messageText.text = getString(R.string.not_found_items)
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

    @SuppressLint("SuspiciousIndentation")
    private fun setupImageSlider() {
        val imageSlider = binding.imageSlider
        val imageArrayList = arrayListOf<SlideModel>(
            SlideModel(R.drawable.pic_1, null, null),
            SlideModel(R.drawable.pic_2, null, null),
            SlideModel(R.drawable.pic_3, null, null),
            SlideModel(R.drawable.pic_4, null, null),
        )
        imageSlider.setImageList(imageArrayList, ScaleTypes.CENTER_CROP)
    }

    private fun setupHomeItemRecycler() {
        homeItemAdapter = HomeItemAdapter()
        val homeItemRecycler = binding.homeItemRV
        homeItemRecycler.adapter = homeItemAdapter
        homeItemRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeItemAdapter.submitList(HomeItemList)
    }

    private fun setupNonDetailedProductRecycler() {
        gridLayoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        var lastViewedItemsSize: Int? = null
        nonDetailedProductAdapter = NonDetailedProductInfoAdapter(gridLayoutManager, "Home")
        val productRecycler = binding.bestSalesSortedByNewRV
        productRecycler.layoutManager = gridLayoutManager
        productRecycler.adapter = nonDetailedProductAdapter

    }

}