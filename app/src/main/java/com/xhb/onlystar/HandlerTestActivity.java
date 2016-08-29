package com.xhb.onlystar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.xhb.R;

import java.lang.ref.WeakReference;

public class HandlerTestActivity extends AppCompatActivity {

    private TextView mTv;
    private static final int MESSAGE_CODE = 8888888;
    private TestHandler mHandler=new TestHandler(this);

    private TextView getTv() {
        return mTv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        mTv = (TextView) findViewById(R.id.tv);
        initView();
    }

    private void initView() {
        Message message=mHandler.obtainMessage();
        message.arg1=0;
        message.arg2=1;
        message.what= MESSAGE_CODE;
        message.obj= 10000;
        mHandler.sendMessageDelayed(message,1000);//延迟1秒后发送消息
    }


    private static class TestHandler extends Handler{

        private final WeakReference<HandlerTestActivity> mHandlerTestActivityWeakReference;

        private TestHandler(HandlerTestActivity activity){
            mHandlerTestActivityWeakReference=new WeakReference<HandlerTestActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerTestActivity handlerTestActivity=mHandlerTestActivityWeakReference.get();
            switch (msg.what){
                case MESSAGE_CODE:
                    int value= (int) msg.obj;
                    handlerTestActivity.getTv().setText("倒计时:"+value/1000);
                    if(value>0){
                        msg=Message.obtain();
                        msg.arg1=0;
                        msg.arg2=1;
                        msg.what= MESSAGE_CODE;
                        msg.obj= value-1000;
                        sendMessageDelayed(msg,1000);//延迟1秒后发送消息
                    }
                    break;
            }
        }
    }
}
