package com.ljb.fire.ui;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.ljb.fire.R;
import com.ljb.fire.base.BaseActivity;
import com.ljb.fire.base.BasePresenter;
import com.ljb.fire.bean.Tab;
import com.ljb.fire.ui.fragment.CareFragment;
import com.ljb.fire.ui.fragment.GirlFragment;
import com.ljb.fire.ui.fragment.HomeFragment;
import com.ljb.fire.ui.fragment.VideoFragment;
import com.ljb.fire.ui.widget.FragmentTabHost;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


public class MainActivity extends BaseActivity {


    @InjectView(android.R.id.tabhost)
    FragmentTabHost mTabhost;
    //tab图标
    private int[] icons = {R.drawable.selector_home, R.drawable.selector_girl, R.drawable.selector_vedio, R.drawable
            .selector_care};


    private List<Tab> mTabs;
    private List<Fragment> mFragmentList;
    private LayoutInflater mInflater;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        mInflater = LayoutInflater.from(this);
        initFragment();
        initTab();
    }


    /**
     * 实例化tab
     */
    private void initTab() {
        mTabhost.setup(this, getSupportFragmentManager(), R.id.frame);
        //设置没有分割线
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);
        mTabs = new ArrayList<>();
        String[] titles = getResources().getStringArray(R.array.title_arr);
        mTabs = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            mTabs.add(new Tab(titles[i], icons[i], mFragmentList.get(i).getClass()));
        }
        for (Tab tab : mTabs) {
            TabHost.TabSpec spec = mTabhost.newTabSpec(tab.getTitle());
            View indicator = buildIndicator(tab);
            spec.setIndicator(indicator);
            mTabhost.addTab(spec, tab.getFragment(), null);
        }
        //默认选择第一个
        mTabhost.setCurrentTab(0);
    }

    /**
     * 构建indicator
     *
     * @param tab
     * @return
     */
    private View buildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView iv = view.findViewById(R.id.iv_icon_tab);
        TextView tv = view.findViewById(R.id.tv_indicator);
        iv.setImageResource(tab.getIcon());
        tv.setText(tab.getTitle());
        return view;
    }


    private void initFragment() {
        mFragmentList = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        GirlFragment girlFragment = new GirlFragment();
        VideoFragment videoFragment = new VideoFragment();
        CareFragment careFragment = new CareFragment();
        mFragmentList.add(homeFragment);
        mFragmentList.add(girlFragment);
        mFragmentList.add(videoFragment);
        mFragmentList.add(careFragment);
    }


}
