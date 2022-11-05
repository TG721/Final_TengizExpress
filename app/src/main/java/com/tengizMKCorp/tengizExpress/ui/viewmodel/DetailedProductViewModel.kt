package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_id.ProductByID
import com.tengizMKCorp.tengizExpress.domain.usecase.GetProductByIDUseCase
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedProductViewModel @Inject constructor(private val getProductByIDUseCase: GetProductByIDUseCase): ViewModel() {
    private val _myState =
        MutableStateFlow<ResponseState<ProductByID>>(ResponseState.Empty()) //mutable state flow
    val myState: StateFlow<ResponseState<ProductByID>> = _myState //immutable state flow

    fun getInfo(productID: String) {
        viewModelScope.launch {
            _myState.emit(ResponseState.Loading())
            val data = getProductByIDUseCase.getProductByID(productID.toLong())
            data.collect{
                _myState.value = it
            }
        }

    }
}