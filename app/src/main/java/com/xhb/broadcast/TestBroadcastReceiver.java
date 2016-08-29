package com.xhb.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.xhb.onlystar.SendBroadcastActivity;



public class TestBroadcastReceiver extends BroadcastReceiver {

    public static final String TAG = "myLog";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent!=null){
            if(TextUtils.equals(intent.getAction(), SendBroadcastActivity.COM_XHB_ONLYSTAR)){
                Log.i(TAG, "onReceive:action "+intent.getAction());
                Log.i(TAG, "onReceive:broadcast "+intent.getStringExtra("toast"));
            }
        }
    }
}
