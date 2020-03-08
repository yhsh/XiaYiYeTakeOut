package com.xiayiye.takeout.ui.adapter

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.GoodData
import com.xiayiye.takeout.model.beans.Goods
import com.xiayiye.takeout.ui.fragment.GoodsFragment
import kotlinx.android.synthetic.main.fragment_goods.*

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
 * 创建时间：2020/3/8 15:24
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.adapter
 * 文件说明：
 */
class GoodTypeRvAdapter(
    private val goodData: List<GoodData>,
    private val goodFragment: GoodsFragment
) :
    RecyclerView.Adapter<GoodTypeRvAdapter.GoodTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodTypeViewHolder {
        val itemType = View.inflate(parent.context, R.layout.item_type, null)
        return GoodTypeViewHolder(itemType)
    }

    override fun getItemCount(): Int = goodData.size

    override fun onBindViewHolder(holder: GoodTypeViewHolder, position: Int) {
        holder.bindView(goodData[position], position)
    }

    var selectPosition = 0

    inner class GoodTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mPosition = 0
        lateinit var goodTypeInfo: GoodData

        init {
            itemView.setOnClickListener {
                selectPosition = mPosition
                //刷新当前页面
                notifyDataSetChanged()
                //点击分类条目后，跳转到点击种类的子条目的第一个位置
                val clickPosition =
                    goodFragment.goodsFragmentPresenter.getGoodPositionByTypeId(goodTypeInfo.id)
                goodFragment.slhlv.setSelection(clickPosition)
            }
        }

        private val tvGoodType = itemView.findViewById<TextView>(R.id.type)
        fun bindView(goodData: GoodData, position: Int) {
            mPosition = position
            goodTypeInfo = goodData
            if (position == selectPosition) {
                //选中了item设置背景为白色
                itemView.setBackgroundColor(Color.WHITE)
                tvGoodType.typeface = Typeface.DEFAULT_BOLD
                tvGoodType.setTextColor(Color.BLACK)
            } else {
                //未选中
                itemView.setBackgroundColor(Color.parseColor("#b9dedcdc"))
                tvGoodType.typeface = Typeface.DEFAULT
                tvGoodType.setTextColor(Color.GRAY)
            }
            tvGoodType.text = goodData.name
        }
    }
}
