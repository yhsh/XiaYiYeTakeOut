package com.xiayiye.takeout.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xiayiye.takeout.R
import com.xiayiye.takeout.dagger2.component.DaggerHomeFragmentComponent
import com.xiayiye.takeout.dagger2.module.HomeFragmentModule
import com.xiayiye.takeout.model.beans.Seller
import com.xiayiye.takeout.presenter.HomeFragmentPresenter
import com.xiayiye.takeout.ui.adapter.HomeRvAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

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
 * 创建时间：2020/3/4 19:13
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.fragment
 * 文件说明：首页的fragment
 */
class HomeFragment : Fragment() {
    private lateinit var homeRvAdapter: HomeRvAdapter
    @Inject
    lateinit var homePresenter: HomeFragmentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化P层
//      homePresenter = HomeFragmentPresenter(this)
        DaggerHomeFragmentComponent.builder().homeFragmentModule(HomeFragmentModule(this)).build()
            .inject(this)
        homePresenter.getHomeInfo()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, null)
    }

    private fun setHomeData(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0 until 100) {
            list.add("外卖第$i 家")
        }
        return list
    }

    /**
     * fragment布局初始化成功后调用的方法
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_home.layoutManager = LinearLayoutManager(context)
        homeRvAdapter = HomeRvAdapter(context!!)
        rv_home.adapter = homeRvAdapter
    }

    val list = ArrayList<Seller>()
    fun homeOnSuccess(nearbySellerList: List<Seller>, otherSellerList: List<Seller>) {
        list.clear()
        list.addAll(nearbySellerList)
        list.addAll(otherSellerList)
        homeRvAdapter.setData(list)
        //设置rv的滑动监听
        var sum: Int = 0
        rv_home?.let {
            rv_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    sum += dy
                    if (sum >= 240) {
                        //当超过240的高度将透明度改为完全不透明
                        sum = 255
                    } else if (sum <= 55) {
                        //当小于55个高度将透明度恢复到初始透明度
                        sum = 55
                    }
                    ll_title_container.setBackgroundColor(Color.argb(sum, 0x31, 0x90, 0xe8))
                }
            })
        }
    }

    fun homeOnFail() {

    }
}