package com.xiayiye.takeout.utils

import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration
import com.xiayiye.takeout.utils.AndroidUtils.getApplicationByReflection

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
 * 创建时间：2020/3/4 20:28
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.utils
 * 文件说明：Kotlin版：单例设计模式
 */
class XiaYiYeUtils private constructor() {

    companion object {
        //拿到工具类对象
        fun getInstance(): XiaYiYeUtils {
            return CreateObject.ANDROID_UTILS
        }
    }

    object CreateObject {
        val ANDROID_UTILS = XiaYiYeUtils()
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Int): Int {
        val scale = getApplicationByReflection()!!.resources.displayMetrics.density;
        return (dpValue * scale + 0.5f).toInt();
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Int): Int {
        val scale = getApplicationByReflection()!!.getResources().getDisplayMetrics().density;
        return (pxValue / scale + 0.5f).toInt();
    }

    /**
     * 获取是否有虚拟按键
     * 通过判断是否有物理返回键反向判断是否有虚拟按键
     * @return
     */
    fun checkDeviceHasNavigationBar(): Boolean {
        val hasMenuKey = ViewConfiguration.get(getApplicationByReflection()!!)
            .hasPermanentMenuKey()
        val hasBackKey = KeyCharacterMap
            .deviceHasKey(KeyEvent.KEYCODE_BACK)
        return !hasMenuKey and !hasBackKey
    }

    //获取虚拟按键的高度
    fun getNavigationBarHeight(): Int {
        var result = 0
        if (checkDeviceHasNavigationBar()) {
            val resourceId = getApplicationByReflection()!!.resources.getIdentifier(
                "navigation_bar_height",
                "dimen",
                "android"
            )
            if (resourceId > 0) {
                result = getApplicationByReflection()!!.resources.getDimensionPixelSize(resourceId)
            }
        }
        return result
    }

    /**
     * ******防止按钮连续点击*******
     */
    private var lastClickTime: Long = 0

    @Synchronized
    fun isFastClick(): Boolean {
        val time = System.currentTimeMillis()
        if (time - lastClickTime < 500) {
            return true
        }
        lastClickTime = time
        return false
    }
}