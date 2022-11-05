package com.tengizMKCorp.tengizExpress.ui.element.model

import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.AvailableQuantity
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_id.ProductByID

class DetailedProductInfo(
    val id: String,
    val productName: String,
    val description: String,
    val originalPrice: Double,
    val discountRate: Int?,
    val hasDiscount: Boolean,
    val discountedPrice: Double?,
    val shopName: String,
    val quantity: Int,
)

fun convertProductByIDToDetailedProductInfo(productByID: ProductByID): DetailedProductInfo {
    var discountRate: Int? = null
    var discountedPrice: Double? = null
    if (productByID.metadata.priceModule.discountPromotion) {
        discountRate = productByID.metadata.priceModule.discount
        discountedPrice =
            productByID.metadata.priceModule.maxAmount.value * (productByID.metadata.priceModule.discount.toDouble() / 100)
    }

    return DetailedProductInfo(productByID.product_id,
        productByID.metadata.titleModule.product_title,
        productByID.metadata.titleModule.product_description,
        productByID.metadata.priceModule.maxAmount.value,
        discountRate,
        productByID.metadata.priceModule.discountPromotion,
        discountedPrice,
        productByID.metadata.storeModule.storeName,
        productByID.metadata.quantityModule.totalAvailQuantity)
}