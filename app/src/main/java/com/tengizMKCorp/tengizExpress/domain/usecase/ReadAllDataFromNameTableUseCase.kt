package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.local.source.product.NameModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllDataFromNameTableUseCase @Inject constructor(
    private val storeRep: StoreRepository,
) {
    suspend fun readAllDataFromNameTable(): Flow<List<NameModel>> {
        return storeRep.readAllDataFromNameTable()
    }
}