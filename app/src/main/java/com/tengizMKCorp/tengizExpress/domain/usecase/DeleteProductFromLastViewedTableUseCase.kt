package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.local.source.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import javax.inject.Inject

class DeleteProductFromLastViewedTableUseCase @Inject constructor(
    private val storeRep: StoreRepository
) {
    suspend fun deleteProductFromLastViewedTable(product: NonDetailedProductDataBaseModel){
        storeRep.deleteProductFromLastViewedTable(product)
    }
}