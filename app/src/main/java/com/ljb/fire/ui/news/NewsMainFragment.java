package com.ljb.fire.ui.news;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.ljb.fire.R;
import com.ljb.fire.base.BaseFragment;
import com.ljb.fire.base.BaseFragmentAdapter;
import com.ljb.fire.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 新闻首页页面
 */
public class NewsMainFragment extends BaseFragment {


    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.tab)
    TabLayout mTab;
    @InjectView(R.id.iv_add_tab)
    ImageView mIvAddTab;
    @InjectView(R.id.vp_news)
    ViewPager mVpNews;


    @Override
    protected int getLayoutId() {
        return R.layout.frag_main_news;
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewFragment());
        fragments.add(new NewFragment());
        fragments.add(new NewFragment());
        fragments.add(new NewFragment());
        List<String > mTitle = new ArrayList<>();
        mTitle.add("A");
        mTitle.add("B");
        mTitle.add("C");
        mTitle.add("D");
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(),fragments,mTitle);
        mVpNews.setAdapter(adapter);
        mTab.setupWithViewPager(mVpNews);

    }

    @OnClick({R.id.iv_add_tab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_tab:
                break;

        }
    }
}
