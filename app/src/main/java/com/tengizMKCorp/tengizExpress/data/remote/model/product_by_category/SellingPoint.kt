package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class SellingPoint(
    val dataSourceIds: String,
    val group: Int,
    val position: Int,
    val sellingPointTagId: String,
    val source: String,
    val tagContent: TagContent,
    val tagStyleType: String
)