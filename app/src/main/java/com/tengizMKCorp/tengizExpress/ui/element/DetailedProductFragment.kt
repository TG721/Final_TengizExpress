package com.tengizMKCorp.tengizExpress.ui.element

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.tengizMKCorp.tengizExpress.R
import com.tengizMKCorp.tengizExpress.databinding.FragmentDetailedProductBinding
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.element.model.convertProductByCategoryIDtoNonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.viewmodel.DetailedProductViewModel
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.launch


class DetailedProductFragment :
    BaseFragment<FragmentDetailedProductBinding>(FragmentDetailedProductBinding::inflate) {
    private val viewModel: DetailedProductViewModel by viewModels()
    private val args by navArgs<DetailedProductFragmentArgs>()
    override fun setup() {
    }

    override fun observers() {
        viewModel.getInfo(args.id)

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
                                productsUIList.add(
                                    convertProductByCategoryIDtoNonDetailedProductInfo(it.items.docs.elementAt(i)))
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

}