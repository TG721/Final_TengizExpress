package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import javax.inject.Inject

class DeleteAllFromNameTableUseCase @Inject constructor(
    private val storeRep: StoreRepository
) {
    suspend fun deleteAllFromNameTableUseCase(){
        storeRep.deleteAllFromNameTable()
    }
}