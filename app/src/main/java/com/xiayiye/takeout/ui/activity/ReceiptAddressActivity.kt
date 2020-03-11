package com.xiayiye.takeout.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.ReceiptAddressBean
import com.xiayiye.takeout.model.dao.AddressDao
import com.xiayiye.takeout.ui.adapter.AddressRvAdapter
import kotlinx.android.synthetic.main.activity_address_list.*

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
 * 创建时间：2020/3/10 21:33
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.activity
 * 文件说明：收货地址列表
 */
class ReceiptAddressActivity : AppCompatActivity() {
    lateinit var addressDao: AddressDao
    private lateinit var queryAllAddress: List<ReceiptAddressBean>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_list)
        addressDao = AddressDao(this)
        queryAllAddress = addressDao.queryAllAddress()
        initView()
        initListener()
    }

    private fun initView() {
        rv_receipt_address.layoutManager = LinearLayoutManager(this)
        rv_receipt_address.adapter = AddressRvAdapter(queryAllAddress)
    }

    private fun initListener() {
        tv_add_address.setOnClickListener {
            startActivity(Intent(this, AddOrEditAddressActivity::class.java))
        }
        ib_back.setOnClickListener { finish() }
    }
}