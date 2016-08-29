package com.xhb.onlystar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xhb.R;
import com.xhb.util.task.DataLoader;
import com.xhb.util.task.SimpleAsyncTask;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestAsyncTaskActivity extends AppCompatActivity {

    private static final String PATH = "http://a.hiphotos.baidu.com/image/h%3D200/sign=64175659840a19d8d403830503fb82c9/e7cd7b899e510fb3a78c787fdd33c895d0430c44.jpg";
    private Button mBtn;
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private TextView show_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_async_task);



        mBtn = (Button) findViewById(R.id.down_load_btn);
        show_text= (TextView) findViewById(R.id.show_text);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mImageView = (ImageView) findViewById(R.id.net_img);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new DownLoadTask().execute(PATH);
                new SimpleAsyncTask<Bitmap>(TestAsyncTaskActivity.this,R.string.loading,loader).execute();
            }
        });
    }

    private DataLoader<Bitmap>  loader=new DataLoader<Bitmap>() {
        @Override
        public void updateUi(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
        }

        @Override
        public Bitmap loadData() {
             InputStream mIn=null;
             ByteArrayOutputStream mBos=null;
            try {
                URL url = new URL(PATH);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(2000);
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    mIn = conn.getInputStream();
                   /* Bitmap map= BitmapFactory.decodeStream(in);
                    return map;*/
                    mBos = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    while ((len = mIn.read(bytes)) != -1) {
                        mBos.write(bytes, 0, len);
                        new Thread().sleep(200);
                    }
                    bytes = mBos.toByteArray();//图片的字节流数组
                    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (mIn != null) {
                        mIn.close();
                    }
                    if (mBos != null) {
                        mBos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    };

    class DownLoadTask extends AsyncTask<String, Integer, Bitmap> {

        private InputStream mIn;
        private ByteArrayOutputStream mBos;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                URL url = new URL(PATH);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(2000);
                conn.connect();
                if (conn.getResponseCode() == 200) {
                    long totalSize=conn.getContentLength();//获取任务的总大小
                    mIn = conn.getInputStream();
                   /* Bitmap map= BitmapFactory.decodeStream(in);
                    return map;*/
                    mBos = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int len = 0;
                    double currentSize=0;//获取当前下载的大小
                    while ((len = mIn.read(bytes)) != -1) {
                        mBos.write(bytes, 0, len);
                        currentSize+=len;
                        publishProgress((int)(currentSize/totalSize*100));
                        new Thread().sleep(200);
                    }
                    bytes = mBos.toByteArray();//图片的字节流数组
                    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (mIn != null) {
                        mIn.close();
                    }
                    if (mBos != null) {
                        mBos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
            show_text.setText("当前进度:"+values[0]+"%");
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
