package com.tengizMKCorp.tengizExpress.domain.repository

import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NameModel
import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category.ProductsByCategoryID
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_name.ProductByName
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    //retrofit
    suspend fun getBestSalesProductsSortByNewest(): Flow<ResponseState<List<BestSalesSortedByNewestItem>>>
    suspend fun getCategories(): Flow<ResponseState<List<CategoryItem>>>
    suspend fun getProductsByCategories(categoryID: Long): Flow<ResponseState<ProductsByCategoryID>>
    suspend fun getProductsByName(name: String): Flow<ResponseState<ProductByName>>

    //room
    suspend fun deleteProductFromLastViewedTable(product: NonDetailedProductDataBaseModel)
    suspend fun readAllDataFromLastViewedTable(): Flow<List<NonDetailedProductDataBaseModel>>
    suspend fun addProductToLastViewedTable(product: NonDetailedProductDataBaseModel)
    suspend fun addToNameTable(name: NameModel)
    suspend fun readAllDataFromNameTable(): Flow<List<NameModel>>
    suspend fun deleteAllFromNameTable()
    suspend fun deleteFromNameTable(name: NameModel)
    suspend fun addProductToCart(product: CartModel)
    suspend fun deleteProductFromCart(product: CartModel)
    suspend fun readAllDataFromCartTable(): Flow<List<CartModel>>


}