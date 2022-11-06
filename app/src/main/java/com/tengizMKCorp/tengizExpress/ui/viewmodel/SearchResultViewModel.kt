package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_name.ProductByName
import com.tengizMKCorp.tengizExpress.domain.usecase.GetProductsByNameUseCase
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(private val getProductsByNameUseCase: GetProductsByNameUseCase) :
    ViewModel() {
    private val _myState =
        MutableStateFlow<ResponseState<ProductByName>>(ResponseState.Empty()) //mutable state flow
    val myState: StateFlow<ResponseState<ProductByName>> = _myState //immutable state flow

     fun getProductsByName(productName: String) {
         viewModelScope.launch {
             _myState.emit(ResponseState.Loading())
             val data = getProductsByNameUseCase.getProductsByName(productName)
             data.collect {
                 _myState.value = it
             }
         }
     }
}