package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category.ProductsByCategoryID
import com.tengizMKCorp.tengizExpress.domain.usecase.GetProductsByCategoriesUseCase
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultByCategoryViewModel @Inject constructor(private val getProductsByCategoriesUseCase: GetProductsByCategoriesUseCase) :
    ViewModel() {
    private val _myState =
        MutableStateFlow<ResponseState<ProductsByCategoryID>>(ResponseState.Empty()) //mutable state flow
    val myState: StateFlow<ResponseState<ProductsByCategoryID>> = _myState //immutable state flow

    fun getInfo(categoryID: Int) {
        viewModelScope.launch {
            _myState.emit(ResponseState.Loading())
            val data = getProductsByCategoriesUseCase.getProductsByCategories(categoryID)
            data.collect {
                _myState.value = it
            }
        }

    }
    private var viewChangeClickCount = 0
    fun getClickCount(): Int{
        return viewChangeClickCount
    }
    fun increaseClickCount(){
        viewChangeClickCount++
    }
}