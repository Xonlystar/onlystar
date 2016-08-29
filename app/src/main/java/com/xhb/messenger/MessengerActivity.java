package com.xhb.messenger;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xhb.R;
import com.xhb.aidl.AIDLActivity;

public class MessengerActivity extends AppCompatActivity {

    private Messenger mMessenger;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        // IBinder 对象
        // 通过服务端返回的Binder 对象 构造Messenger
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        findViewById(R.id.button_aidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMessenger != null) {
                    //Message message = Message.obtain(null, 0, "hello");
                    // 向服务端发送消息
                    try {
                        Message message = Message.obtain();
                        Bundle data = new Bundle();
                        data.putString("content", "lalala");
                        message.setData(data);
                        mMessenger.send(message);
                        Log.i("myLog","向服务端发送了消息");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        bindService(new Intent(MessengerActivity.this, MessengerService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

}
