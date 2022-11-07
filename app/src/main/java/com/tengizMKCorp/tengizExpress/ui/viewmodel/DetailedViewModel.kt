package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(private val getBestSalesSortByNewestUseCase : GetBestSalesSortByNewestUseCase,
                                            private val addProductToLastViewedTableUseCase: AddProductToLastViewedTableUseCase,
                                            private val readAllDataFromLastViewedTableUseCase: ReadAllDataFromLastViewedTableUseCase,
                                            private val deleteProductFromLastViewedTableUseCase: DeleteProductFromLastViewedTableUseCase,
                                            private val addProductToCartUseCase: AddProductToCartUseCase
): ViewModel() {

    fun addProductToLastViewedTable(product: NonDetailedProductDataBaseModel){
        viewModelScope.launch {
            addProductToLastViewedTableUseCase.addProductToLastViewedTable(product)
        }
    }
    fun deleteProductFromLastViewedTable(product: NonDetailedProductDataBaseModel){
        viewModelScope.launch {
            deleteProductFromLastViewedTableUseCase.deleteProductFromLastViewedTable(product)
        }
    }
    lateinit var products: Flow<List<NonDetailedProductDataBaseModel>>
    fun readAllDataFromLastViewedTableUseCase(){
        viewModelScope.launch {
            products = readAllDataFromLastViewedTableUseCase.readAllDataFromLastViewedTable()
        }
    }
    fun addProductToCart(product: CartModel){
        viewModelScope.launch {
            addProductToCartUseCase.addProductToCart(product)
        }
    }

}