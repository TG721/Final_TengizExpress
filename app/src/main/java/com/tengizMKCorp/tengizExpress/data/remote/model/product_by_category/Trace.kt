package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category

data class Trace(
    val click: Click,
    val custom: Custom,
    val detailPage: DetailPage,
    val exposure: Exposure,
    val pdpParams: PdpParams,
    val utLogMap: UtLogMap
)