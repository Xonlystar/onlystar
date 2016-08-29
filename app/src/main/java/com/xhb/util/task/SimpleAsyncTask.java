package com.xhb.util.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;

/**
 * Created by onlystar on 2016/8/1.
 */

public class SimpleAsyncTask<Result> extends AsyncTask<Void,Integer,Result>{

    private Context context;
    private ProgressDialog mProgressDialog;
    private String message;
    private DataLoader<Result> loader;

    public SimpleAsyncTask(Context context,int msgID,DataLoader<Result> loader){
        this.context=context;
        message=context.getString(msgID);
        this.loader=loader;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog=new ProgressDialog(context);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    @Override
    protected Result doInBackground(Void... params) {
        try{
            return loader.loadData();
        }catch (Exception e){
            mProgressDialog.dismiss();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Result result) {
        loader.updateUi(result);
        mProgressDialog.dismiss();
        super.onPostExecute(result);
    }
}
