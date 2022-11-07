package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.domain.usecase.AddProductToCartUseCase
import com.tengizMKCorp.tengizExpress.domain.usecase.DeleteProductFromCartUseCase
import com.tengizMKCorp.tengizExpress.domain.usecase.ReadAllDataFromCartTableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val deleteProductFromCartUseCase: DeleteProductFromCartUseCase,
    private val readAllDataFromCartTableUseCase: ReadAllDataFromCartTableUseCase,
    private val addProductToCartUseCase: AddProductToCartUseCase,
) : ViewModel() {

    lateinit var cartProducts: Flow<List<CartModel>>
    fun readAllDataFromCartTable() {
        viewModelScope.launch {
            cartProducts = readAllDataFromCartTableUseCase.readAllDataFromCartTable()
        }
    }

    fun deleteProductFromCart(product: CartModel) {
        viewModelScope.launch {
            deleteProductFromCartUseCase.deleteProductFromCart(product)
        }
    }

    fun addProductToCart(product: CartModel) {
        viewModelScope.launch {
            addProductToCartUseCase.addProductToCart(product)
        }
    }
}