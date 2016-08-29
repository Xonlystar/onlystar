package com.xhb.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.xhb.R;
import com.xhb.onlystar.ServiceTestActivity;

/**
 * Created by onlystar on 2016/7/29.
 */

/**
 * Service两种方式
 * 1.context.startService()  -> onCreate()  -> onStartCommand()  -> Service running  ->
 * context.stopService()  -> onDestroy()  -> Service stop
 * 2. bindService()  ——> onCreate()  ——> onBind()  ——> Service running  ——>
 * onUnbind()  ——> onDestroy()  ——> Service stop
 */

public class MusicService extends Service {
    public static final String TAG = MusicService.class.getSimpleName();
    private MediaPlayer mMediaPlayer;
    private MyBinder mMyBinder = new MyBinder();


    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(this, R.raw.chuxuezhe);

        //通知栏
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, ServiceTestActivity.class), 0);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1, mBuilder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mMediaPlayer.start();
        return mMyBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mMediaPlayer.start();
        return START_NOT_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        stopForeground(true);
    }


    public class MyBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

}
