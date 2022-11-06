package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import javax.inject.Inject

class AddProductToLastViewedTableUseCase @Inject constructor(
    private val storeRep: StoreRepository,
) {
    suspend fun addProductToLastViewedTable(product: NonDetailedProductDataBaseModel){
        storeRep.addProductToLastViewedTable(product)
    }
}