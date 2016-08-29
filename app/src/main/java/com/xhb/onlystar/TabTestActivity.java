package com.xhb.onlystar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.xhb.R;
import com.xhb.fragment.TabLayoutFragment;

import java.util.ArrayList;
import java.util.List;


public class TabTestActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabAdapter mAdapter;
    private List<Fragment> mFragments;
    private String[] mTitile;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_test);
        mTabLayout= (TabLayout) findViewById(R.id.tl);
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mTitile=new String[]{"全部","热点","社会","娱乐","科技","头条","军事","历史","国际","视频","民生"};
        mFragments=new ArrayList<Fragment>();
        for (int i=0;i<mTitile.length;i++){
            mFragments.add(TabLayoutFragment.newInstance(i));
        }
        mAdapter=new TabAdapter(getSupportFragmentManager(),mFragments,mTitile);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    private class TabAdapter extends FragmentPagerAdapter{

        private List<Fragment> mFragments;
        private String[] mTitle;

        public TabAdapter(FragmentManager manager,List<Fragment> fragments,String[]mTitile){
            super(manager);
            this.mFragments=fragments;
            this.mTitle=mTitile;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }
}