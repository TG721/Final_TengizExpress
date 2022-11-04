package com.tengizMKCorp.tengizExpress.data.local.source

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NonDetailedProductDataBaseModel::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

}