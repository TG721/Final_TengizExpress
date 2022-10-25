package com.tengizMKCorp.tengizExpress.ui.element

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.FragmentCategoriesBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.CategoryUIItemAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.CategoryUIItem
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.element.model.convertBestSalesSortedByNestToNonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.element.model.convertDataCategoryToUI
import com.tengizMKCorp.tengizExpress.ui.viewmodel.CategoriesViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment :
    BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {
    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoryUIItemAdapter
    override fun setup() {
        setupCategoriesRecycler()
    }

    private fun setupCategoriesRecycler() {
        categoriesAdapter = CategoryUIItemAdapter()
        val recycler = binding.categoriesRV
        recycler.adapter = categoriesAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
    override fun observers() {
        viewModel.getInfo()

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
                            val uiList = mutableListOf<CategoryUIItem>()
                            for (i in 0 until it.items.size){
                                uiList.add(convertDataCategoryToUI(it.items.elementAt(i)))
                            }
                            categoriesAdapter.submitList(uiList)
                            if (categoriesAdapter.currentList.isEmpty()) {
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

}