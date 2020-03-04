package com.xiayiye.takeout

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //下面是Kotlin语言查找id的方法
//        val mainBottomBar = findViewById<LinearLayout>(R.id.main_bottom_bar)
        initBottomBar()
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
