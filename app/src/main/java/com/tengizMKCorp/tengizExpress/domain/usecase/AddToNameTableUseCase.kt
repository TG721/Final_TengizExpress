package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.local.source.product.NameModel
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import javax.inject.Inject

class AddToNameTableUseCase @Inject constructor(
    private val storeRep: StoreRepository,
) {
    suspend fun addToNameTable(name: NameModel){
        storeRep.addToNameTable(name)
    }
}