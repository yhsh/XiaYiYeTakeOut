package com.xiayiye.takeout.ui.adapter

import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.Goods
import com.xiayiye.takeout.ui.activity.BusinessActivity
import com.xiayiye.takeout.ui.fragment.GoodsFragment
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
class GoodsAdapter(
    private val goodsFragment: GoodsFragment,
    private val goodList: ArrayList<Goods>
) :
    BaseAdapter(),
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
            goodViewHolder.tvCount = itemView.find<TextView>(R.id.tv_count)
        } else {
            goodViewHolder = (contentView.tag) as GoodViewHolder
            itemView = contentView
        }
        val itemTypeData = goodList.get(position)
        goodViewHolder.tvName.text = itemTypeData.name
        goodViewHolder.tvNewPrice.text = StringBuffer("￥").append(itemTypeData.newPrice)
        goodViewHolder.tvOldPrice.text = StringBuffer("￥").append(itemTypeData.oldPrice)
//        goodViewHolder.tvOldPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        goodViewHolder.tvOldPrice.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        //抗锯齿
        goodViewHolder.tvOldPrice.paint.isAntiAlias = true
        goodViewHolder.tvMonthSale.text =
            StringBuffer("月售").append(itemTypeData.monthSaleNum).append("份")
        goodViewHolder.tvForm.text = itemTypeData.from
        Picasso.with(parent?.context).load(itemTypeData.icon).into(goodViewHolder.ivIcon)
        if (itemTypeData.count > 0) {
            //设置数量
            goodViewHolder.tvCount.text = itemTypeData.count.toString()
            goodViewHolder.tvCount.visibility = View.VISIBLE
            goodViewHolder.btMinus.visibility = View.VISIBLE
        } else {
            goodViewHolder.tvCount.visibility = View.GONE
            goodViewHolder.btMinus.visibility = View.GONE
        }
        goodViewHolder.btMinus.setOnClickListener {
            var oldCount = goodList[position].count
            if (oldCount > 0) {
                //显示红点
                if (oldCount == 1) {
                    val animationSet = hideAnimation()
                    goodViewHolder.tvCount.startAnimation(animationSet)
                    goodViewHolder.btMinus.startAnimation(animationSet)
                }
                oldCount--
                //设置红点的数量
                processRedNotCount(false, position)
                setShopNumber(oldCount, goodViewHolder, position)
                //更新购物车数量和价格
                (goodsFragment.activity as BusinessActivity).updateCarUi()
            }
        }
        goodViewHolder.btAdd.setOnClickListener {
            var oldCount = itemTypeData.count
            if (oldCount == 0) {
                val animationSet = showAnimation()
                goodViewHolder.tvCount.startAnimation(animationSet)
                goodViewHolder.btMinus.startAnimation(animationSet)
            }
            oldCount++
            //设置红点的数量
            processRedNotCount(true, position)
            setShopNumber(oldCount, goodViewHolder, position)
            //点击加号产生抛物线的效果
            val ib = ImageButton(parent?.context)
            ib.setBackgroundResource(R.mipmap.button_add)
            val outLocation = IntArray(2)
            goodViewHolder.btAdd.getLocationInWindow(outLocation)
            //设置复制的加号的绝对值坐标
            ib.x = outLocation[0].toFloat()
            ib.y = outLocation[1].toFloat()
            Log.e("打印X：", outLocation[0].toString() + "Y 轴：" + outLocation[1].toString())
            ((goodsFragment.activity) as BusinessActivity).addImageButton(
                ib,
                goodViewHolder.btAdd.width,
                goodViewHolder.btAdd.height
            )
            val destLocation: IntArray =
                ((goodsFragment.activity) as BusinessActivity).getCarLocation()
            //执行抛物线动画
            val animationSet = setAnimation(ib, outLocation, destLocation)
            ib.startAnimation(animationSet)
            //更新购物车数量和价格
            (goodsFragment.activity as BusinessActivity).updateCarUi()
        }
        return itemView
    }

    /**
     * 抛物线的动画
     */
    private fun setAnimation(
        ib: ImageButton,
        outLocation: IntArray,
        destLocation: IntArray
    ): AnimationSet {
        val anim = AnimationSet(false)
        val translateX = TranslateAnimation(
            Animation.ABSOLUTE,
            0f,
            Animation.ABSOLUTE,
            destLocation[0].toFloat() - outLocation[0],
            Animation.ABSOLUTE,
            0f,
            Animation.ABSOLUTE,
            0f
        )
        val translateY = TranslateAnimation(
            Animation.ABSOLUTE,
            0f,
            Animation.ABSOLUTE,
            0f,
            Animation.ABSOLUTE,
            0f,
            Animation.ABSOLUTE,
            destLocation[1].toFloat() - outLocation[1]
        )
        //设置Y 轴方向加速效果
        translateY.interpolator = AccelerateInterpolator()
        anim.addAnimation(translateX)
        anim.addAnimation(translateY)
        anim.duration = 1500
        //设置动画监听
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                //动画结束,移除加号按钮,下面是解决报错的方法将移除操作放入子线程
                //java.lang.NullPointerException: Attempt to read from field 'int android.view.View.mViewFlags' on a null object reference
                Handler().post { (ib.parent as ViewGroup).removeView(ib) }
            }

            override fun onAnimationStart(p0: Animation?) {

            }
        })
        return anim
    }

    /**
     * 设置小红点数量的方法
     */
    private fun processRedNotCount(isAdd: Boolean, position: Int) {
        val typePosition =
            goodsFragment.goodsFragmentPresenter.getTypePositionByTypeId(goodList[position].typeId)
        val goodTypeInfo = goodsFragment.goodsFragmentPresenter.goodData[typePosition]
        var redPointCount = goodTypeInfo.redPointCount
        if (isAdd) {
            redPointCount++
        } else {
            redPointCount--
        }
        goodTypeInfo.redPointCount = redPointCount
        //刷新左侧列表
        goodsFragment.goodTypeRvAdapter.notifyDataSetChanged()
    }

    private fun showAnimation(): AnimationSet {
        val animationSet = AnimationSet(false)
        val alpha = AlphaAnimation(0.0f, 1.0f)
        val rotate = RotateAnimation(
            0.0f,
            720f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        val translate = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            2.0f,
            Animation.RELATIVE_TO_SELF,
            0.0f,
            Animation.RELATIVE_TO_SELF,
            0.0f,
            Animation.RELATIVE_TO_SELF,
            0.0f
        )
        animationSet.duration = 1000
        animationSet.addAnimation(rotate)
        animationSet.addAnimation(translate)
        animationSet.addAnimation(alpha)
        return animationSet
    }

    private fun hideAnimation(): AnimationSet {
        val animationSet = AnimationSet(false)
        val alpha = AlphaAnimation(1.0f, 0.0f)
        val rotate = RotateAnimation(
            720.0f,
            0.00f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        val translate = TranslateAnimation(
            Animation.RELATIVE_TO_SELF,
            0f,
            Animation.RELATIVE_TO_SELF,
            2.0f,
            Animation.RELATIVE_TO_SELF,
            0.0f,
            Animation.RELATIVE_TO_SELF,
            0.0f
        )
        animationSet.duration = 1000
        animationSet.addAnimation(rotate)
        animationSet.addAnimation(translate)
        animationSet.addAnimation(alpha)
        return animationSet
    }

    private fun setShopNumber(newCount: Int, goodViewHolder: GoodViewHolder, position: Int) {
        //将数量设置给文本
        goodViewHolder.tvCount.text = newCount.toString()
        goodList.get(position).count = newCount
        notifyDataSetChanged()
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
        lateinit var tvCount: TextView
    }
}