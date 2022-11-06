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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tengizMKCorp.tengizExpress.databinding.FragmentCartBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.CartItemAdapter
import com.tengizMKCorp.tengizExpress.ui.element.adapter.HomeItemAdapter
import com.tengizMKCorp.tengizExpress.ui.element.adapter.NonDetailedProductInfoAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.model.NonDetailedProductInfo
import com.tengizMKCorp.tengizExpress.ui.viewmodel.CartViewModel
import com.tengizMKCorp.tengizExpress.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding>(FragmentCartBinding::inflate) {
    private val viewModel: CartViewModel by viewModels()
    private lateinit var cartItemAdapter: CartItemAdapter
    override fun setup() {
        setUpRecycler()
    }

    private fun setUpRecycler() {
        cartItemAdapter = CartItemAdapter()
        val cartRecycler = binding.cartItemSRV
        cartRecycler.layoutManager =  LinearLayoutManager(requireContext())
        cartRecycler.adapter = cartItemAdapter
    }

    override fun observers() {
        viewModel.readAllDataFromCartTable()
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.cartProducts.collect {
                    cartItemAdapter.submitList(it)
                }
            }

        }
    }
}