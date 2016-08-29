package com.xhb.onlystar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Toast;

import com.xhb.R;
import com.xhb.adapter.ActivityAdapter;
import com.xhb.adapter.MyListAdapter;
import com.xhb.aidl.AIDLActivity;
import com.xhb.bean.ActivityListInfo;
import com.xhb.bean.UserInfo;
import com.xhb.messenger.MessengerActivity;
import com.xhb.util.TestFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.xhb.R.id.file_test;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int RESULT_CODE = 1234;
    private boolean isExit = false;
    private SeekBar seek_bar;
    private ProgressBar progress;
    private ListView lv;
    private MyListAdapter adapter;
    private GridView gv;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        handleIntentData();
        /*
        mLayoutInflater = getLayoutInflater();
        mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mLayoutInflater = LayoutInflater.from(MainActivity.this);
        * */
    }

    private void handleIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra(SplashActivity.TITLE);
            UserInfo userInfo = (UserInfo) intent.getSerializableExtra(SplashActivity.USER_INFO);
            setTitle("名字是：" + userInfo.getUserName());
        }
    }

    private void initView() {

        mGridView = (GridView) findViewById(R.id.grid_view);
        final List<ActivityListInfo> activityListInfos = new ArrayList<>();
        activityListInfos.add(new ActivityListInfo("TabLayout+viewpager", TabTestActivity.class));
        activityListInfos.add(new ActivityListInfo("发送广播", SendBroadcastActivity.class));
        activityListInfos.add(new ActivityListInfo("FragmentTabhost", TestTabHostActivity.class));
        activityListInfos.add(new ActivityListInfo("我的自定义view", ViewTestActivity.class));
        activityListInfos.add(new ActivityListInfo("WebView", TestWebviewActivity.class));
        activityListInfos.add(new ActivityListInfo("fragment测试", FragmentTestActivity.class));
        activityListInfos.add(new ActivityListInfo("Toolbar及自定义indicator", IndicatorActivity.class));
        activityListInfos.add(new ActivityListInfo("handler测试", HandlerTestActivity.class));
        activityListInfos.add(new ActivityListInfo("AsyncTask异步任务下载图片", TestAsyncTaskActivity.class));
        activityListInfos.add(new ActivityListInfo("service测试", ServiceTestActivity.class));
        activityListInfos.add(new ActivityListInfo("Tween Animation动画", TestAnimationActivity.class));
        activityListInfos.add(new ActivityListInfo("ference测试", ListViewDemoActivity.class));
        activityListInfos.add(new ActivityListInfo("db与provider测试", DbTestActivity.class));
        activityListInfos.add(new ActivityListInfo("网络测试", NetworkActivity.class));
        activityListInfos.add(new ActivityListInfo("网络数据解析", XmlAndJsonActivity.class));
        activityListInfos.add(new ActivityListInfo("多线程", ThreadActivity.class));
        activityListInfos.add(new ActivityListInfo("进程通信", MessengerActivity.class));
        activityListInfos.add(new ActivityListInfo("aidl测试", AIDLActivity.class));
        activityListInfos.add(new ActivityListInfo("传感器", SensorManagerActivity.class));
        activityListInfos.add(new ActivityListInfo("百度地图", MyMapActivity.class));

        mGridView.setAdapter(new ActivityAdapter(getApplicationContext(), activityListInfos));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("myLog", "class--->" + activityListInfos.get(position).getActiviy());
                startActivity(new Intent(MainActivity.this, (activityListInfos.get(position)).getActiviy()));
            }
        });

        seek_bar = (SeekBar) findViewById(R.id.seek_bar);
        progress = (ProgressBar) findViewById(R.id.progress);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new MyListAdapter(getApplicationContext(), new String[]{"123", "456"});
        lv.setAdapter(adapter);
        gv = (GridView) findViewById(R.id.gv);
        gv.setAdapter(adapter);
    }


    private final Runnable onBackTimeThread = new Runnable() {
        public void run() {
            isExit = false;
        }
    };

/*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Handler mainLoopHandler=new Handler(Looper.getMainLooper());
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                mainLoopHandler.removeCallbacks(onBackTimeThread);
                isExit = false;
                finish();
            }else {
                isExit = true;
                Toast.makeText(this, "再按一下退出", Toast.LENGTH_SHORT).show();
                mainLoopHandler.postDelayed(onBackTimeThread, 2000);
            }
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }*/


    @Override
    public void onBackPressed() {
        Handler handler = new Handler(Looper.getMainLooper());
        if (isExit) {
            handler.removeCallbacks(onBackTimeThread);
            isExit = false;
            finish();
        } else {
            isExit = true;
            Toast.makeText(this, "再按一下退出", Toast.LENGTH_SHORT).show();
            handler.postDelayed(onBackTimeThread, 3000);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case file_test:
                try {
                    TestFile mTest = new TestFile(getApplicationContext());
                    mTest.testAssets();
                    mTest.testFileDemo();
                    mTest.testResFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


}
