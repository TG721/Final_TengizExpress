package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllDataFromCartTableUseCase @Inject constructor(
private val storeRep: StoreRepository
) {
    suspend fun readAllDataFromCartTable(): Flow<List<CartModel>> {
        return storeRep.readAllDataFromCartTable()
    }
}