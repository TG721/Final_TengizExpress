package com.tengizMKCorp.tengizExpress.ui.element.model

import com.tengizMKCorp.tengizExpress.data.remote.model.feedback.FeedbackDoc

class FeedbackUI (
    val content: String,
    val country: String,
    val date: String,
    val displayName: String,
    val name: String,
    val photos: List<String>,
    val rating: Int
    )

fun convertFeedbackDocToFeedbackUI(feedbackDoc: FeedbackDoc): FeedbackUI{
    return FeedbackUI(
        feedbackDoc.content,feedbackDoc.country,
        feedbackDoc.date,
        feedbackDoc.displayName,feedbackDoc.name,
        feedbackDoc.photos,feedbackDoc.rating
    )
}