package com.tengizMKCorp.tengizExpress.data.remote.model.category

data class CategoryItem(
    val __v: Int,
    val _id: String,
    val alie_category_id: Int,
    val alie_category_name: String,
    val alie_parent_id: Int,
    val api_category_id: Int,
    val api_parent_id: Int,
    val category_name: String,
    val modificationDate: String,
    val provider: String,
    val url: String
)