package com.xiayiye.takeout.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.Goods
import com.xiayiye.takeout.model.beans.GoodsBean
import com.xiayiye.takeout.presenter.GoodsFragmentPresenter
import com.xiayiye.takeout.ui.adapter.GoodTypeRvAdapter
import com.xiayiye.takeout.ui.adapter.GoodsAdapter
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
 * 创建时间：2020/3/8 13:24
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.fragment
 * 文件说明：
 */
class GoodsFragment : Fragment() {
    val goodsFragmentPresenter by lazy { GoodsFragmentPresenter(this) }
    lateinit var goodTypeRvAdapter: GoodTypeRvAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //请求数据
        goodsFragmentPresenter.getGoodListUrl()
        return LayoutInflater.from(context).inflate(R.layout.fragment_goods, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //设置相关数据
        rv_goods_type.layoutManager = LinearLayoutManager(context)
    }

    fun onSuccess(
        goodBean: GoodsBean,
        allTypeGood: ArrayList<Goods>
    ) {
        goodTypeRvAdapter = GoodTypeRvAdapter(goodBean.goodData, this)
        rv_goods_type.adapter = goodTypeRvAdapter
        slhlv.adapter = GoodsAdapter(this, allTypeGood)
        //数据展示成功后再设置滑动监听
        slhlv.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                //找到旧的position位置
                val oldPosition = goodTypeRvAdapter.selectPosition
                val oldTypeId = allTypeGood[oldPosition].typeId
                //滑动获取新的position
                val newTypeId = allTypeGood[firstVisibleItem].typeId
                if (oldTypeId != newTypeId) {
                    //修改左侧滑动位置
                    val newPosition = goodsFragmentPresenter.getTypePositionByTypeId(newTypeId)
                    //设置新的选中位置
                    goodTypeRvAdapter.selectPosition = newPosition
                    //刷新布局
                    goodTypeRvAdapter.notifyDataSetChanged()
                    //将左边列表滑动到滑动的位置
                    rv_goods_type.scrollToPosition(newPosition)
                }
            }

            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }

        })
    }

    fun onFail() {
        Toast.makeText(context, "服务器请求出错", Toast.LENGTH_SHORT).show()
    }
}