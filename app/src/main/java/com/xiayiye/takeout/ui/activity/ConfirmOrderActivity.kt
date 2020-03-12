package com.xiayiye.takeout.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.ReceiptAddressBean
import kotlinx.android.synthetic.main.activity_confirm_order.*

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
 * 创建时间：2020/3/10 20:50
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.activity
 * 文件说明：确认提交订单页面
 */
class ConfirmOrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)
        initListener()
    }

    private fun initListener() {
        rl_location.setOnClickListener {
            startActivityForResult(
                Intent(
                    this,
                    ReceiptAddressActivity::class.java
                ), 777
            )
        }
        ib_back.setOnClickListener { finish() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == 666) {
            data?.let {
                val selectAddress = it.getSerializableExtra("selectAddress") as ReceiptAddressBean
                tv_address.text =
                    StringBuffer(selectAddress.address).append(selectAddress.detailAddress)
                tv_name.text = selectAddress.userName
                tv_sex.text = selectAddress.sex
                val phone = selectAddress.phone
                val phoneOther = selectAddress.phoneOther
                if (phoneOther.isNotEmpty()) {
                    tv_phone.text = StringBuffer(phone).append(",").append(phoneOther)
                } else {
                    tv_phone.text = phone
                }
            }
        }
    }
}