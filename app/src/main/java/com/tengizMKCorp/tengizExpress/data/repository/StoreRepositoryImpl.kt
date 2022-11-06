package com.tengizMKCorp.tengizExpress.data.repository

import com.tengizMKCorp.tengizExpress.data.local.source.product.CartModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NameModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.NonDetailedProductDataBaseModel
import com.tengizMKCorp.tengizExpress.data.local.source.product.ProductDao
import com.tengizMKCorp.tengizExpress.data.remote.StoreApi
import com.tengizMKCorp.tengizExpress.data.remote.model.best_sales_sorted_by_newest.BestSalesSortedByNewestItem
import com.tengizMKCorp.tengizExpress.data.remote.model.category.CategoryItem
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_category.ProductsByCategoryID
import com.tengizMKCorp.tengizExpress.data.remote.model.product_by_name.ProductByName
import com.tengizMKCorp.tengizExpress.domain.repository.StoreRepository
import com.tengizMKCorp.tengizExpress.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val api: StoreApi, private val productDao: ProductDao) : StoreRepository {
    //retrofit
    override suspend fun getBestSalesProductsSortByNewest(): Flow<ResponseState<List<BestSalesSortedByNewestItem>>> = flow{
        try {
            val response: Response<List<BestSalesSortedByNewestItem>> = api.getBestSalesProductsSortByNewest()
            val body: List<BestSalesSortedByNewestItem>? = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun getCategories(): Flow<ResponseState<List<CategoryItem>>> = flow {
        try {
            val response: Response<List<CategoryItem>> = api.getCategories()
            val body: List<CategoryItem>? = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun getProductsByCategories(categoryID: Int): Flow<ResponseState<ProductsByCategoryID>> = flow {
        try {
            val response: Response<ProductsByCategoryID> = api.getProductsByCategories(id = categoryID)
            val body: ProductsByCategoryID? = response.body()
            if (response.isSuccessful && body != null) {
                emit(ResponseState.Success(body))
            } else {
                emit(ResponseState.Error(response.errorBody()?.string()))
            }
        } catch (e: Exception){
            emit(ResponseState.Error(e.message.toString()))
        }
    }

    override suspend fun getProductsByName(productName: String): Flow<ResponseState<ProductByName>> = flow {
            try {
                val response: Response<ProductByName> = api.getProductsByName(name = productName)
                val body: ProductByName? = response.body()
                if (response.isSuccessful && body != null) {
                    emit(ResponseState.Success(body))
                } else {
                    emit(ResponseState.Error(response.errorBody()?.string()))
                }
            } catch (e: Exception){
                emit(ResponseState.Error(e.message.toString()))
            }
        }


    //room
    override suspend fun deleteProductFromLastViewedTable(product: NonDetailedProductDataBaseModel) {
        productDao.deleteFromLastViewedTable(product)
    }

    override suspend fun readAllDataFromLastViewedTable(): Flow<List<NonDetailedProductDataBaseModel>> {
       return productDao.readAllDataFromLastViewedTable()
    }

    override suspend fun addProductToLastViewedTable(product: NonDetailedProductDataBaseModel){
        productDao.addProductToLastViewedTable(product)
    }

    override suspend fun addToNameTable(name: NameModel) {
        productDao.addToNameTable(name)
    }

    override suspend fun readAllDataFromNameTable(): Flow<List<NameModel>> {
      return productDao.readAllDataFromNameTable()
    }

    override suspend fun deleteAllFromNameTable() {
        productDao.deleteAllFromNameTable()
    }

    override suspend fun deleteFromNameTable(name: NameModel) {
        productDao.deleteFromNameTable(name)
    }

    override suspend fun addProductToCart(product: CartModel) {
        productDao.addProductToCart(product)
    }

    override suspend fun deleteProductFromCart(product: CartModel) {
        productDao.deleteProductFromCart(product)
    }

    override suspend fun readAllDataFromCartTable(): Flow<List<CartModel>> {
        return productDao.readAllDataFromCartTable()
    }
}