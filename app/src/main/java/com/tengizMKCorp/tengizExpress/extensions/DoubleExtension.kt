package com.tengizMKCorp.tengizExpress.extensions

fun Double.formatDecimal(numberOfDecimals: Int = 2): Double = "%.${numberOfDecimals}f".format(this).toDouble()