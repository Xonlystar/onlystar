package com.xhb.onlystar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhb.R;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class XutilsActivity extends AppCompatActivity {

    private static final String TAG = "myLog";
    private ImageView img;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xutils);
        img = (ImageView) findViewById(R.id.img_show);
        text = (TextView) findViewById(R.id.text_show);
        //new NetImg().execute("http://d.hiphotos.baidu.com/image/pic/item/0ff41bd5ad6eddc492d491153ddbb6fd52663328.jpg");
        new NetText().execute("http://www.baidu.com");
    }

    class NetText extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return getNetText(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            text.setText(s);
            super.onPostExecute(s);
        }
    }


    class NetImg extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            return getNetPictureGet(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.i(TAG, "bitmap: " + bitmap);
            img.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
    }

    private Bitmap getNetPictureGet(String u) {
        Log.e("myLog", "url：" + u);
        Bitmap bitmap = null;
        int responseCode = 0;
        InputStream is = null;
        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.connect();
            responseCode = conn.getResponseCode();
            Log.e("myLog", "responseCode：" + responseCode);
            if (responseCode == 200) {
                is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is);
                Log.e("myLog", "bitmap：" + bitmap);
            }
        } catch (IOException e) {
            Log.i("myLog", "访问失败：" + responseCode);
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return bitmap;
    }


    private String getNetText(String u) {
        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(10 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                StringBuffer sb=new StringBuffer();
                BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String str=null;
                while((str=reader.readLine())!=null){
                    sb.append(str);
                }
                return sb.toString();
            }
        } catch (Exception e) {
            Log.e("myLog", "e：" + e.getMessage());
        }
        return null;
    }


}
