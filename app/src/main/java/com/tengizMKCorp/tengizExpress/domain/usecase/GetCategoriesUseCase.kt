package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val storeRep: StoreRepository
) {
    suspend fun getCategories(): Flow<ResponseState<List<CategoryItem>>> {
        return storeRep.getCategories()
    }
}