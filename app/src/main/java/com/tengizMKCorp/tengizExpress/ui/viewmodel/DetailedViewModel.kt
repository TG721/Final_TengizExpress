package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.data.remote.model.feedback.feedback
import com.tengizMKCorp.tengizExpress.domain.usecase.*
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(private val getBestSalesSortByNewestUseCase : GetBestSalesSortByNewestUseCase,
                                            private val addProductToLastViewedTableUseCase: AddProductToLastViewedTableUseCase,
                                            private val readAllDataFromLastViewedTableUseCase: ReadAllDataFromLastViewedTableUseCase,
                                            private val deleteProductFromLastViewedTableUseCase: DeleteProductFromLastViewedTableUseCase,
                                            private val addProductToCartUseCase: AddProductToCartUseCase,
                                            private val getFeedbackUseCase: GetFeedbackUseCase
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
    private val _myState =
        MutableStateFlow<ResponseState<feedback>>(ResponseState.Empty()) //mutable state flow
    val myState: StateFlow<ResponseState<feedback>> = _myState //immutable state flow
    fun getInfo(productID: Long) {
        viewModelScope.launch {
            _myState.emit(ResponseState.Loading())
            val data = getFeedbackUseCase.getProductFeedbacksByID(productID)
            data.collect{
                _myState.value = it
            }
        }

    }
}