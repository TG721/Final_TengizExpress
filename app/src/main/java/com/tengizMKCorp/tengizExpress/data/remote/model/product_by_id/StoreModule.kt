package com.tengizMKCorp.tengizExpress.data.remote.model.product_by_id

data class StoreModule(
    val companyId: Int,
    val countryCompleteName: String,
    val detailPageUrl: String,
    val feedbackMessageServer: String,
    val feedbackServer: String,
    val followed: Boolean,
    val followingNumber: Int,
    val hasStore: Boolean,
    val hasStoreHeader: Boolean,
    val hideCustomerService: Boolean,
    val id: Int,
    val name: String,
    val openTime: String,
    val openedYear: Int,
    val positiveNum: Int,
    val positiveRate: String,
    val productId: Long,
    val sellerAdminSeq: Long,
    val sessionId: String,
    val siteType: String,
    val storeName: String,
    val storeNum: Int,
    val storeURL: String,
    val topBrandDescURL: String,
    val topRatedSeller: Boolean
)