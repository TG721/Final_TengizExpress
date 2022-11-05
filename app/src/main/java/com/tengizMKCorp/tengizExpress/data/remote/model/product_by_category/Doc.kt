package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class Doc(
    val app_sale_price: Double,
    val discount_rate: Int,
    val product_detail_url: String,
    val product_id: String,
    val product_main_image_url: String,
    val product_title: String
)