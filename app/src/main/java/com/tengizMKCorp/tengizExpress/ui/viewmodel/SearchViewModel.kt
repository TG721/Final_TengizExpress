package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.local.source.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.usecase.GetProductsByCategoriesUseCase
import com.tengizMKCorp.tengizExpress.domain.usecase.ReadAllDataFromLastViewedTableUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val readAllDataFromLastViewedTableUseCase: ReadAllDataFromLastViewedTableUseCase) :
    ViewModel() {
    lateinit var products: Flow<List<NonDetailedProductDataBaseModel>>
        fun readAllDataFromLastViewedTable(){
            viewModelScope.launch {
                products = readAllDataFromLastViewedTableUseCase.readAllDataFromLastViewedTable()
            }
        }
}