package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_id

data class QuantityModule(
    val id: Int,
    val lot: Boolean,
    val multiUnitName: String,
    val name: String,
    val oddUnitName: String,
    val purchaseLimitNumMax: Int,
    val totalAvailQuantity: Int
)