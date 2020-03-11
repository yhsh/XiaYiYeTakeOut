package com.xiayiye.takeout.ui.adapter

import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.ReceiptAddressBean
import com.xiayiye.takeout.ui.activity.AddOrEditAddressActivity
import org.jetbrains.anko.startActivity

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
 * 创建时间：2020/3/11 19:32
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.adapter
 * 文件说明：
 */
class AddressRvAdapter(val queryAllAddress: List<ReceiptAddressBean>) :
    RecyclerView.Adapter<AddressRvAdapter.AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val addressView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_receipt_address, parent, false
        )
        return AddressViewHolder(addressView)
    }

    override fun getItemCount(): Int = queryAllAddress.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.bindView(queryAllAddress[position])
    }

    class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private val tvSex = itemView.findViewById<TextView>(R.id.tv_sex)
        private val tvPhone = itemView.findViewById<TextView>(R.id.tv_phone)
        private val tvAddress = itemView.findViewById<TextView>(R.id.tv_address)
        private val tvLabel = itemView.findViewById<TextView>(R.id.tv_label)
        private val ivEdit = itemView.findViewById<ImageView>(R.id.iv_edit)
        fun bindView(queryAllAddress: ReceiptAddressBean) {
            tvName.text = queryAllAddress.userName
            tvSex.text = queryAllAddress.sex
            val phoneOther = queryAllAddress.phoneOther
            if (TextUtils.isEmpty(phoneOther)) {
                tvPhone.text = queryAllAddress.phone
            } else {
                tvPhone.text =
                    StringBuffer(queryAllAddress.phone).append(",").append(phoneOther)
            }
            tvAddress.text =
                StringBuffer(queryAllAddress.address).append(queryAllAddress.detailAddress)
            if (queryAllAddress.label.isNotEmpty()) {
                tvLabel.text = queryAllAddress.label
                tvLabel.setBackgroundColor(Color.parseColor("#ff9933"))
                tvLabel.setTextColor(Color.WHITE)
            } else {
                tvLabel.text = ""
                tvLabel.setBackgroundColor(Color.WHITE)
                tvLabel.setTextColor(Color.WHITE)
            }
            ivEdit.setOnClickListener {
                val intent = Intent(
                    itemView.context,
                    AddOrEditAddressActivity::class.java
                )
                intent.putExtra("address", queryAllAddress)
                itemView.context.startActivity(intent)
            }
        }
    }
}