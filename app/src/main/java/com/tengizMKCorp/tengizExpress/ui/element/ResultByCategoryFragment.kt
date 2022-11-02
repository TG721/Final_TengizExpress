package com.tengizMKCorp.tengizExpress.ui.element

import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.FragmentResultByCategoryBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.NonDetailedProductInfoAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.CategoryUIItem
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.element.model.convertDataCategoryToUI
import com.tengizMKCorp.tengizExpress.ui.element.model.convertProductByCategoryIDtoNonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.viewmodel.ResultByCategoryViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultByCategoryFragment :
    BaseFragment<FragmentResultByCategoryBinding>(FragmentResultByCategoryBinding::inflate) {
    private val viewModel: ResultByCategoryViewModel by viewModels()
    private lateinit var nonDetailedProductAdapter: NonDetailedProductInfoAdapter
    private val args by navArgs<ResultByCategoryFragmentArgs>()
    override fun setup() {
        setupDropDownMenu()
        setupRecyclerView()
    }

    override fun observers() {
        viewModel.getInfo(args.categoryId)

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
                            for (i in 0 until it.items.docs.size){
                                uiList.add(convertProductByCategoryIDtoNonDetailedProductInfo(it.items.docs.elementAt(i)))
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

    private fun setupRecyclerView() {
        nonDetailedProductAdapter = NonDetailedProductInfoAdapter()
        val productRecycler = binding.ItemsRV
        productRecycler.adapter = nonDetailedProductAdapter
        productRecycler.layoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)

    }

    private fun setupDropDownMenu() {
        val sortingMethods: Array<String> = resources.getStringArray(R.array.sort)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, sortingMethods)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
    }
}