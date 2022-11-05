package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.data.remote.model.productByName.ProductByName
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByNameUseCase @Inject constructor(
    private val storeRep: StoreRepository
) {
    suspend fun getProductsByName(name: String): Flow<ResponseState<List<ProductByName>>> {
        return storeRep.getProductsByName(name)
    }
}