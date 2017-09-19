package com.iguitar.xiaoxiaozhitan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

/**
 * 闪屏页面
 */
public class SplashActivity extends Activity {
    private LinearLayout ll_welcome_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_splash);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startMyActivity(MainActivity.class, null);
//                finish();
//            }
//        }, 2000);
        init();
    }

    /**
     * 初始化动画
     */
    private void init() {
        ll_welcome_bg = (LinearLayout) findViewById(R.id.ll_welcome_bg);

        // 旋转动画，0 ~ 360
        RotateAnimation rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);

        // 缩放动画，从无到有
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                0, 1,
                0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);

        // 渐变动画，从无到有
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        // 创建动画集合
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);

        ll_welcome_bg.startAnimation(animationSet);

        // 监听动画
        animationSet.setAnimationListener(new MyAnimationListener());
    }

    class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

}
