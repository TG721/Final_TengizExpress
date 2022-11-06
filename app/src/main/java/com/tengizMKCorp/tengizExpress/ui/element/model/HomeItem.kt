package com.tengizMKCorp.tengizExpress.ui.element.model

import com.tengizMKCorp.tengizExpress.R

data class HomeItem (
    val title: String,
    val image: Int
        )

val HomeItemList = mutableListOf<HomeItem>(HomeItem("Categories", R.drawable.ic_categories_icon),
    HomeItem("Search", R.drawable.big_search_icon),
    HomeItem("Coupons",R.drawable.ic_coupon_icon),
    HomeItem("Gifts", R.drawable.ic_gifts_icon)
)