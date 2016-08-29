package com.xhb.onlystar;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.xhb.R;
import com.xhb.view.Indicator;

import java.util.ArrayList;
import java.util.List;

public class IndicatorActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private List<View> views=new ArrayList<View>();
    private Indicator indicator;
    private int[]ID=new int[]{R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);//设置导航栏图标
        toolbar.setLogo(R.drawable.loading_0);//设置logo
        toolbar.setTitle("这是标题");//设置主标题
        toolbar.setSubtitle("小标题");//设置子标题
        //设置子菜单
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        setSupportActionBar(toolbar);




        initData();
        ViewPager viewPager= (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPagerAdapter());
        viewPager.setOnPageChangeListener(this);
        indicator= (Indicator) findViewById(R.id.my_indicator);
    }

    private void initData() {
        for(int i=0;i<4;i++){
            ImageView imageView=new ImageView(this);
            imageView.setBackgroundResource(ID[i]);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(imageView);
        }
    }

    @Override//viewpager滚动调用
    /****
     * position
     * positionOffset  偏移量
     * positionOffsetPixels  偏移的百分比
     */
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
          indicator.setOffset(position,positionOffset);
    }

    @Override//viewPager选中
    public void onPageSelected(int position) {

    }

    @Override//viewpager滑动状态改变
    public void onPageScrollStateChanged(int state) {

    }

    class MyPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position=position%views.size();
            View view=views.get(position);
            ViewParent v=view.getParent();
            if(v!=null){
                ViewGroup parent = (ViewGroup)v;
                parent.removeView(view);
            }
            container.addView(view);
            return view;
        }
    }


    //toolbar菜单栏

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
