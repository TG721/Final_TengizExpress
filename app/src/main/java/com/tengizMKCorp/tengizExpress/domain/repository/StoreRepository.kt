package com.tengizMKCorp.tengizExpress.domain.repository

import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Headers

interface StoreRepository {
    suspend fun getBestSalesProductsSortByNewest(): Flow<ResponseState<List<BestSalesSortedByNewestItem>>>
    suspend fun getCategories(): Flow<ResponseState<List<CategoryItem>>>
}