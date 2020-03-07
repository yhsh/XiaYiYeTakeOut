package com.xiayiye.takeout.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.OrderData
import com.xiayiye.takeout.utils.OrderChangeFunction
import org.json.JSONObject
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
 * 创建时间：2020/3/6 20:36
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.adapter
 * 文件说明：订单观察者
 */
class OrderRvAdapter(private val context: Context, private val orderList: List<OrderData>) :
    RecyclerView.Adapter<OrderRvAdapter.OrderViewHolder>(), Observer {
    override fun update(p0: Observable?, pushKeyAndValue: Any?) {
        //观察者,拿到订单最新状态
        //解析json数据
        val jsonObject = JSONObject(pushKeyAndValue as String)
        val orderId = jsonObject.optString("id")
        val orderType = jsonObject.optString("type")
        Log.e("打印推送TakeOutReceiver", "$orderId=$orderType")
        for (index in 0 until orderList.size) {
            val orderDataStatus = orderList.get(index)
            if (orderDataStatus.id == orderId) {
                //更新订单状态
                orderDataStatus.type = orderType
                //刷新全局adapter
                notifyDataSetChanged()
                //刷新单个订单状态改变的item
//                notifyItemChanged(index)
            }
        }
    }

    init {
        //添加绑定关系
        OrderChangeFunction.instance.addObserver(this)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_order_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.tvOrderItemSellerName.text = orderList.get(position).orderSeller.name
        holder.tvOrderItemTime.text = orderList.get(position).detail.time
        holder.tv_order_item_money.text = orderList.get(position).goodsInfo.get(0).newPrice
        holder.tvOrderItemType.text = getType(orderList.get(position).type)
        holder.tv_order_item_foods.text = orderList.get(position).goodsInfo[0].name
    }

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvOrderItemSellerName: TextView
        val tvOrderItemTime: TextView
        val tv_order_item_money: TextView
        val tvOrderItemType: TextView
        val tv_order_item_foods: TextView

        init {
            tvOrderItemSellerName = itemView.findViewById<TextView>(R.id.tv_order_item_seller_name)
            tvOrderItemTime = itemView.findViewById<TextView>(R.id.tv_order_item_time)
            tv_order_item_money = itemView.findViewById<TextView>(R.id.tv_order_item_money)
            tvOrderItemType = itemView.findViewById<TextView>(R.id.tv_order_item_type)
            tv_order_item_foods = itemView.findViewById<TextView>(R.id.tv_order_item_foods)
        }
    }

    private fun getType(type: String): String {
        return when (type) {
            "10" -> "订单已提交"
            "20" -> "订单已完成"
            "30" -> "订单已取消"
            "40" -> "订单正在配送"
            else -> {
                "订单错误"
            }
        }
    }
}