package com.xiayiye.takeout.model.beans

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable


data class User(
    val code: String,
    val `data`: Data
) : Serializable

@DatabaseTable(tableName = "t_user")
data class Data(
    @DatabaseField(columnName = "balance") val balance: Double,
    @DatabaseField(columnName = "discount") val discount: Int,
    @DatabaseField(id = true) val id: Int,
    @DatabaseField(columnName = "integral") val integral: Int,
    @DatabaseField(columnName = "name") val name: String,
    @DatabaseField(columnName = "phone") var phone: String
) : Serializable
/*
{
  "code": "0",
  "data": {
    "balance": 0.0,
    "discount": 0,
    "id": 101,
    "integral": 0,
    "name": "新用户",
    "phone": "13512345678"
  }
}
 */