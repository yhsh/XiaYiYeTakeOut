package com.xiayiye.takeout.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.Goods
import org.jetbrains.anko.find
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter

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
 * 创建时间：2020/3/8 16:17
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.adapter
 * 文件说明：
 */
class GoodsAdapter(private val goodList: ArrayList<Goods>) : BaseAdapter(),
    StickyListHeadersAdapter {
    override fun getView(position: Int, contentView: View?, parent: ViewGroup?): View {
        val goodViewHolder: GoodViewHolder
        val itemView: View
        if (contentView == null) {
            goodViewHolder = GoodViewHolder()
            itemView =
                LayoutInflater.from(parent?.context).inflate(R.layout.item_goods, parent, false)
            itemView.tag = goodViewHolder
            goodViewHolder.tvName = itemView.find<TextView>(R.id.tv_name)
            goodViewHolder.tvNewPrice = itemView.find<TextView>(R.id.tv_newprice)
            goodViewHolder.tvOldPrice = itemView.find<TextView>(R.id.tv_oldprice)
            goodViewHolder.tvMonthSale = itemView.find<TextView>(R.id.tv_month_sale)
            goodViewHolder.tvForm = itemView.find<TextView>(R.id.tv_form)
            goodViewHolder.ivIcon = itemView.find<ImageView>(R.id.iv_icon)
            goodViewHolder.btMinus = itemView.find<ImageButton>(R.id.bt_minus)
            goodViewHolder.btAdd = itemView.find<ImageButton>(R.id.bt_add)
        } else {
            goodViewHolder = (contentView.tag) as GoodViewHolder
            itemView = contentView
        }
        val itemTypeData = goodList.get(position)
        goodViewHolder.tvName.text = itemTypeData.name
        goodViewHolder.tvNewPrice.text = StringBuffer("￥").append(itemTypeData.newPrice)
        goodViewHolder.tvOldPrice.text = StringBuffer("￥").append(itemTypeData.oldPrice)
        goodViewHolder.tvMonthSale.text =
            StringBuffer("月售").append(itemTypeData.monthSaleNum).append("份")
        goodViewHolder.tvForm.text = itemTypeData.from
        Picasso.with(parent?.context).load(itemTypeData.icon).into(goodViewHolder.ivIcon)
        goodViewHolder.btMinus.setOnClickListener { }
        goodViewHolder.btAdd.setOnClickListener { }
        return itemView
    }

    override fun getItem(position: Int): Any = goodList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = goodList.size

    override fun getHeaderId(position: Int): Long {
        return goodList[position].typeId.toLong()
    }

    override fun getHeaderView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val goodInfo = goodList[position]
        val typeName = goodInfo.typeName
        val tv =
            LayoutInflater.from(parent?.context).inflate(
                R.layout.item_type_header,
                parent,
                false
            ) as TextView
        tv.text = typeName
        tv.setTextColor(Color.RED)
        return tv
    }

    class GoodViewHolder {
        lateinit var tvName: TextView
        lateinit var tvNewPrice: TextView
        lateinit var tvOldPrice: TextView
        lateinit var tvMonthSale: TextView
        lateinit var tvForm: TextView
        lateinit var ivIcon: ImageView
        lateinit var btMinus: ImageButton
        lateinit var btAdd: ImageButton
    }
}