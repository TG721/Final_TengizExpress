package com.tengizMKCorp.tengizExpress.ui.element

import android.util.Log.d
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.FragmentSearchResultBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.NonDetailedProductInfoAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.element.model.convertProductByName
import com.tengizMKCorp.tengizExpress.ui.viewmodel.SearchResultViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchResultFragment :
    BaseFragment<FragmentSearchResultBinding>(FragmentSearchResultBinding::inflate) {
    private val args by navArgs<SearchResultFragmentArgs>()
    private val viewModel: SearchResultViewModel by viewModels()
    private lateinit var nonDetailedProductAdapter: NonDetailedProductInfoAdapter
    override fun setup() {
        binding.resultTitle.text = args.inputName
        setupRecycler()
    }

    private fun setupRecycler() {
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        nonDetailedProductAdapter = NonDetailedProductInfoAdapter(gridLayoutManager, "SearchResults")
        val productRecycler = binding.SearchResultRV
        productRecycler.layoutManager = gridLayoutManager
        productRecycler.adapter = nonDetailedProductAdapter

    }

    override fun observers() {
        viewModel.getProductsByName(args.inputName)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.myState.collect {
                    when (it) {
                        is ResponseState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.resultTitle.visibility = View.GONE
                            binding.messageText.visibility = View.GONE


                        }
                        is ResponseState.Error -> {
//                            binding.messageText.text = "could not get the items"
                            binding.messageText.text = it.message
                            binding.messageText.visibility = View.VISIBLE
                            binding.resultTitle.visibility = View.GONE
                            binding.progressBar.visibility = View.GONE
                        }
                        is ResponseState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            val uiList = mutableListOf<NonDetailedProductInfo>()
                            for (i in 0 until it.items.docs.size) {
                                uiList.add(convertProductByName(it.items.docs.elementAt(i)))
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


}