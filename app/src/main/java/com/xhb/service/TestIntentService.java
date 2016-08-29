package com.xhb.service;

import android.app.IntentService;
import android.content.Intent;

/**
 * Created by onlystar on 2016/7/29.
 */

public class TestIntentService extends IntentService {

    public TestIntentService(String name) {
        super(name);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //UI线程，不能进行耗时操作
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //排队 从MesssageQueue同步操作-------------->排队买书，处理intent数据
    }
}
