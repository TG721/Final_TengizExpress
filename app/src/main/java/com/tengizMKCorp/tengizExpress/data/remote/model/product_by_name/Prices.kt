package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_name

data class Prices(
    val builderType: String,
    val currencySymbol: String,
    val prefix: String,
    val pricesStyle: String,
    val skuId: String,
    val taxRate: String
)