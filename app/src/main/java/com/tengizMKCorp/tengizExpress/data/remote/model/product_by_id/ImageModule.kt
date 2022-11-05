package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_id

data class ImageModule(
    val id: Int,
    val imagePathList: List<String>,
    val name: String,
    val summImagePathList: List<String>
)