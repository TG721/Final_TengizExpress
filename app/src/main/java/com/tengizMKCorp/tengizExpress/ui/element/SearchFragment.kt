package com.tengizMKCorp.tengizExpress.ui.element

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tengizMKCorp.tengizExpress.databinding.FragmentResultByCategoryBinding
import com.tengizMKCorp.tengizExpress.databinding.FragmentSearchBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.NonDetailedProductInfoAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.element.model.convertProductByCategoryIDtoNonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.viewmodel.HomeViewModel
import com.tengizMKCorp.tengizExpress.ui.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.data.local.source.product.NameModel
import com.tengizMKCorp.tengizExpress.ui.element.adapter.SearchedNameAdapter

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var lastViewItemAdapter: NonDetailedProductInfoAdapter
    private lateinit var lastWordsAdapter: SearchedNameAdapter
    override fun setup() {
        viewModel.readAllDataFromLastViewedTable()
        viewModel.readAllDataFromNameTable()
        val gridLayoutManager =
            GridLayoutManager(requireContext(), 1, GridLayoutManager.VERTICAL, false)
        lastViewItemAdapter = NonDetailedProductInfoAdapter(gridLayoutManager, "Search")
        val recyclerView = binding.lastViewedRV
        recyclerView.adapter = lastViewItemAdapter
        recyclerView.layoutManager = gridLayoutManager
        setupLastSearchedWords()

    }

    private fun setupLastSearchedWords() {
        val linearLayoutManager = LinearLayoutManager(requireContext())
        lastWordsAdapter = SearchedNameAdapter( {
            viewModel.deleteFromNameTable(it)
        }){
            binding.searchView.setQuery(it.name,false)
        }
        val recyclerView = binding.latestSearchRV
        recyclerView.adapter = lastWordsAdapter
        recyclerView.layoutManager = linearLayoutManager
    }

    override fun observers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect {
                    val productsUIList = mutableListOf<NonDetailedProductInfo>()
                    for (i in it.indices) {
                        productsUIList.add(NonDetailedProductInfo(it.elementAt(i).id,
                            it.elementAt(i).originalPrice,
                            it.elementAt(i).discountedPrice,
                            it.elementAt(i).discountPercentage,
                            it.elementAt(i).productName,
                            it.elementAt(i).productPicture))
                    }
                    lastViewItemAdapter.submitList(productsUIList)
                }
            }

        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.names.collect {
            lastWordsAdapter.submitList(it)
        }
    }
}
        }

    override fun listeners() {
        binding.searchView?.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query!="") {
                    viewModel.addToNameTable(NameModel(query!!))
                    val action =
                        SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(query!!) //current item
                    binding.rootLayout.findNavController().navigate(action)
                    return false;
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
        binding.cleanAllHistoryTV.setOnClickListener {
            viewModel.deleteAllFromNameTable()
        }
    }


}