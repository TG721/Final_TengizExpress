package com.tengizMKCorp.tengizExpress.data.local.source.product

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cart_table")
data class CartModel (
    @PrimaryKey val  id: String,
    @ColumnInfo(name = "discounted_price") val discountedPrice: Double,
    @ColumnInfo(name = "product_name") val productName: String,
    @ColumnInfo(name = "product_picture") val productPicture: String
) : Parcelable