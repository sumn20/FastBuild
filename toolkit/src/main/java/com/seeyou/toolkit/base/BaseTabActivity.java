package com.seeyou.toolkit.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.seeyou.toolkit.R;
import com.seeyou.toolkit.view.ContentPagerAdapter;

import java.util.List;

/**
 * @author sumn
 * date 2019/10/23
 */
public abstract class BaseTabActivity extends BaseActivity {
    Toolbar toolbar;
    TabLayout tabViewpagerTab;
    ViewPager tabViewpagerViewpager;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_tab;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        toolbar = findViewById(R.id.toolbar);
        tabViewpagerTab = findViewById(R.id.tab_viewpager_tab);
        tabViewpagerViewpager = findViewById(R.id.tab_viewpager_viewpager);
        bindToolbarWithBack(toolbar);
        toolbar.setTitle(getToolbarTitle());
        ContentPagerAdapter contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager(), getTabFragments(), getTabIndicators());
        tabViewpagerViewpager.setAdapter(contentPagerAdapter);
        tabViewpagerTab.setTabMode(TabLayout.MODE_FIXED);
        tabViewpagerTab.setupWithViewPager(tabViewpagerViewpager);

    }

    protected abstract List<Fragment> getTabFragments();

    protected abstract List<String> getTabIndicators();


    protected abstract String getToolbarTitle();
}
