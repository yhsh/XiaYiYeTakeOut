package com.xiayiye.takeout.model.beans

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable


data class User(
    val code: String,
    val `data`: Data
) : Serializable

@DatabaseTable(tableName = "t_user")
class Data() : Serializable {
    @DatabaseField(columnName = "balance")
    var balance: Double = 0.0
    @DatabaseField(columnName = "discount")
    var discount: Int = 0
    @DatabaseField(id = true)
    var id: Int = 0
    @DatabaseField(columnName = "integral")
    var integral: Int = 0
    @DatabaseField(columnName = "name")
    var name: String = ""
    @DatabaseField(columnName = "phone")
    var phone: String = ""

    constructor(
        balance: Double,
        discount: Int,
        id: Int,
        integral: Int,
        name: String,
        phone: String
    ) : this() {
        this.balance = balance
        this.discount = discount
        this.id = id
        this.integral = integral
        this.name = name
        this.phone = phone
    }
}
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