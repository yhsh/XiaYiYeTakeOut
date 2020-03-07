package com.xiayiye.takeout.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.OrderBean
import com.xiayiye.takeout.presenter.OrderFragmentPresenter
import com.xiayiye.takeout.ui.adapter.OrderRvAdapter
import kotlinx.android.synthetic.main.fragment_order.*
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
 * 创建时间：2020/3/4 19:13
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.fragment
 * 文件说明：订单的fragment
 */
class OrderFragment : Fragment(), Observer {
    override fun update(p0: Observable?, p1: Any?) {
        //当收到推送消息，从这里刷新订单状态
    }

    lateinit var orderFragmentPresenter: OrderFragmentPresenter
    private lateinit var orderRvAdapter: OrderRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        orderFragmentPresenter = OrderFragmentPresenter(this)
        val view = inflater.inflate(R.layout.fragment_order, null)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        srl_order.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN)
        srl_order.setOnRefreshListener {
            initData()
        }
        rv_order_list.layoutManager = LinearLayoutManager(context)
    }

    private fun initData() {
        //请求服务器数据
        orderFragmentPresenter.getAllOrder()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    fun onSuccess(body: OrderBean) {
        srl_order?.let { srl_order.isRefreshing = false }
        //刷新adapter
        context?.let {
            orderRvAdapter = OrderRvAdapter(it, body.orderData)
            rv_order_list.adapter = orderRvAdapter
        }
    }

    fun onFail() {
        context?.let { Toast.makeText(context, "服务器繁忙", Toast.LENGTH_SHORT).show() }
        srl_order?.let { srl_order.isRefreshing = false }
    }
}