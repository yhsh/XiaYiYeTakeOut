package com.xiayiye.takeout.model.beans

import java.io.Serializable


data class User(
    val code: String,
    val `data`: Data
) : Serializable

data class Data(
    val balance: Double,
    val discount: Int,
    val id: Int,
    val integral: Int,
    val name: String,
    var phone: String
) : Serializable