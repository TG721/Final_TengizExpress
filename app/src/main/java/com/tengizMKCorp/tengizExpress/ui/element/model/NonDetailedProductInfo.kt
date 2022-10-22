package com.tengizMKCorp.tengizExpress.ui.element.model

import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem

class NonDetailedProductInfo (
    val id: String,
    val originalPrice: Double,
    val discountedPrice: Double,
    val discountPercentage: Int,
    val productName: String,
    val productPicture: String
    )

fun convertBestSalesSortedByNestToNonDetailedProductInfo(obj: BestSalesSortedByNewestItem): NonDetailedProductInfo{
    return NonDetailedProductInfo(obj._id,obj.original_price,obj.sale_price, obj.discount_rate,obj.product_title, obj.product_main_image_url)
}