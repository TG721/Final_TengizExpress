package com.tengizMKCorp.tengizExpress.data.remote

import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {
    @GET("bestSales/SortedByNewest?limit=20")
    suspend fun getBestSalesProductsSortByNewest(): Response<List<BestSalesSortedByNewestItem>>
    @GET("v2/categories")
    suspend fun getCategories(): Response<List<CategoryItem>>

}