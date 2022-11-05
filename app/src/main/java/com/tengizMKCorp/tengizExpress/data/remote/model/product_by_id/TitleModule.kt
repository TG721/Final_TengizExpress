package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_id

data class TitleModule(
    val id: Int,
    val name: String,
    val orig: Boolean,
    val origTitle: Boolean,
    val product_description: String,
    val product_title: String,
    val subject: String,
    val titleTags: List<Any>,
    val tradeCount: Int,
    val tradeCountUnit: String,
    val trans: Boolean,
    val transTitle: Boolean
)