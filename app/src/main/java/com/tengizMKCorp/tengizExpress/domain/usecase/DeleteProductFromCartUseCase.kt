package com.tengizMKCorp.tengizExpress.domain.usecase

import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import javax.inject.Inject

class DeleteProductFromCartUseCase @Inject constructor(
    private val storeRep: StoreRepository
) {
    suspend fun deleteProductFromCart(product: CartModel){
        storeRep.deleteProductFromCart(product)
    }
}