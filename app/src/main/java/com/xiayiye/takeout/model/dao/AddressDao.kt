package com.xiayiye.takeout.model.dao

import android.content.Context
import com.j256.ormlite.dao.Dao
import com.xiayiye.takeout.model.beans.ReceiptAddressBean

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
 * 创建时间：2020/3/11 14:33
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.model.dao
 * 文件说明：
 */
class AddressDao(val context: Context) {
    private var addressDao: Dao<ReceiptAddressBean, Int>

    init {
        val openHelper = TakeOutOpenHelper(context)
        addressDao =
            openHelper.getDao(ReceiptAddressBean::class.java)
    }

    /**
     * 增加地址
     */
    fun addReceiptAddressBean(bean: ReceiptAddressBean) {
        try {
            addressDao.create(bean)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 删除地址
     */
    fun deleteReceiptAddressBean(bean: ReceiptAddressBean) {
        try {
            addressDao.delete(bean)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 修改地址
     */
    fun updateReceiptAddressBean(bean: ReceiptAddressBean) {
        try {
            addressDao.update(bean)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 查找地址
     */
    fun queryAllAddress(): List<ReceiptAddressBean> {
        try {
            return addressDao.queryForAll()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ArrayList<ReceiptAddressBean>()
    }
}