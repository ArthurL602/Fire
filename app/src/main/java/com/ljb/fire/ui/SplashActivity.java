package com.ljb.fire.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.ljb.fire.R;
import com.ljb.fire.base.BaseActivity;
import com.ljb.fire.base.BasePresenter;

import butterknife.InjectView;

/**
 * Splash页面
 */
public class SplashActivity extends BaseActivity {


    @InjectView(R.id.iv_logo)
    ImageView mIvLogo;
    @InjectView(R.id.tv_name)
    TextView mTvName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1.0f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1.0f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1.0f);

        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(mIvLogo, alpha, scaleX, scaleY);
        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(mTvName, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator1, animator2);
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                goTo(MainActivity.class);
                finish();
            }
        });
        animatorSet.start();
    }



}
