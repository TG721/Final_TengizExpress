package com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest

data class Price(
    val _id: String,
    val app_sale_price: Double,
    val app_sale_price_currency: String,
    val modificationDate: String,
    val original_price: Double,
    val sale_price: Double,
    val sale_price_currency: String
)