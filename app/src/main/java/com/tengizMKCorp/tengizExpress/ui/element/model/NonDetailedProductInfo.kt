package com.tengizMKCorp.tengizExpress.ui.element.model

import android.os.Parcelable
import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category.Doc
import com.tengizMKCorp.tengizExpress.extensions.formatDecimal
import kotlinx.parcelize.Parcelize

@Parcelize
class NonDetailedProductInfo (
    val id: String,
    val originalPrice: Double,
    val discountedPrice: Double,
    val discountPercentage: Int,
    val productName: String,
    val productPicture: String
    ) : Parcelable

fun convertBestSalesSortedByNestToNonDetailedProductInfo(obj: BestSalesSortedByNewestItem): NonDetailedProductInfo{
    return NonDetailedProductInfo(obj.product_id.toString(),obj.original_price,obj.sale_price, obj.discount_rate,obj.product_title, obj.product_main_image_url)
}

fun convertProductByCategoryIDtoNonDetailedProductInfo(obj: Doc): NonDetailedProductInfo{
    val originalPrice = (obj.app_sale_price/(obj.discount_rate.toDouble()/100)).formatDecimal()
    return NonDetailedProductInfo(obj.product_id,originalPrice,obj.app_sale_price, obj.discount_rate, obj.product_title, obj.product_main_image_url)
}
