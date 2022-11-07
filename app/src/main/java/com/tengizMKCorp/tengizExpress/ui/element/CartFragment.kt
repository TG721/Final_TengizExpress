package com.tengizMKCorp.tengizExpress.ui.element

import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.databinding.FragmentCartBinding
import com.tengizMKCorp.tengizExpress.ui.element.adapter.CartItemAdapter
import com.tengizMKCorp.tengizExpress.ui.element.common.BaseFragment
import com.tengizMKCorp.tengizExpress.ui.element.common.SwipeGesture
import com.tengizMKCorp.tengizExpress.ui.viewmodel.CartViewModel
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
        cartRecycler.layoutManager = LinearLayoutManager(requireContext())
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

    override fun listeners() {
        handleSwipe()
        binding.floatingActionButton.setOnClickListener {
            val action = CartFragmentDirections.actionCartFragmentToHomeFragment()
            binding.rootLayout.findNavController().navigate(action)
        }
    }

    private fun handleSwipe() {
        lateinit var productName: String
        lateinit var discountedPrice: String
        lateinit var productPicture: String
        lateinit var productID: String
        lateinit var currentCartProduct: CartModel
        val swipeGesture = object : SwipeGesture(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                productName =
                    ((viewHolder.itemView as androidx.constraintlayout.widget.ConstraintLayout).getChildAt(
                        1) as TextView).text.toString()
                discountedPrice =
                    ((viewHolder.itemView as androidx.constraintlayout.widget.ConstraintLayout).getChildAt(
                        2) as TextView).text.toString()
                productID =
                    ((viewHolder.itemView as androidx.constraintlayout.widget.ConstraintLayout).getChildAt(
                        4) as TextView).text.toString()
                productPicture =
                    ((viewHolder.itemView as androidx.constraintlayout.widget.ConstraintLayout).getChildAt(
                        5) as TextView).text.toString()
                currentCartProduct =
                    CartModel(productID, discountedPrice.toDouble(), productName, productPicture)
                viewModel.deleteProductFromCart(currentCartProduct)
                Snackbar.make(binding.root, "item was removed", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo") {
                        viewModel.addProductToCart(currentCartProduct)
                    }
                }.show()
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.cartItemSRV)

    }
}