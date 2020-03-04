package com.xiayiye.takeout.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xiayiye.takeout.R
import com.xiayiye.takeout.ui.fragment.HomeFragment
import com.xiayiye.takeout.ui.fragment.MoreFragment
import com.xiayiye.takeout.ui.fragment.OrderFragment
import com.xiayiye.takeout.ui.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //添加所有页面的fragment
    val list = listOf<Fragment>(HomeFragment(), OrderFragment(), UserFragment(), MoreFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //下面是Kotlin语言查找id的方法
//        val mainBottomBar = findViewById<LinearLayout>(R.id.main_bottom_bar)
        initBottomBar()
        //默认选中第一个
        changeIndex(0)
    }

    private fun initBottomBar() {
        for (index in 0 until main_bottom_bar.childCount) {
            main_bottom_bar.getChildAt(index).setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    changeIndex(index)
                }
            })
        }
    }

    private fun changeIndex(index: Int) {
        for (position in 0 until main_bottom_bar.childCount) {
            val childView = main_bottom_bar.getChildAt(position)
            if (index == position) {
                senEnable(childView, false)
            } else {
                senEnable(childView, true)
            }
        }
        //点击哪个切换哪个fragment
        //Android的app包下
//        fragmentManager.beginTransaction().replace(R.id.main_content, list[index])
        //AndroidX的app包下
        supportFragmentManager.beginTransaction().replace(R.id.main_content, list[index]).commit()
    }

    private fun senEnable(childView: View, isEnabled: Boolean) {
        childView.isEnabled = isEnabled
        if (childView is ViewGroup) {
            for (position in 0 until childView.childCount) {
                childView.getChildAt(position).isEnabled = isEnabled
            }
        }
    }
}
