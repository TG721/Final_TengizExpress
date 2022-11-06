package com.tengizMKCorp.tengizExpress.data.local.source.product

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM last_viewed_table")
    fun readAllDataFromLastViewedTable(): Flow<List<NonDetailedProductDataBaseModel>>

    @Delete
    suspend fun deleteFromLastViewedTable(product: NonDetailedProductDataBaseModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToLastViewedTable(product: NonDetailedProductDataBaseModel)
    //name
    @Query("SELECT * FROM name_table")
    fun readAllDataFromNameTable(): Flow<List<NameModel>>

    @Delete
    suspend fun deleteFromNameTable(name: NameModel)

    //delete everything
    @Query("DELETE FROM name_table")
    suspend fun deleteAllFromNameTable()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToNameTable(name: NameModel)
}