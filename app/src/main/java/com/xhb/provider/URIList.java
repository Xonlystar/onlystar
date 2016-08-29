package com.xhb.provider;


import com.xhb.db.DatabaseHelper;

public class URIList {

    public static final String CONTENT = "content://";
    public static final String AUTHORITY = "com.xhb";

    public static final String USER_URI = CONTENT + AUTHORITY + "/" + DatabaseHelper.USER_TABLE_NAME;
    public static final String BOOK_URI = CONTENT + AUTHORITY + "/" + DatabaseHelper.BOOK_TABLE_NAME;


}
