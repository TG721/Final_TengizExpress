package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class Prices(
    val bigSalePrice: BigSalePrice,
    val builderType: String,
    val currencySymbol: String,
    val prefix: String,
    val pricesStyle: String,
    val salePrice: SalePrice,
    val skuId: String,
    val taxRate: String
)