package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.local.source.product.NameModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val readAllDataFromLastViewedTableUseCase: ReadAllDataFromLastViewedTableUseCase,
    private val deleteAllFromNameTableUseCase: DeleteAllFromNameTableUseCase,
    private val deleteFromNameTableUseCase: DeleteFromNameTableUseCase,
    private val addToNameTableUseCase: AddToNameTableUseCase,
    private val readAllDataFromNameTableUseCase: ReadAllDataFromNameTableUseCase,
) :
    ViewModel() {
    lateinit var products: Flow<List<NonDetailedProductDataBaseModel>>
    fun readAllDataFromLastViewedTable() {
        viewModelScope.launch {
            products = readAllDataFromLastViewedTableUseCase.readAllDataFromLastViewedTable()
        }
    }

    lateinit var names: Flow<List<NameModel>>
    fun readAllDataFromNameTable() {
        viewModelScope.launch {
            names = readAllDataFromNameTableUseCase.readAllDataFromNameTable()
        }
    }

    fun deleteFromNameTable(name: NameModel) {
        viewModelScope.launch {
            deleteFromNameTableUseCase.deleteFromNameTable(name)
        }
    }

    fun deleteAllFromNameTable() {
        viewModelScope.launch {
            deleteAllFromNameTableUseCase.deleteAllFromNameTableUseCase()
        }
    }

    fun addToNameTable(name: NameModel) {
        viewModelScope.launch {
            addToNameTableUseCase.addToNameTable(name)
        }
    }
}