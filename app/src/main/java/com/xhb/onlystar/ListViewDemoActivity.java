package com.xhb.onlystar;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.xhb.R;
import com.xhb.adapter.PhoneBookAdapter;
import com.xhb.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class ListViewDemoActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String LIST_VIEW_DATA_COUNTS = "list_view_data_counts";
    public static final String PREFERENCE_NAME = "preference_name";
    public static final int DEFAULT_VALUE = 10;
    private ListView mPhoneBookListView;
    private List<UserInfo> mUserInfos;
    private int mDataCounts = 10;
    private PhoneBookAdapter mPhoneBookAdapter;
    private EditText mDataCountEditText;
    private Button mConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);

        findViews();
        setData();
        setListeners();
    }

    private void findViews() {
        mPhoneBookListView = (ListView) findViewById(R.id.list_view);
        mDataCountEditText = (EditText) findViewById(R.id.data_counts_edit_text);
        mConfirmButton = (Button) findViewById(R.id.confirm_button);
    }

    @NonNull
    private void setData() {

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        mDataCounts = sharedPreferences.getInt(LIST_VIEW_DATA_COUNTS, DEFAULT_VALUE);

        mDataCountEditText.setText(String.valueOf(mDataCounts));
        mUserInfos = new ArrayList<>();
        for (int index = 0; index < mDataCounts; index++) {
            mUserInfos.add(new UserInfo("刘小明", 21));
        }
        mPhoneBookAdapter = new PhoneBookAdapter(ListViewDemoActivity.this, mUserInfos);

        mPhoneBookListView.setAdapter(mPhoneBookAdapter);
    }

    private void setListeners() {
        mPhoneBookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    // 新建另外一批数据
                    // 替换掉老的数据
                    // 刷新listview，让它更新自己的视图
                    mUserInfos.clear();
                    mUserInfos.add(new UserInfo("我是新的数据一", 1892));
                    mUserInfos.add(new UserInfo("我是新的数据二", 2345));
                    mUserInfos.add(new UserInfo("我是新的数据三", 3245));

                    mPhoneBookAdapter.refreshData(mUserInfos);
                    mPhoneBookAdapter.notifyDataSetChanged();
                }

                Toast.makeText(ListViewDemoActivity.this, mUserInfos.get(position).getUserName() + "被我点击了，怎么办？", Toast.LENGTH_LONG).show();
            }
        });

        //

        mPhoneBookListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewDemoActivity.this, mUserInfos.get(position).getUserName() + "被我长按了，怎么办？", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        mConfirmButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirm_button:
                String countString = mDataCountEditText.getText().toString();
                mDataCounts = Integer.valueOf(countString);
                refreshListView();

                saveData2Preference(mDataCounts);
                break;
        }
    }

    private void saveData2Preference(int dataCounts) {

        // 系统会自动帮我们创建一个XML文件 名字是"preference_name" 地址在：data/data/com.geekband.Test01/Shard_prefs
        SharedPreferences sharedPreferences = ListViewDemoActivity.this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(LIST_VIEW_DATA_COUNTS, dataCounts);

        editor.commit();
        // 和网络相关，和IO操作相关的，都要用异步
        // 后台写数据，另开线程
        editor.apply();

        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

            }
        });


    }

    private void refreshListView() {
        mUserInfos.clear();
        for (int index = 0; index < mDataCounts; index++) {
            mUserInfos.add(new UserInfo("刘小明", 21));
        }
        mPhoneBookAdapter.refreshData(mUserInfos);
        mPhoneBookAdapter.notifyDataSetChanged();
    }
}
