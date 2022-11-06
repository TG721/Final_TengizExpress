package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_name

data class ProductNameDoc(
    val app_sale_price: Double,
    val app_sale_price_currency: String,
    val discount_rate: Int,
    val evaluate_rate: Double,
    val keywords: String,
    val lastest_volume: String,
    val metadata: Metadata,
    val product_detail_url: String,
    val product_id: String,
    val product_main_image_url: String,
    val product_title: String
)