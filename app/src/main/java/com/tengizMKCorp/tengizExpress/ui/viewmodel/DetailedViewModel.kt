package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.usecase.AddProductToLastViewedTableUseCase
import com.tengizMKCorp.tengizExpress.domain.usecase.DeleteProductFromLastViewedTableUseCase
import com.tengizMKCorp.tengizExpress.domain.usecase.GetBestSalesSortByNewestUseCase
import com.tengizMKCorp.tengizExpress.domain.usecase.ReadAllDataFromLastViewedTableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(private val getBestSalesSortByNewestUseCase : GetBestSalesSortByNewestUseCase,
                                            private val addProductToLastViewedTableUseCase: AddProductToLastViewedTableUseCase,
                                            private val readAllDataFromLastViewedTableUseCase: ReadAllDataFromLastViewedTableUseCase,
                                            private val deleteProductFromLastViewedTableUseCase: DeleteProductFromLastViewedTableUseCase
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

}