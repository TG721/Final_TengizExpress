package com.tengizMKCorp.tengizExpress.data.remote.model.feedback

data class feedback(
    val currentPage: String,
    val docs: List<FeedbackDoc>,
    val hasNextPage: Boolean,
    val hasPrevPage: Boolean,
    val limit: Int,
    val nextPage: Int,
    val prevPage: Any,
    val totalPages: Int
)