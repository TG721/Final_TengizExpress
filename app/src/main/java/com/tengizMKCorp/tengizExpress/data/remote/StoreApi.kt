package com.tengizMKCorp.tengizExpress.data.remote

import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import retrofit2.Response
import retrofit2.http.GET

interface StoreApi {
    @GET("bestSales/SortedByNewest?limit=20")
    suspend fun getBestSalesProductsSortByNewest(): Response<List<BestSalesSortedByNewestItem>>

}