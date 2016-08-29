package com.xhb.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xhb.R;

import java.security.spec.MGF1ParameterSpec;

/**
 * Created by onlystar on 2016/8/1.
 */

public class Indicator extends TextView {
    //前景颜色的画笔
    private Paint mForePaint;
    //背景颜色的画笔
    private Paint mBgPaint;
    //偏移量
    private float offset=0;
    //半径
    private int radius;
    //数量
    private int mNum;
    //背景颜色
    private int bgColor;
    //前景颜色
    private int foreColor;

    public void setOffset(int position,float offset) {
        position=position%mNum;
        this.offset = position*3*radius+offset*3*radius;
        invalidate();
    }




    public Indicator(Context context) {
        this(context,null);
    }

    public Indicator(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Indicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context,attrs,defStyleAttr);
    }

    private void initPaint(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.Indicator);
        radius=typedArray.getInteger(R.styleable.Indicator_radius,20);
        mNum=typedArray.getInteger(R.styleable.Indicator_mNum,2);
        bgColor=typedArray.getColor(R.styleable.Indicator_bgColor,Color.RED);
        foreColor=typedArray.getColor(R.styleable.Indicator_foreColor,Color.BLUE);


        mForePaint=new Paint();
        mForePaint.setAntiAlias(true);
        mForePaint.setStyle(Paint.Style.FILL);
        mForePaint.setColor(foreColor);
        mForePaint.setStrokeWidth(2);

        mBgPaint=new Paint();
        mBgPaint.setAntiAlias(true);
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setColor(bgColor);
        mBgPaint.setStrokeWidth(2);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Indicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i=0;i<mNum;i++){
            canvas.drawCircle(60+i*radius*3,60,radius,mBgPaint);
        }
        canvas.drawCircle(60+ offset,60,radius, mForePaint);
    }
}
