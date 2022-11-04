package com.tengizMKCorp.tengizExpress.domain.repository

import com.tengizMKCorp.tengizExpress.data.local.source.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category.ProductsByCategoryID
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    //retrofit
    suspend fun getBestSalesProductsSortByNewest(): Flow<ResponseState<List<BestSalesSortedByNewestItem>>>
    suspend fun getCategories(): Flow<ResponseState<List<CategoryItem>>>
    suspend fun getProductsByCategories(categoryID: Int): Flow<ResponseState<ProductsByCategoryID>>
    //room
    suspend fun deleteProductFromLastViewedTable(product: NonDetailedProductDataBaseModel)

    suspend fun readAllDataFromLastViewedTable(): Flow<List<NonDetailedProductDataBaseModel>>

    suspend fun addProductToLastViewedTable(product: NonDetailedProductDataBaseModel)

}