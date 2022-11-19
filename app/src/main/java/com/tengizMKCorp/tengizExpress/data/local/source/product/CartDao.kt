package com.tengizMKCorp.tengizExpress.data.local.source.product

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    //cart
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProductToCart(product: CartModel)

    @Delete
    suspend fun deleteProductFromCart(product: CartModel)

    @Query("SELECT * FROM cart_table ORDER BY cartPosID DESC")
    fun readAllDataFromCartTable(): Flow<List<CartModel>>

}