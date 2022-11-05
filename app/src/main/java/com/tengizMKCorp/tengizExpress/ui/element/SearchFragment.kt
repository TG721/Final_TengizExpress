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
import com.tengizMKCorp.tengizExpress.R

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var lastViewItemAdapter: NonDetailedProductInfoAdapter
    override fun setup() {
    viewModel.readAllDataFromLastViewedTable()
        val gridLayoutManager = GridLayoutManager(requireContext(),1, GridLayoutManager.VERTICAL,false)
        lastViewItemAdapter = NonDetailedProductInfoAdapter(gridLayoutManager){
            val action =
                SearchFragmentDirections.actionSearchFragmentToDetailedProductFragment(it)
            binding.rootLayout.findNavController().navigate(action)
        }
        val recyclerView = binding.lastViewedRV
        recyclerView.adapter = lastViewItemAdapter
        recyclerView.layoutManager = gridLayoutManager
        popStack()

    }

    private fun popStack() {
//        findNavController().popBackStack(R.id.homeFragment, false)
        findNavController().popBackStack(R.id.categoriesFragment, false)
//        findNavController().popBackStack(R.id.resultByCategoryFragment, false)
//        findNavController().popBackStack(R.id.detailedProductFragment, false)
    }

    override fun observers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.products.collect{
                    val productsUIList = mutableListOf<NonDetailedProductInfo>()
                    for (i in it.indices){
                        productsUIList.add(NonDetailedProductInfo(it.elementAt(i).id, it.elementAt(i).originalPrice,it.elementAt(i).discountedPrice,it.elementAt(i).discountPercentage,it.elementAt(i).productName, it.elementAt(i).productPicture))
                    }
                    lastViewItemAdapter.submitList(productsUIList)
                }

            }
        }
    }


}