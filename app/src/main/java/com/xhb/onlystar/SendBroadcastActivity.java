package com.xhb.onlystar;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xhb.R;
import com.xhb.broadcast.TestBroadcastReceiver;

public class SendBroadcastActivity extends AppCompatActivity {

    public static final String COM_XHB_ONLYSTAR = "com.xhb.onlystar";
    private TestBroadcastReceiver mReceiver;
    private IntentFilter mFilter;
    private Button mSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_broadcast);
        //注册需要一个IntentFilter
        mFilter = new IntentFilter();
        mFilter.addAction(COM_XHB_ONLYSTAR);
        //注册需要一个广播接收器
        mReceiver = new TestBroadcastReceiver();

        mSend = (Button) findViewById(R.id.send);
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(COM_XHB_ONLYSTAR);
                intent.putExtra("toast","this is the broadcast");
                sendBroadcast(intent);//发送多个广播能全部接收到
                //sendOrderedBroadcast(intent);//按照优先级接收
               // LocalBroadcastManager

            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        //注册
        registerReceiver(mReceiver, mFilter);
    }


    @Override
    protected void onStop() {
        super.onStop();
        //取消注册
        unregisterReceiver(mReceiver);
    }
}
