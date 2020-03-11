package com.xiayiye.takeout.model.beans

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable

/*
 * Copyright (c) 2020, smuyyh@gmail.com All Rights Reserved.
 * #                                                   #
 * #                       _oo0oo_                     #
 * #                      o8888888o                    #
 * #                      88" . "88                    #
 * #                      (| -_- |)                    #
 * #                      0\  =  /0                    #
 * #                    ___/`---'\___                  #
 * #                  .' \\|     |# '.                 #
 * #                 / \\|||  :  |||# \                #
 * #                / _||||| -:- |||||- \              #
 * #               |   | \\\  -  #/ |   |              #
 * #               | \_|  ''\---/''  |_/ |             #
 * #               \  .-\__  '-'  ___/-. /             #
 * #             ___'. .'  /--.--\  `. .'___           #
 * #          ."" '<  `.___\_<|>_/___.' >' "".         #
 * #         | | :  `- \`.;`\ _ /`;.`/ - ` : | |       #
 * #         \  \ `_.   \_ __\ /__ _/   .-` /  /       #
 * #     =====`-.____`.___ \_____/___.-`___.-'=====    #
 * #                       `=---='                     #
 * #     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~   #
 * #                                                   #
 * #               佛祖保佑         永无BUG            #
 * #                                                   #
 */

/**
 * @author 下一页5（轻飞扬）
 * 创建时间：2020/3/11 14:12
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.model.beans
 * 文件说明：
 */
@DatabaseTable(tableName = "t_address")
class ReceiptAddressBean() : Serializable {
    @DatabaseField(generatedId = true)
    var id: Int = 0
    @DatabaseField(columnName = "userName")
    var userName: String = ""
    @DatabaseField(columnName = "sex")
    var sex: String = "先生"
    @DatabaseField(columnName = "phone")
    var phone: String = ""
    @DatabaseField(columnName = "phoneOther")
    var phoneOther: String = ""
    @DatabaseField(columnName = "address")
    var address: String = ""
    @DatabaseField(columnName = "detailAddress")
    var detailAddress: String = ""
    @DatabaseField(columnName = "label")
    var label: String = ""
    @DatabaseField(columnName = "userId")
    var userId: String = "38"

    constructor(
        id: Int,
        userName: String,
        sex: String,
        phone: String,
        phoneOther: String,
        address: String,
        detailAddress: String,
        label: String,
        userId: String
    ) : this() {
        this.id = id
        this.userName = userName
        this.sex = sex
        this.phone = phone
        this.phoneOther = phoneOther
        this.address = address
        this.detailAddress = detailAddress
        this.label = label
        this.userId = userId
    }
}
