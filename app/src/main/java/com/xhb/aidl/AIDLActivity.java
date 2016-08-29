package com.xhb.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Toast;
import com.xhb.IMyAidlInterface;
import com.xhb.R;


public class AIDLActivity extends Activity {

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IMyAidlInterface mIMyAidlInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aidl);

        findViewById(R.id.button_aidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIMyAidlInterface != null){
                    try {
                        String name = mIMyAidlInterface.getName("nick_know_maco");
                        Toast.makeText(AIDLActivity.this, name + "", Toast.LENGTH_LONG).show();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        bindService(new Intent(this, AIDLService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

}















