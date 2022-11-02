package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class SearchEngine(
    val category_id: String,
    val isFastShipping: Boolean,
    val isFreeShipping: Boolean,
    val isPopular: Boolean,
    val keywords: String,
    val page: Int,
    val pricerange: String,
    val shipFromCountry: String,
    val shipToCountry: String,
    val sortOrder: String,
    val sortType: String,
    val target_currency: String
)