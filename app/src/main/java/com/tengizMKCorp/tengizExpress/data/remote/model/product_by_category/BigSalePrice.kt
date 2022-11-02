package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class BigSalePrice(
    val currencyCode: String,
    val formattedPrice: String,
    val minPrice: Double,
    val minPriceDiscount: Int,
    val priceStyles: PriceStyles,
    val priceType: String
)