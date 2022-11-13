package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.remote.model.feedback.feedback
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFeedbackUseCase @Inject constructor(
    private val storeRep: StoreRepository,
) {
    suspend fun getProductFeedbacksByID(productID: Long): Flow<ResponseState<feedback>> {
        return storeRep.getProductFeedbacksByID(productID)
    }
}