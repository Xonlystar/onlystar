package com.xhb.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

public class MessengerService extends Service {

    /**
     * 构建handler 对象
     */
    public static Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            // 接受客户端发送的消息
            super.handleMessage(msg);
             // 处理消息
            switch (msg.what){
                case 0:
                    // fdsfdskfdlsj;
                    Log.i("myLog","接收到客户端消息:"+msg.getData().get("content"));
                    break;
            }
        }
    };

    // 适合于多进程，单线程。。不需要考虑线程安全
    Messenger mMessenger = new Messenger(handler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
