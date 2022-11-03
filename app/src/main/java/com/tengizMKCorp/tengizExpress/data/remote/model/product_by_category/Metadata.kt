package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class Metadata(
    val evaluation: Evaluation,
    val image: Image,
    val itemType: String,
    val lunchTime: String,
    val nativeCardType: String,
    val prices: Prices,
    val productId: String,
    val productType: String,
    val sellingPoints: List<SellingPoint>,
    val title: Title,
    val trace: Trace,
    val trade: Trade
)