package com.xiayiye.takeout.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amap.api.services.core.PoiItem
import com.xiayiye.takeout.R
import java.util.*

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
 * 创建时间：2020/3/12 15:30
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.adapter
 * 文件说明：
 */
class AroundRvAdapter(private val activity: Activity, private val poiItem: ArrayList<PoiItem>) :
    RecyclerView.Adapter<AroundRvAdapter.AroundViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AroundViewHolder {
        val aroundView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_around_address, parent, false
        )
        return AroundViewHolder(aroundView)
    }

    override fun getItemCount(): Int = poiItem.size

    override fun onBindViewHolder(holder: AroundViewHolder, position: Int) {
        holder.bindView(poiItem[position])
        holder.itemView.setOnClickListener {
            val intent = Intent()
            intent.putExtra("title", poiItem[position].title)
            intent.putExtra("snippet", poiItem[position].snippet)
            activity.setResult(999, intent)
            activity.finish()
        }
    }

    class AroundViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        private val tvAddress = itemView.findViewById<TextView>(R.id.tv_address)
        fun bindView(poiItem: PoiItem) {
            tvTitle.text = poiItem.title
            tvAddress.text = poiItem.snippet
        }
    }
}