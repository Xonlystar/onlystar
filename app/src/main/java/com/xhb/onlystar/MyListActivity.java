package com.xhb.onlystar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import com.xhb.R;
import com.xhb.adapter.ListViewAdapter;
import com.xhb.view.MyListView;

public class MyListActivity extends AppCompatActivity implements MyListView.OnRefreshListener,
        MyListView.OnLoadListener {

    private MyListView lstv;
    private ListViewAdapter adapter;
    private List<String> list = new ArrayList<String>();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            List<String> result = (List<String>) msg.obj;
            switch (msg.what) {
                case MyListView.REFRESH:
                    lstv.onRefreshComplete();
                    list.clear();
                    list.addAll(result);
                    break;
                case MyListView.LOAD:
                    lstv.onLoadComplete();
                    list.addAll(result);
                    break;
            }
            lstv.setResultSize(result.size());
            adapter.notifyDataSetChanged();
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        lstv = (MyListView) findViewById(R.id.lstv);
        adapter = new ListViewAdapter(this, list);
        lstv.setAdapter(adapter);
        lstv.setOnRefreshListener(this);
        lstv.setOnLoadListener(this);
        initData();
    }

    private void initData() {
        loadData(MyListView.REFRESH);
    }

    private void loadData(final int what) {
        // 这里模拟从服务器获取数据
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = what;
                msg.obj = getData();
                handler.sendMessage(msg);
            }
        }).start();
    }

    @Override
    public void onRefresh() {
        loadData(MyListView.REFRESH);
    }

    @Override
    public void onLoad() {
        loadData(MyListView.LOAD);
    }

    // 测试数据
    public List<String> getData() {
        List<String> result = new ArrayList<String>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            long l = random.nextInt(10000);
            result.add("当前条目的ID：" + l);
        }
        return result;
    }
}


