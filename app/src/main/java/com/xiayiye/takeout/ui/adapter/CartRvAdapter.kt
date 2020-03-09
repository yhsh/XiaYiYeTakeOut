package com.xiayiye.takeout.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.Goods
import com.xiayiye.takeout.ui.activity.BusinessActivity
import com.xiayiye.takeout.ui.fragment.GoodsFragment
import com.xiayiye.takeout.utils.PriceFormater

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
 * 创建时间：2020/3/9 16:03
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.adapter
 * 文件说明：点击购物车展示购物车列表的adapter
 */
class CartRvAdapter(
    private val context: BusinessActivity,
    private val cartList: ArrayList<Goods>
) :
    RecyclerView.Adapter<CartRvAdapter.CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_cart,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = cartList.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bindView(cartList[position], context, this@CartRvAdapter, cartList)
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        private val tvTypeAllPrice = itemView.findViewById<TextView>(R.id.tv_type_all_price)
        private val ibAdd = itemView.findViewById<ImageButton>(R.id.ib_add)
        private val ibMinus = itemView.findViewById<ImageButton>(R.id.ib_minus)
        private val tvCount = itemView.findViewById<TextView>(R.id.tv_count)
        fun bindView(
            goods: Goods,
            context: BusinessActivity,
            cartRvAdapter: CartRvAdapter,
            cartList: ArrayList<Goods>
        ) {
            tvName.text = goods.from
            tvTypeAllPrice.text = PriceFormater.format(goods.newPrice.toFloat() * goods.count)
            tvCount.text = goods.count.toString()
            //设置加号和减号的点击事件
            ibMinus.setOnClickListener {
                doAddAndMinusOperation(
                    false,
                    goods,
                    context,
                    cartRvAdapter, cartList
                )
            }
            ibAdd.setOnClickListener {
                doAddAndMinusOperation(
                    true,
                    goods,
                    context,
                    cartRvAdapter,
                    cartList
                )
            }
        }

        /**
         * 加减按钮的操作
         */
        private fun doAddAndMinusOperation(
            add: Boolean,
            goods: Goods,
            context: BusinessActivity,
            cartRvAdapter: CartRvAdapter,
            cartList: ArrayList<Goods>
        ) {
            //改变数量,刷新UI
            var changeCount = goods.count
            if (add) {
                changeCount++
                if (changeCount == 1) {
                    ibMinus.visibility = View.VISIBLE
                }
            } else {
                if (changeCount >= 1) {
                    changeCount--
                }
                if (changeCount == 0) {
                    //删除购物车中数量为 0 的商品
                    cartList.remove(goods)
                }
                if (cartList.size == 0) {
                    //隐藏弹出的商品列表
                    context.showOrHideCar()
                }
            }
            val goodsFragment = context.list[0] as GoodsFragment
            goods.count = changeCount
            //刷新UI
            cartRvAdapter.notifyDataSetChanged()
            //刷新右侧商品列表数量
            goodsFragment.goodsAdapter.notifyDataSetChanged()

            //刷新左侧分类数量
            processRedNotCount(goodsFragment, goods, add)

            //更新购物车数量和价格
            context.updateCarUi()
        }

        /**
         * 刷新左边数量的方法
         */
        private fun processRedNotCount(
            goodsFragment: GoodsFragment,
            goods: Goods,
            add: Boolean
        ) {
            val typePosition =
                goodsFragment.goodsFragmentPresenter.getTypePositionByTypeId(goods.typeId)
            val goodTypeInfo = goodsFragment.goodsFragmentPresenter.goodData[typePosition]
            var redPointCount = goodTypeInfo.redPointCount
            if (add) {
                redPointCount++
            } else {
                redPointCount--
            }
            goodTypeInfo.redPointCount = redPointCount
            //刷新左侧列表
            goodsFragment.goodTypeRvAdapter.notifyDataSetChanged()
        }
    }
}