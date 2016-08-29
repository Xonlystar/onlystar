package com.xhb.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.xhb.db.DatabaseHelper;
import com.xhb.db.TestDbHelper;


public class TestContentProvider extends ContentProvider {


    private static UriMatcher sUriMatcher;
    public static final int URI_MATCH_USER = 1;
    public static final int URI_MATCH_BOOK = 2;
    public static final int URI_MATCH_user = 3;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(URIList.AUTHORITY, DatabaseHelper.USER_TABLE_NAME, URI_MATCH_USER);
        sUriMatcher.addURI(URIList.AUTHORITY, DatabaseHelper.BOOK_TABLE_NAME, URI_MATCH_BOOK);
        sUriMatcher.addURI(URIList.AUTHORITY, TestDbHelper.TABLE_NAME, URI_MATCH_user);
    }

    private TestDbHelper mDatabaseHelper;

    @Override
    public boolean onCreate() {
        mDatabaseHelper = new TestDbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            return null;
        }
        Cursor cursor = mDatabaseHelper.getReadableDatabase()
                .query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
       // Log.i("myLog","tableName---"+tableName+" uri---"+uri+"   cursor---"+cursor);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            return null;
        }
        long id = mDatabaseHelper.getWritableDatabase().insert(tableName, null, values);

        // uri: content://com.xhb/user
        return ContentUris.withAppendedId(uri, id);// content://com.xhbly/user/id
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            return -1;
        }

        int count = mDatabaseHelper.getWritableDatabase().delete(tableName, selection, selectionArgs);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (TextUtils.isEmpty(tableName)) {
            return -1;
        }
        int count = mDatabaseHelper.getWritableDatabase().update(tableName, values, selection, selectionArgs);
        return count;
    }

    private String getTableName(Uri uri) {
        int type = sUriMatcher.match(uri);
        String tableName = null;
        switch (type) {
            case URI_MATCH_USER:
                tableName = DatabaseHelper.USER_TABLE_NAME;
                break;
            case URI_MATCH_BOOK:
                tableName = DatabaseHelper.BOOK_TABLE_NAME;
                break;
            case URI_MATCH_user:
                tableName = TestDbHelper.TABLE_NAME;
                break;
        }

        return tableName;

    }
}
