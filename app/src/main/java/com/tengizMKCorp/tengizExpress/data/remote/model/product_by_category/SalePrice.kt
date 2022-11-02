package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class SalePrice(
    val currencyCode: String,
    val discount: Int,
    val formattedPrice: String,
    val minPrice: Double,
    val minPriceDiscount: Int,
    val minPriceType: Int,
    val priceType: String
)