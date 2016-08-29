package com.xhb.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.xhb.IMyAidlInterface;


public class AIDLService extends Service {

    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName(String nickName) throws RemoteException {
            Log.i("myLog","nickName:"+nickName);
            return nickName + "aidl_hahaha";
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }

}
