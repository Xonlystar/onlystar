package com.xhb.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.xhb.R;
import com.xhb.onlystar.MainActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by onlystar on 2016/8/22.
 */
public class TestFile {

    private Context context;

    public TestFile(Context context) {
        this.context = context;
    }

   public void testAssets() throws IOException {

        // 第一种，直接读路径
        WebView webView = new WebView(context);
        webView.loadUrl("file:///android_asset/test.html");

        try {
            // open的只能是文件，不能是文件夹
            InputStream inputStream = context.getResources().getAssets().open("test.html");

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(context, "文件读取异常", Toast.LENGTH_SHORT).show();
        }

        // 读列表
        String[] filenames = context.getAssets().list("images");
        // 读图片
        InputStream inputStream = context.getAssets().open("images/dog.jpg");
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap);

        // 读音乐
        AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd("libai.mp3");

        MediaPlayer player = new MediaPlayer();
        player.reset();
        player.setDataSource(
                assetFileDescriptor.getFileDescriptor(),
                assetFileDescriptor.getStartOffset(),
                assetFileDescriptor.getLength());

        player.prepare();
        player.start();

    }

    public void testResFile() {
        InputStream inputStream = context.getResources().openRawResource(R.raw.chuxuezhe);

        context.getResources().getColor(R.color.abc_background_cache_hint_selector_material_dark);
        context.getResources().getString(R.string.abc_action_bar_home_description);
    }

    void testSDCard() {
        File file = new File("/sdcard/test/a.txt");
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Environment.getDataDirectory(); // 获取Android中的data数据目录
        Environment.getDownloadCacheDirectory();
        Environment.getExternalStorageDirectory();
    }


    public  void testFileDemo() {

        // create a new file of test.txt in the internal storage
        File file = new File(context.getFilesDir(), "test.txt");

        Log.i("myLog", "getFilesDir:" + context.getFilesDir().getAbsolutePath());
        Log.i("myLog", "file path:" + file.getAbsolutePath());

        String string = "Our teacher is handsome!";

        try {
            boolean isSuccess = file.createNewFile();
        } catch (IOException e) {
            Log.i("myLog", "test.txt create error:" + e.toString());
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = context.openFileOutput("test2.txt", Context.MODE_PRIVATE);
            try {
                fileOutputStream.write(string.getBytes());
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // check external storage state
        String state = Environment.getExternalStorageState();

        if (TextUtils.equals(state, Environment.MEDIA_MOUNTED)) {

        }

    }
}
