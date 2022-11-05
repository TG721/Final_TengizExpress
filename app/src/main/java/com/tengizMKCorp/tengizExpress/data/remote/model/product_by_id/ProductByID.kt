package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_id

import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.ProductSmallImageUrls

data class ProductByID(
    val app_sale_price: Double,
    val app_sale_price_currency: String,
    val discount_rate: Double,
    val evaluate_rate: String,
    val first_level_category_id: Int,
    val lastest_volume: Int,
    val metadata: Metadata,
    val product_detail_url: String,
    val product_id: String,
    val product_small_image_urls: ProductSmallImageUrls,
    val second_level_category_id: Int,
    val shop_id: Int,
    val shop_name: String,
    val shop_url: String,
    val wishedCount: Int
)