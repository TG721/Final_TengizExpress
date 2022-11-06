package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_name

data class ProductByName(
    val docs: List<ProductNameDoc>,
    val hasNextPage: Boolean,
    val hasPrevPage: Boolean,
    val limit: Int,
    val nextPage: Int,
    val page: Int,
    val totalPages: Int,
    val total_record_count: Int
)