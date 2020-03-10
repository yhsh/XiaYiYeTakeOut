package com.xiayiye.takeout.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.Goods
import com.xiayiye.takeout.model.beans.Seller
import com.xiayiye.takeout.ui.adapter.BusinessAdapter
import com.xiayiye.takeout.ui.adapter.CartRvAdapter
import com.xiayiye.takeout.ui.fragment.CommentsFragment
import com.xiayiye.takeout.ui.fragment.GoodsFragment
import com.xiayiye.takeout.ui.fragment.SellerFragment
import com.xiayiye.takeout.utils.PriceFormater
import com.xiayiye.takeout.utils.TakeOutApplication
import kotlinx.android.synthetic.main.activity_business.*

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
 * 创建时间：2020/3/7 17:15
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.activity
 * 文件说明：商家店铺页面
 */
class BusinessActivity : AppCompatActivity() {
    val list = listOf<Fragment>(GoodsFragment(), SellerFragment(), CommentsFragment())
    private lateinit var bottomSheetView: View
    private var cartList: ArrayList<Goods>? = null
    private val listTitle = listOf<String>("商品", "商家", "评论")
    var hasSelectInfo: Boolean = false
    private lateinit var seller: Seller
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)
        processIntent()
        vp.adapter = BusinessAdapter(list, listTitle, supportFragmentManager)
        tabs.setupWithViewPager(vp)
        bottom.setOnClickListener { showOrHideCar() }
    }

    private fun processIntent() {
        if (intent.hasExtra("hasSelectInfo")) {
            hasSelectInfo = intent.getBooleanExtra("hasSelectInfo", false)
            seller = intent.getSerializableExtra("mSeller") as Seller
            tvSendPrice.text =
                StringBuffer("另需配送费").append(PriceFormater.format(seller.deliveryFee!!.toFloat()))
            tvDeliveryFee.text = PriceFormater.format(seller.sendPrice!!.toFloat())
        }
    }

    /**
     * 显示和隐藏购物车商品的方法
     */
    fun showOrHideCar() {
        bottomSheetView = LayoutInflater.from(this)
            .inflate(R.layout.cart_list, window.decorView as ViewGroup, false)
        //判断购物车是否显示
        if (bottomSheetLayout.isSheetShowing) {
            //隐藏购物车商品
            bottomSheetLayout.dismissSheet()
        } else {
            //购物车为空,不显示
            cartList?.let {
                if (it.size > 0) {
                    //显示购物车商品
                    bottomSheetLayout.showWithSheetView(bottomSheetView)
                    //展示购物车数据
                    val rvCart = bottomSheetView.findViewById<RecyclerView>(R.id.rvCart)
                    val tvClear = bottomSheetView.findViewById<TextView>(R.id.tvClear)
                    rvCart.layoutManager = LinearLayoutManager(this)
                    val cartRvAdapter = CartRvAdapter(this, it)
                    rvCart.adapter = cartRvAdapter
                    tvClear.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(p0: View?) {
                            //清空购物车
                            val dialog = AlertDialog.Builder(this@BusinessActivity)
                            dialog.setTitle("提示").setMessage("确认不吃了吗？")
                                .setPositiveButton("确认") { p0, p1 ->
                                    val goodsFragment = list[0] as GoodsFragment
                                    //清空数据集合
                                    goodsFragment.goodsFragmentPresenter.clearCart()
                                    //刷新购物车
                                    cartRvAdapter.notifyDataSetChanged()
                                    showOrHideCar()
                                    //刷新左边种类
                                    goodsFragment.goodsAdapter.notifyDataSetChanged()
                                    clearRedPoint()
                                    //刷新右边商品
                                    goodsFragment.goodTypeRvAdapter.notifyDataSetChanged()
                                    //更新下购物车
                                    updateCarUi()
                                    //清空所有缓存商品
                                    TakeOutApplication.sInstance.clearCacheSelectedInfo(seller.id.toInt())
                                }
                                .setNegativeButton("取消") { p0, p1 -> p0?.dismiss() }
                            dialog.show()
                        }
                    })
                }
            }
        }
    }

    /**
     * 清空购物车后联动清空左边种类小红点的方法
     */
    private fun clearRedPoint() {
        val goodTypeList = (list[0] as GoodsFragment).goodsFragmentPresenter.goodData
        for (i in 0 until goodTypeList.size) {
            goodTypeList[i].redPointCount = 0
        }
    }

    //添加加号按钮到activity上面
    fun addImageButton(ib: ImageButton, width: Int, height: Int) {
        fl_Container.addView(ib, width, height)
    }

    /**
     * 拿到购物车我图片在屏幕上的绝对位置
     */
    fun getCarLocation(): IntArray {
        val outLocation = IntArray(2)
        imgCart.getLocationInWindow(outLocation)
        return outLocation
    }

    fun updateCarUi() {
        var count = 0
        var totalPrice = 0.0f
        val goodsFragment: GoodsFragment = list[0] as GoodsFragment
        cartList = goodsFragment.goodsFragmentPresenter.getCarList()
        cartList?.let {
            for (i in 0 until it.size) {
                count += it[i].count
                val oneGoodPrice = it[i].newPrice.toFloat() * it[i].count
                totalPrice += oneGoodPrice
            }
        }
        if (count > 0) {
            //显示
            tvCountPrice.visibility = View.VISIBLE
            tvSelectNum.visibility = View.VISIBLE
            tvCountPrice.text = PriceFormater.format(totalPrice)
            tvSelectNum.text = count.toString()
            if (totalPrice >= seller.sendPrice!!.toFloat()) {
                tvSubmit.visibility = View.VISIBLE
                tvDeliveryFee.visibility = View.GONE
                //能提交再开始提交的点击事件
                tvSubmit.setOnClickListener {
                    startActivity(Intent(this, ConfirmOrderActivity::class.java))
                }
            } else {
                tvSubmit.visibility = View.GONE
                tvDeliveryFee.visibility = View.VISIBLE
            }
        } else {
            //隐藏
            tvCountPrice.text = "￥0"
            tvSelectNum.visibility = View.INVISIBLE
        }
    }
}