package com.xhb.onlystar;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.xhb.R;
import com.xhb.bean.Tab;
import com.xhb.fragment.HomeFragment;
import com.xhb.fragment.TestFragment;

import java.util.ArrayList;
import java.util.List;

public class TestTabHostActivity extends AppCompatActivity {

    private FragmentTabHost mTabhost;
    private List<Tab> mTabs = new ArrayList<Tab>(5);
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_tab_host);
        initView();
    }

    private void initView() {

        Tab tab_home = new Tab(HomeFragment.class, R.string.home, R.drawable.selector_icon_home);
        Tab tab_hot = new Tab(HomeFragment.class, R.string.hot, R.drawable.selector_icon_hot);
        Tab tab_category = new Tab(HomeFragment.class, R.string.catagory, R.drawable.selector_icon_category);
        Tab tab_cart = new Tab(HomeFragment.class, R.string.cart, R.drawable.selector_icon_cart);
        Tab tab_mine = new Tab(HomeFragment.class, R.string.mine, R.drawable.selector_icon_mine);

        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_category);
        mTabs.add(tab_cart);
        mTabs.add(tab_mine);


        mInflater = LayoutInflater.from(this);
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);//必须要

        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabhost.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildIndicator(tab));
            mTabhost.addTab(tabSpec, tab.getFragment(), null);
        }
        mTabhost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);//取消item之间的分割线
        mTabhost.setCurrentTab(0);//默认选择第一个
    }

    //得到indicator
    private View buildIndicator(Tab tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView img = (ImageView) view.findViewById(R.id.icon_tab);
        TextView text = (TextView) view.findViewById(R.id.txt_indicator);
        img.setBackgroundResource(tab.getIcon());
        text.setText(tab.getTitle());
        return view;
    }
}
