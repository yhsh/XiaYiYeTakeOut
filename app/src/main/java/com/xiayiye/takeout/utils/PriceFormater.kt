package com.xiayiye.takeout.utils

import java.text.NumberFormat

/**
 * Created by lidongzhi on 16/10/14.
 */
object PriceFormater {

    fun format(countPrice: Float): String {
        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 2
        return format.format(countPrice.toDouble())
    }
}
