package com.xiayiye.takeout.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.xiayiye.takeout.R;

/**
 * @author xiayiye
 */
public class SplashActivity extends Activity {

    private View sp;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash_main);
        sp = findViewById(R.id.sp);
//		sp.animate().scaleYBy(1.0f);
//		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(sp, "rotationX", 0.0f,360f);
//		objectAnimator = ObjectAnimator.ofFloat(sp, "scaleX", 0.0f,1.0f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(sp, "xyy", 0.0f, 1.0f);
        //设置动画时间，开启动画
        objectAnimator.setDuration(5000).start();
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Toast.makeText(getApplicationContext(), "动画已结束，进入主界面！", Toast.LENGTH_LONG).show();
                //动画结束的时候调用
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        });
        objectAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                sp.setAlpha(cVal);
                sp.setScaleX(cVal);
                sp.setScaleY(cVal);
            }
        });
    }
}
