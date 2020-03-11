package com.xiayiye.takeout.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.xiayiye.takeout.R
import com.xiayiye.takeout.model.beans.Data
import com.xiayiye.takeout.model.beans.User
import com.xiayiye.takeout.presenter.LoginActivityPresenter
import com.xiayiye.takeout.utils.LogTools
import com.xiayiye.takeout.utils.SMSUtil
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject


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
 * 创建时间：2020/3/6 14:08
 * 个人小站：http://yhsh.wap.ai(已挂)
 * 最新小站：http://www.iyhsh.icoc.in
 * 联系作者：企鹅 13343401268
 * 博客地址：http://blog.csdn.net/xiayiye5
 * 项目名称：XiaYiYeTakeOut
 * 文件包名：com.xiayiye.takeout.ui.activity
 * 文件说明：登陆页面
 */
class LoginActivity : AppCompatActivity() {
    lateinit var loginPhone: String
    /**
     * 倒计时CountDownTimer
     * 每过1000毫秒执行一次onTick
     * 倒计时完成执行onFinish
     */
    var timer: CountDownTimer = object : CountDownTimer(60 * 1000, 1000) {
        override fun onTick(sin: Long) {
            tv_user_code.text = StringBuffer("剩余时间").append(sin / 1000).append("s")
            tv_user_code.isEnabled = false
        }

        override fun onFinish() {
            tv_user_code.text = "获取验证码"
            tv_user_code.isEnabled = true
        }
    }
    // 创建EventHandler对象
    private val eventHandler = object : EventHandler() {
        override fun afterEvent(event: Int, result: Int, data: Any) {
            if (data is Throwable) {
                val msg = data.message
                msg?.let {
                    LogTools.showLog("sms", msg)
                    try {
                        val jsonObject = JSONObject(msg)
                        val detail = jsonObject.getString("detail")
                        runOnUiThread {
                            Toast.makeText(this@LoginActivity, detail, Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            } else {
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    timer.start()
                    LogTools.showLog("sms", "获取验证码成功")
                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    LogTools.showLog("sms", "提交验证码成功。。。")
                    loginPhone = et_user_phone.text.toString().trim()
                    if (loginPhone.isNotEmpty()) {
                        //开始登录
                        loginActivityPresenter.loginByPhone(loginPhone)
                    } else {
                        runOnUiThread {
                            Toast.makeText(
                                this@LoginActivity,
                                "手机号为空，请重新输入",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
    lateinit var loginActivityPresenter: LoginActivityPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginActivityPresenter = LoginActivityPresenter(this)
        initListener()
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler)
    }

    private fun initListener() {
        iv_user_back.setOnClickListener { finish() }
        tv_user_code.setOnClickListener {
            loginPhone = et_user_phone.text.toString().trim()
            if (loginPhone.isNotEmpty()) {
                if (SMSUtil.judgePhoneNums(this, loginPhone)) {
                    SMSSDK.getVerificationCode("86", loginPhone)
                } else {
                    Toast.makeText(this, "手机号格式错误，请重新输入", Toast.LENGTH_SHORT).show()
                }
            }
        }
        iv_login.setOnClickListener {
            val code = et_user_code.text.toString().trim()
            loginPhone = et_user_phone.text.toString().trim()
            if (loginPhone.isNotEmpty() && code.isNotEmpty()) {
                if (SMSUtil.judgePhoneNums(this, loginPhone)) {
                    SMSSDK.submitVerificationCode("86", loginPhone, code)
                } else {
                    Toast.makeText(this, "手机号格式错误，请重新输入", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * 页面销毁的时候将其销毁即可
     */
    override fun onDestroy() {
        super.onDestroy()
        SMSSDK.unregisterEventHandler(eventHandler)
        //取消定时器
        timer.cancel()
    }

    fun onSuccess(user: Data) {
        val intent = Intent()
        user.phone = loginPhone
        intent.putExtra("user", user);
        setResult(666, intent)
        finish()
    }

    fun onFail() {
        finish()
    }
}