package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_id.ProductByID
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIDUseCase @Inject constructor(
    private val storeRep: StoreRepository
) {
    suspend fun getProductByID(productID: Long): Flow<ResponseState<ProductByID>> {
        return storeRep.getProductByID(productID)
    }
}