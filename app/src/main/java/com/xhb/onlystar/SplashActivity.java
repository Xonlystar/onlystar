package com.xhb.onlystar;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.xhb.R;
import com.xhb.bean.UserInfo;

public class SplashActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    public static final String TITLE = "title";
    public static final String USER_INFO = "userInfo";
    public static final int REQUEST_CODE = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                UserInfo userInfo = new UserInfo("小明", 12);
                Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra(TITLE, "welcome");
                intent.putExtra(USER_INFO, userInfo);
                startActivityForResult(intent, REQUEST_CODE);
                finish();
            }
        }, 3000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("myLog","requestCode: " + requestCode+",resultCode:"+resultCode );

        if(requestCode == REQUEST_CODE && resultCode == MainActivity.RESULT_CODE){
            if(data != null){
                String title = data.getStringExtra(TITLE);
                Toast.makeText(SplashActivity.this,title,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
