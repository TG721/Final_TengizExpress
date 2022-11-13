package com.tengizMKCorp.tengizExpress.data.remote.model.feedback

data class FeedbackDoc(
    val content: String,
    val country: String,
    val date: String,
    val displayName: String,
    val name: String,
    val photos: List<String>,
    val rating: Int
)