package com.xhb.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.xhb.R;

/**
 * Created by onlystar on 2016/7/31.
 */

public class TestWidget extends AppWidgetProvider {

    public static final String WIDGET_BUTTON_ACTION = "widget_button_action";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        Intent intent=new Intent();
        intent.setClass(context,TestWidget.class);
        intent.setAction(WIDGET_BUTTON_ACTION);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intent,0);
        remoteViews.setOnClickPendingIntent(R.id.widget_btn,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetIds,remoteViews);
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent!=null&& TextUtils.equals(intent.getAction(),WIDGET_BUTTON_ACTION)){
            RemoteViews remoteViews=new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            remoteViews.setTextViewText(R.id.widget_tv,System.currentTimeMillis()+"");
            remoteViews.setTextColor(R.id.widget_tv, Color.WHITE);
            AppWidgetManager manager=AppWidgetManager.getInstance(context);
            ComponentName componentName=new ComponentName(context,TestWidget.class);
            manager.updateAppWidget(componentName,remoteViews);
        }
    }
}
