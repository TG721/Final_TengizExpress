package com.tengizMKCorp.tengizExpress.data.remote.repository

import com.tengizMKCorp.tengizExpress.data.remote.StoreApi
import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.http.Headers
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val api: StoreApi) : StoreRepository {
    override suspend fun getBestSalesProductsSortByNewest(): Flow<ResponseState<List<BestSalesSortedByNewestItem>>> = flow{
        try {
            val response: Response<List<BestSalesSortedByNewestItem>> = api.getBestSalesProductsSortByNewest()
            val body: List<BestSalesSortedByNewestItem>? = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun getCategories(): Flow<ResponseState<List<CategoryItem>>> = flow {
        try {
            val response: Response<List<CategoryItem>> = api.getCategories()
            val body: List<CategoryItem>? = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}