package com.xhb.onlystar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.xhb.R;
import com.xhb.view.MyProgressBar;
import com.xhb.view.MyProgressDialog;

public class ViewTestActivity extends AppCompatActivity {


    //自定义进度条控件
    private MyProgressBar pg;
    //SeekBar控件
    private SeekBar sb;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test);
        initViews();
        initEvents();

        MyProgressDialog dialog=new MyProgressDialog(this);
        dialog.setMsg("to be or not to be");
        dialog.show();
    }

    private void initViews(){
        pg =(MyProgressBar) findViewById(R.id.pg);
        sb = (SeekBar) findViewById(R.id.sb);
        tv= (TextView) findViewById(R.id.my_lv);

    }
    /**
     * 初始化事件监听与处理
     */
    private void initEvents() {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewTestActivity.this,MyListActivity.class));
            }
        });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //改变圆弧的进度，并重新绘制圆弧，主要是通过触发自定义控件的onDraw()方法达到目的
                pg.setProgress(progress);
            }
        });
    }
}
