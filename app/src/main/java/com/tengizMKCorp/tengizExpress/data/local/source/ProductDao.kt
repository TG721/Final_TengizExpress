package com.tengizMKCorp.tengizExpress.data.local.source

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM last_viewed_table")
    fun readAllDataFromLastViewedTable(): Flow<List<NonDetailedProductDataBaseModel>>

    @Delete
    suspend fun deleteFromLastViewedTable(product: NonDetailedProductDataBaseModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProductToLastViewedTable(product: NonDetailedProductDataBaseModel)
}