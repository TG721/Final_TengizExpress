package com.tengizMKCorp.tengizExpress.ui.element

import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.FragmentResultByCategoryBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.NonDetailedProductInfoAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
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
    private var productsUIList = mutableListOf<NonDetailedProductInfo>()
    private lateinit var productRecycler: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    override fun setup() {
        setupPageTitle()
        setupDropDownMenu()
        setupRecyclerView()
        setupGridView()
    }

    private fun setupGridView() {
        val viewChangeClickCount=viewModel.getClickCount()
        if(viewChangeClickCount%2==1) {
            Glide.with(this@ResultByCategoryFragment)
                .load(R.drawable.ic_linear_view_icon)
                .into(binding.viewType)
            gridLayoutManager.spanCount = 1
        }
        else {
            Glide.with(this@ResultByCategoryFragment)
                .load(R.drawable.ic_grid_view_icon)
                .into(binding.viewType)
            gridLayoutManager.spanCount = 2
        }
    }

    private fun setupPageTitle() {
        binding.title.text = args.categoryName
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
                            productsUIList = mutableListOf<NonDetailedProductInfo>()
                            for (i in 0 until it.items.docs.size){
                                productsUIList.add(convertProductByCategoryIDtoNonDetailedProductInfo(it.items.docs.elementAt(i)))
                            }
                            nonDetailedProductAdapter.submitList(productsUIList)
                            if (nonDetailedProductAdapter.currentList.isEmpty()) {
                                binding.messageText.text = getString(R.string.not_found_items)
                                binding.messageText.visibility = View.VISIBLE
                                binding.progressBar.visibility = View.GONE
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
    override fun listeners() {
        binding.autoCompleteTextView.setOnItemClickListener { adapterView, _, i, _ ->
            when {
                (adapterView.getItemAtPosition(i)).toString()=="ascending price" -> {
                    val asc = productsUIList.sortedBy {
                        it.discountedPrice
                    }
                    nonDetailedProductAdapter.submitList(asc)
                }
                (adapterView.getItemAtPosition(i)).toString()=="decreasing price" -> {
                    val dec = productsUIList.sortedByDescending {
                        it.discountedPrice
                    }
                    nonDetailedProductAdapter.submitList(dec)
                }
                else -> {}
            }
        }
        binding.viewType.setOnClickListener {
            viewModel.increaseClickCount()
            setupGridView()
        }
    }

    private fun setupRecyclerView() {
        productRecycler = binding.ItemsRV
        gridLayoutManager = GridLayoutManager(requireContext(),2, GridLayoutManager.VERTICAL,false)
        nonDetailedProductAdapter = NonDetailedProductInfoAdapter(gridLayoutManager)
        productRecycler.layoutManager = gridLayoutManager
        productRecycler.adapter = nonDetailedProductAdapter


    }

    private fun setupDropDownMenu() {
        val sortingMethods: Array<String> = resources.getStringArray(R.array.sort)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, sortingMethods)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)
        binding.autoCompleteTextView.showSoftInputOnFocus = false
    }
}