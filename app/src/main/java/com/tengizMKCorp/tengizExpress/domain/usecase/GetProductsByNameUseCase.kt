package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_name.ProductByName
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsByNameUseCase @Inject constructor(
    private val storeRep: StoreRepository
) {
    suspend fun getProductsByName(name: String): Flow<ResponseState<ProductByName>> {
        return storeRep.getProductsByName(name)
    }
}