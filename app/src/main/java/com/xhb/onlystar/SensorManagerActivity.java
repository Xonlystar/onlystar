package com.xhb.onlystar;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.xhb.R;

public class SensorManagerActivity extends Activity implements SensorEventListener {

    private ImageView mImageView;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private float mStartDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_manager);

        mImageView = (ImageView) findViewById(R.id.imageView);

        //获取SensorManager对象
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //获取Sensor对象
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ALL);

        //  判断传感器是否存在
        if (mSensor != null) {

        } else {
            //传感器不存在
        }

        //安卓自带的定位：
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.i("myLog------Location", "onLocationChanged" + location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i("myLog------Location", "onStatusChanged" + status);
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册Sensor对象
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注销Sensor对象
        mSensorManager.unregisterListener(this);
    }

    //重写onSensorChanged，onAccuracyChanged方法
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float degree = -event.values[0];

            RotateAnimation rotateAnimation = new RotateAnimation(
                    mStartDegree, degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            );

            rotateAnimation.setDuration(300);
            mImageView.startAnimation(rotateAnimation);
            mStartDegree = degree;
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[SensorManager.DATA_X];
            float y = event.values[SensorManager.DATA_Y];
            float z = event.values[SensorManager.DATA_Z];
            if (x > 18 || y > 18 || z > 18) {
                Log.i("myLog", "Accelerometer:" + "摇一摇");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // 当传感器精度发生变化的时候
    }
}
