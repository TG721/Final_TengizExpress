package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class ProductsByCategoryID(
    val docs: List<Doc>,
    val hasNextPage: Boolean,
    val hasPrevPage: Boolean,
    val limit: Int,
    val nextPage: Int,
    val page: Int,
    val prvPage: Any,
    val totalPages: Int,
    val total_record_count: Int
)