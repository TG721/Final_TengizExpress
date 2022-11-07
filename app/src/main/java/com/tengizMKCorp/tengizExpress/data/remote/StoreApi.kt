package com.tengizMKCorp.tengizExpress.data.remote

import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category.ProductsByCategoryID
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_name.ProductByName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreApi {
    @GET("bestSales/SortedByNewest?limit=20")
    suspend fun getBestSalesProductsSortByNewest(): Response<List<BestSalesSortedByNewestItem>>
    @GET("v2/categories")
    suspend fun getCategories(): Response<List<CategoryItem>>
    @GET("category/{categoryID}/products")
    suspend fun getProductsByCategories(@Path("categoryID") id: Long):Response<ProductsByCategoryID>
    @GET("products/search")
    suspend fun getProductsByName(@Query("name") name:String) : Response<ProductByName>

}