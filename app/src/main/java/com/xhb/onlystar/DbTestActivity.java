package com.xhb.onlystar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xhb.R;
import com.xhb.db.TestDbHelper;


public class DbTestActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_test);
        TestDbHelper helpter = new TestDbHelper(this);
        mDatabase = helpter.getWritableDatabase();
    }

    public void add(View v) {
        //原生调用数据库
        //mDatabase.execSQL("insert into user(username, age) values ('liu da ming','5岁')");

        ContentValues params = new ContentValues();
        params.put("name", "王二小");
        params.put("age", 100);
        long insert = mDatabase.insert(TestDbHelper.TABLE_NAME, null, params);
        if (insert != -1) {
            Toast.makeText(DbTestActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
        }
        queryAll();
    }

    public void delete(View v) {
        int delete = mDatabase.delete(TestDbHelper.TABLE_NAME, "name=?", new String[]{"王二小"});
        if (delete != 0) {
            Toast.makeText(DbTestActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View v) {
        ContentValues params = new ContentValues();
        params.put("name", "李白");
        mDatabase.update(TestDbHelper.TABLE_NAME, params, "name=?", new String[]{"王二小"});
    }

    public void query(View v) {
        queryAll();
    }

    public void transaction(View v) {
        //开始事务-----此时数据库会被锁定
        mDatabase.beginTransaction();
        try {
            //我的操作
            for (int i = 0; i < 100; i++) {
                ContentValues params = new ContentValues();
                params.put("name", "王二小");
                params.put("age", 100);
                long insert = mDatabase.insert(TestDbHelper.TABLE_NAME, null, params);
            }
            //设置事务成功,否则会自动回滚不提交
            mDatabase.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDatabase.endTransaction();//关闭事物并且设置成功后才会提交
        }
    }

    public void my_provider(View view){
        ContentResolver contentResolver=getContentResolver();
        Uri uri = Uri.parse("content://com.xhb/user");
         //Uri uri = Uri.parse("content://com.android.contacts/contacts");
        /**
         * content://+包名+表名       content://com.xhb/user     表中的所有数据
         * content://+包名+表名+id    content://com.xhb/user/10  表中id为10的记录
         * content://+包名+表名+id+字段 content://com.xhb/user/10/name 表中id为10的记录的name字段
         */
          //Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
          //Uri uri = ContactsContract.Contacts.CONTENT_URI;
              Cursor cursor = contentResolver.query(uri, null, null, null, null);
               if(cursor != null && cursor.moveToFirst()){
                   for (int i = 0; i < cursor.getCount(); i++) {
                      //String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                       String name = cursor.getString(cursor.getColumnIndex("name"));
                       Log.i("myLog----", "contentProvider,name:"+name);
                       cursor.moveToNext();
                   }
               }
    }


    public void queryAll() {
        // query :
        // 游标 要查找的数据集合
        Cursor cursor = mDatabase.query(TestDbHelper.TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                Log.i("myLog", "i------->" + i + "                name------>" + name + "            age-------->" + age);
            }
        }
    }
}
