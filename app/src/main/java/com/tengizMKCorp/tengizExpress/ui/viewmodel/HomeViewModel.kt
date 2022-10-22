package com.tengizMKCorp.tengizExpress.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.domain.usecase.GetBestSalesSortByNewestUseCase
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel  @Inject constructor(private val getBestSalesSortByNewestUseCase : GetBestSalesSortByNewestUseCase): ViewModel() {
    private val _myState =
        MutableStateFlow<ResponseState<List<BestSalesSortedByNewestItem>>>(ResponseState.Empty()) //mutable state flow
    val myState: StateFlow<ResponseState<List<BestSalesSortedByNewestItem>>> = _myState //immutable state flow

    fun getInfo() {
        viewModelScope.launch {
            _myState.emit(ResponseState.Loading())
            val data = getBestSalesSortByNewestUseCase.getBestSalesSortByNewestUseCase()
            data.collect{
                _myState.value = it
            }
        }

    }
}