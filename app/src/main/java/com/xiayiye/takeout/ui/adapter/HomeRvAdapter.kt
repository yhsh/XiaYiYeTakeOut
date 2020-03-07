package com.xiayiye.takeout.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.TextSliderView
import com.squareup.picasso.Picasso
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.Seller
import com.xiayiye.takeout.ui.activity.BusinessActivity
import org.jetbrains.anko.find

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
 * 创建时间：2020/3/4 21:12
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.adapter
 * 文件说明：
 */
class HomeRvAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        val TYPE_TITLE = 0
        val TYPE_SELLER = 1
    }

    private var mDatas: ArrayList<Seller> = ArrayList()
    fun setData(data: ArrayList<Seller>) {
        mDatas = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_TITLE
        } else {
            return TYPE_SELLER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_TITLE) {
            return TittleViewHolder(View.inflate(context, R.layout.item_title, null))
        } else {
            return SellerViewHolder(View.inflate(context, R.layout.item_seller, null))
        }
    }

    override fun getItemCount(): Int {
        return if (mDatas.size > 0) {
            mDatas.size + 1
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_TITLE) {
            (holder as TittleViewHolder).bindData("我是大哥…………………………")
        } else {
            (holder as SellerViewHolder).bindData(mDatas[position - 1])
        }
    }

    val urlMap = HashMap<String, String>()

    inner class TittleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sliderLayout: SliderLayout = itemView.findViewById(R.id.slider)

        fun bindData(data: String) {
            if (urlMap.size == 0) {
                urlMap["全场四折"] =
                    "http://img0.imgtn.bdimg.com/it/u=2128568984,3003092150&fm=26&gp=0.jpg";
                urlMap["远离毒品"] =
                    "http://img1.imgtn.bdimg.com/it/u=1673877324,281791951&fm=26&gp=0.jpg";
                urlMap["只做最好"] =
                    "http://img5.imgtn.bdimg.com/it/u=1339193068,1490232691&fm=26&gp=0.jpg";
                urlMap["美丽团购节"] =
                    "http://img3.imgtn.bdimg.com/it/u=3230946850,1678356471&fm=26&gp=0.jpg";
                for ((key, value) in urlMap) {
                    val textSliderView = TextSliderView(context)
                    textSliderView.description(key).image(value)
                    sliderLayout.addSlider(textSliderView)
                }
            }
        }
    }

    inner class SellerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTittle: TextView
        private val tvHomeSale: TextView
        private val tvHomeSendPrice: TextView
        private val tvHomeDistance: TextView
        private val ratingBar: RatingBar
        private val sellerLogo: ImageView

        init {
            tvTittle = itemView.findViewById(R.id.tv_title)
            tvHomeSale = itemView.findViewById(R.id.tv_home_sale)
            tvHomeSendPrice = itemView.findViewById(R.id.tv_home_send_price)
            tvHomeDistance = itemView.findViewById(R.id.tv_home_distance)
            ratingBar = itemView.findViewById(R.id.ratingBar)
            sellerLogo = itemView.find(R.id.seller_logo)
            //跳转商家店铺页面
            itemView.setOnClickListener {
                context.startActivity(
                    Intent(
                        context,
                        BusinessActivity::class.java
                    )
                )
            }
        }

        fun bindData(data: Seller) {
            tvTittle.text = data.name
            tvHomeSale.text = data.sale
            tvHomeSendPrice.text =
                StringBuffer("￥").append(data.sendPrice).append("起送/配送费￥").append(data.deliveryFee)
            tvHomeDistance.text = data.distance
            ratingBar.rating = data.score!!.toFloat()
            Picasso.with(context).load(data.icon).into(sellerLogo)
        }
    }
}