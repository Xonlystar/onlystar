package com.xhb.onlystar;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xhb.R;
import com.xhb.service.MusicService;

public class ServiceTestActivity extends AppCompatActivity implements View.OnClickListener{

    private Button start_service,stop_service;
    private boolean isBind = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        start_service= (Button) findViewById(R.id.start_service);
        stop_service= (Button) findViewById(R.id.stop_service);
        start_service.setOnClickListener(this);
        stop_service.setOnClickListener(this);
    }

    private ServiceConnection mServiceConnection=new ServiceConnection() {

        private MusicService mMusicService;

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.MyBinder binder= (MusicService.MyBinder) iBinder;
            mMusicService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mMusicService=null;
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_service:
                isBind =bindService(new Intent(ServiceTestActivity.this, MusicService.class),mServiceConnection,BIND_AUTO_CREATE);
                //startService(new Intent(ServiceTestActivity.this, MusicService.class));
                break;
            case R.id.stop_service:
                if (isBind) {
                    unbindService(mServiceConnection);
                    isBind = false;
                }
                //stopService(new Intent(ServiceTestActivity.this, MusicService.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBind) {
            unbindService(mServiceConnection);
            isBind = false;
        }
    }
}
