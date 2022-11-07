package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category.ProductsByCategoryID
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByCategoriesUseCase @Inject constructor(
    private val storeRep: StoreRepository,
) {
    suspend fun getProductsByCategories(categoryID: Long): Flow<ResponseState<ProductsByCategoryID>> {
        return storeRep.getProductsByCategories(categoryID)
    }
}