package com.xhb.onlystar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.xhb.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.net.URLConnection;

public class ThreadActivity extends Activity implements View.OnClickListener {

    public static final String GEEK_BAND = "onlystar";
    private TextView mTextView;
    private Button mDownloadButton;
    private ProgressBar mProgressBar;
    private static final String APK_URL = "http://download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk";

    private Handler mHandler = new DownloadHandler(this);

    public TextView getTextView() {
        return mTextView;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // UI/Main线程

        setContentView(R.layout.activity_thread);
        mTextView = (TextView) findViewById(R.id.text_view);

        mDownloadButton = (Button) findViewById(R.id.download_button);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mDownloadButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_button:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        download(APK_URL);
                    }
                }).start();
                break;
        }
    }

    private void download(String apkUrl) {
        try {
            URL url = new URL(apkUrl);
            URLConnection urlConnection = url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();

            int contentLength = urlConnection.getContentLength(); // 要下载的文件的大小
            Log.i("myLog", "the download file's content length: " + contentLength);

            //File.separator代表/
            String downloadFoldersName = Environment.getExternalStorageDirectory() + File.separator + GEEK_BAND + File.separator;
            File file = new File(downloadFoldersName);
            if (!file.exists()) {
                file.mkdir();
            }

            String fileName = downloadFoldersName + "test.apk";
            File apkFile = new File(fileName);
            if (apkFile.exists()) {
                apkFile.delete();
            }

            int downloadSize = 0;

            byte[] bytes = new byte[1024];
            int length = 0;

            OutputStream outputStream = new FileOutputStream(fileName);

            while ((length = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, length);
                downloadSize += length;
                int progress = downloadSize * 100 / contentLength;
                // update UI

                Message message = mHandler.obtainMessage();
                message.obj = progress;
                message.what = 0;
                mHandler.sendMessage(message);

                Log.i("myLog", "download progress: " + progress);
            }
            Log.i("myLog", "download success");
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("myLog", "download failure");
        }
    }

    public static class DownloadHandler extends Handler {

        public final WeakReference<ThreadActivity> mActivity;

        public DownloadHandler(ThreadActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ThreadActivity activity = mActivity.get();

            switch (msg.what) {
                case 0:
                    int progress = (int) msg.obj;
                    activity.getProgressBar().setProgress(progress);
                    activity.getTextView().setText("progress:" + progress);
                    if (progress == 100) {
                        Toast.makeText(activity, "download success", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }

        }
    }
}
