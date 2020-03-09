package com.xiayiye.takeout.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.Goods
import com.xiayiye.takeout.ui.adapter.BusinessAdapter
import com.xiayiye.takeout.ui.fragment.CommentsFragment
import com.xiayiye.takeout.ui.fragment.GoodsFragment
import com.xiayiye.takeout.ui.fragment.SellerFragment
import com.xiayiye.takeout.utils.PriceFormater
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
    private val list = listOf<Fragment>(GoodsFragment(), SellerFragment(), CommentsFragment())
    private val listTitle = listOf<String>("商品", "商家", "评论")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_business)
        vp.adapter = BusinessAdapter(list, listTitle, supportFragmentManager)
        tabs.setupWithViewPager(vp)
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
        val cartList: ArrayList<Goods> = goodsFragment.goodsFragmentPresenter.getCarList()
        for (i in 0 until cartList.size) {
            count += cartList[i].count
            val oneGoodPrice = cartList[i].newPrice.toFloat() * cartList[i].count
            totalPrice += oneGoodPrice
        }
        if (count > 0) {
            //显示
            tvCountPrice.visibility = View.VISIBLE
            tvSelectNum.visibility = View.VISIBLE
            tvCountPrice.text = PriceFormater.format(totalPrice)
            tvSelectNum.text = count.toString()
        } else {
            //隐藏
            tvCountPrice.visibility = View.GONE
            tvSelectNum.visibility = View.GONE
        }
    }
}