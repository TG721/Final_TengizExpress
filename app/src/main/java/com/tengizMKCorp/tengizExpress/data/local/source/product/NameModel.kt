package com.tengizMKCorp.tengizExpress.data.local.source.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "name_table")
class NameModel (
    @PrimaryKey val name: String
    )