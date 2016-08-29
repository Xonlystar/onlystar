package com.xhb.onlystar;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.xhb.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkActivity extends Activity implements View.OnClickListener {

    private EditText mEditText;
    private TextView mTextView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        findViews();
        setListeners();

    }

    private void setListeners() {
        mButton.setOnClickListener(this);
    }

    private void findViews() {
        mEditText = (EditText) findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);
        mButton = (Button) findViewById(R.id.button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String url = getEditTextUrl();

                // 请求网络数据
                // 1.申请网络权限
                new RequestNetworkDataTask().execute(url);
                break;
        }
    }


    private String getEditTextUrl() {
        return mEditText != null ? mEditText.getText().toString() : "";
    }

    private String requestData(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30000);
            connection.setRequestMethod("GET");  // GET POST

            connection.connect();
            int responseCode = connection.getResponseCode();
            //String responseMessage = connection.getResponseMessage();
            String result = null;
            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream = connection.getInputStream();
                Reader reader = new InputStreamReader(inputStream, "UTF-8");
                char[] buffer = new char[1024];
                reader.read(buffer);
                result = new String(buffer);
            } else {

            }

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i("myLog","非法的uri");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 异步任务处理

    class RequestNetworkDataTask extends AsyncTask<String,Integer,String>{

        // 在后台work之前
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 主线程
            // UI Loading

        }

        @Override
        protected String doInBackground(String[] params) {
            //
            String result = requestData(params[0]);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //执行完之后在主线程中
            mTextView.setText(result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }
}
