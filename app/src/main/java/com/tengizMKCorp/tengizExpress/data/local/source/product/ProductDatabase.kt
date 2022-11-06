package com.tengizMKCorp.tengizExpress.data.local.source.product

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NonDetailedProductDataBaseModel::class, NameModel::class, CartModel::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

}