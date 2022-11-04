package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.local.source.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAllDataFromLastViewedTableUseCase @Inject constructor(
    private val storeRep: StoreRepository,
) {
    suspend fun readAllDataFromLastViewedTable(): Flow<List<NonDetailedProductDataBaseModel>> {
       return storeRep.readAllDataFromLastViewedTable()
    }
}