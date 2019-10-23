package com.seeyou.fastbuild;

import androidx.fragment.app.Fragment;

import com.seeyou.toolkit.base.BaseTabActivity;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends BaseTabActivity {


    @Override
    protected List<Fragment> getTabFragments() {
        List<Fragment> list=new ArrayList<>();
        list.add(new BlankFragment());
        list.add(new TestFragment());
        return list;
    }

    @Override
    protected List<String> getTabIndicators() {
        List<String> list=new ArrayList<>();
        list.add("空白");
        list.add("测试");
        return list;
    }

    @Override
    protected String getToolbarTitle() {
        return "tab测试界面";
    }
}
