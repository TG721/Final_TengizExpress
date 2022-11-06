package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.local.source.product.NameModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import javax.inject.Inject

class DeleteFromNameTableUseCase @Inject constructor(
    private val storeRep: StoreRepository
) {
    suspend fun deleteFromNameTable(name: NameModel){
        storeRep.deleteFromNameTable(name)
    }
}