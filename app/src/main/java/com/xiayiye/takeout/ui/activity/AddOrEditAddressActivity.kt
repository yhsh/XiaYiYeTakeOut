package com.xiayiye.takeout.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.ReceiptAddressBean
import com.xiayiye.takeout.model.dao.AddressDao
import com.xiayiye.takeout.utils.SMSUtil
import com.xiayiye.takeout.utils.SimpleTextChangeListener
import kotlinx.android.synthetic.main.activity_add_edit_receipt_address.*
import org.jetbrains.anko.toast

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
 * 创建时间：2020/3/10 21:38
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.activity
 * 文件说明：增加和修改地址页面
 */
class AddOrEditAddressActivity : AppCompatActivity() {
    private val selectTittle = arrayOf("家", "学校", "公司", "其它")
    private val tittleColor = arrayOf("#778899", "#ff3399", "#ff9933", "#33ff99")
    private lateinit var addressDao: AddressDao
    //是否是更新和删除地址的标识1：新增 2：更新 3：删除
    private var isUpdateOrDelete = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_receipt_address)
        processIntent()
        addressDao = AddressDao(this)
        initListener()
    }

    private lateinit var addressBean: ReceiptAddressBean
    private fun processIntent() {
        if (intent.hasExtra("address")) {
            ib_delete.visibility = View.VISIBLE
            ib_delete.setOnClickListener { clickOk(3) }
            isUpdateOrDelete = 2
            addressBean = intent.getSerializableExtra("address") as ReceiptAddressBean
            tv_title.text = "修改地址"
            et_name.setText(addressBean.userName)
            if (addressBean.sex.equals("先生")) {
                rb_man.isChecked = true
                rb_women.isChecked = false
            } else {
                rb_man.isChecked = false
                rb_women.isChecked = true
            }
            et_phone.setText(addressBean.phone)
            et_phone_other.setText(addressBean.phoneOther)
            et_receipt_address.setText(addressBean.address)
            et_detail_address.setText(addressBean.detailAddress)
            tv_label.text = addressBean.label
            tv_label.setTextColor(Color.WHITE)
            for (i in 0 until selectTittle.size) {
                if (selectTittle[i].equals(addressBean.label)) {
                    tv_label.setBackgroundColor(Color.parseColor(tittleColor[i]))
                }
            }
        }
    }

    private fun initListener() {
        ib_back.setOnClickListener { finish() }
        ib_delete_phone.setOnClickListener {
            et_phone.setText("")
            ib_delete_phone.visibility = View.GONE
        }
        ib_delete_phone_other.setOnClickListener {
            et_phone_other.setText("")
            ib_delete_phone_other.visibility = View.GONE
        }
        ib_add_phone_other.setOnClickListener { rl_phone_other.visibility = View.VISIBLE }
        ib_select_label.setOnClickListener { selectLabel() }
        btn_ok.setOnClickListener { clickOk(isUpdateOrDelete) }
        et_phone.addTextChangedListener(object : SimpleTextChangeListener() {
            override fun afterTextChanged(p0: Editable?) {
                //显示删除按钮
                ib_delete_phone.visibility = View.VISIBLE
            }
        })
        et_phone_other.addTextChangedListener(object : SimpleTextChangeListener() {
            override fun afterTextChanged(p0: Editable?) {
                //显示删除按钮
                ib_delete_phone_other.visibility = View.VISIBLE
            }
        })
    }

    private fun clickOk(isUpdateOrDelete: Int) {
        val name = et_name.text.toString().trim()
        val sex: String
        val phone = et_phone.text.toString().trim()
        val phoneOther = et_phone_other.text.toString().trim()
        val address = et_receipt_address.text.toString().trim()
        val detailAddress = et_detail_address.text.toString().trim()
        val label = tv_label.text.toString().trim()
        if (rb_man.isChecked) {
            sex = "先生"
        } else {
            sex = "女士"
        }
        if (checkNull(name, address, phone, detailAddress)) {
            //有备用手机号
            if (rl_phone_other.visibility == View.VISIBLE) {
                val otherPhone = et_phone_other.text.toString().trim()
                if (otherPhone.isEmpty()) {
                    toast("其它手机号不能为空")
                    return
                }
                if (SMSUtil.judgePhoneNums(this, otherPhone)) {
                    addAddress(
                        name,
                        sex,
                        phone,
                        phoneOther,
                        address,
                        detailAddress,
                        label,
                        isUpdateOrDelete
                    )
                }
            } else {
                //没有备用手机号
                addAddress(
                    name,
                    sex,
                    phone,
                    phoneOther,
                    address,
                    detailAddress,
                    label,
                    isUpdateOrDelete
                )
            }
        }
    }

    private fun addAddress(
        name: String,
        sex: String,
        phone: String,
        phoneOther: String,
        address: String,
        detailAddress: String,
        label: String,
        isUpdateOrDelete: Int
    ) {
        if (isUpdateOrDelete == 1) {

            addressDao.addReceiptAddressBean(
                ReceiptAddressBean(
                    999,
                    name,
                    sex,
                    phone,
                    phoneOther,
                    address,
                    detailAddress,
                    label,
                    "38"
                )
            )
        } else if (isUpdateOrDelete == 2) {
            //修改地址数据,重新赋值
            addressBean.userName = name
            addressBean.sex = sex
            addressBean.phone = phone
            addressBean.phoneOther = phoneOther
            addressBean.address = address
            addressBean.detailAddress = detailAddress
            addressBean.label = label
            addressDao.updateReceiptAddressBean(addressBean)
        } else {
            //删除
            addressDao.deleteReceiptAddressBean(addressBean)
        }
        toast("新增地址成功了")
        startActivity(Intent(this, ReceiptAddressActivity::class.java))
        finish()
    }

    /**
     * 检查属性是否为空
     */
    private fun checkNull(
        name: String,
        address: String,
        phone: String,
        detailAddress: String
    ): Boolean {
        if (name.isEmpty() || address.isEmpty() || detailAddress.isEmpty()) {
            toast("地址姓名不能为空！")
            return false
        }
        //判断手机号
        if (phone.isEmpty()) {
            toast("手机号不能为空")
            return false
        }
        if (SMSUtil.judgePhoneNums(this, phone)) {
            return true
        }
        return false
    }

    private fun selectLabel() {
        val build = AlertDialog.Builder(this)
        build.setTitle("选择标签").setItems(selectTittle) { dialog, switch ->
            tv_label.text = selectTittle[switch]
            tv_label.setBackgroundColor(Color.parseColor(tittleColor[switch]))
            tv_label.setTextColor(Color.WHITE)
        }.show()
    }
}