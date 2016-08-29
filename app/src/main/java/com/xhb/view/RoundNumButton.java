package com.xhb.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.xhb.R;


/**
 * unction
 * 1.做一个圆形的红色按钮
 * 2.中间有一个白色的数字，起始值为20
 * 2.每点击一次减少1
 */

public class RoundNumButton extends View implements View.OnClickListener{

    private Paint mPaint;
    private Rect mRect;
    private int num=20;
    private boolean isUp=false;
    private int mBackGroundColor;
    private int mTextColor;
    private int mTextSize;

    public RoundNumButton(Context context) {
        this(context,null);
    }

    public RoundNumButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundNumButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    private void init(Context context,AttributeSet attrs) {
         mPaint= new Paint();
         mRect= new Rect();
         this.setOnClickListener(this);

        //读取attrs.xml属性文件
        TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.RoundNumButton);
        mBackGroundColor=typedArray.getColor(R.styleable.RoundNumButton_backGroundColor,Color.BLACK);
        mTextColor=typedArray.getColor(R.styleable.RoundNumButton_textColor,Color.WHITE);
        mTextSize=(int)typedArray.getInteger(R.styleable.RoundNumButton_textSize,18);
        mTextSize=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, mTextSize, getResources().getDisplayMetrics());
        final float scale = context.getResources().getDisplayMetrics().density;
        mTextSize= (int) (mTextSize * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画圆
        mPaint.setColor(mBackGroundColor);
        canvas.drawCircle(getWidth() / 2,getWidth() / 2,getWidth()/2,mPaint);
        //画文字
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        String text=String.valueOf(num);
        //将文字的四周边距算出来放到Rect
        mPaint.getTextBounds(text,0,text.length(),mRect);
        canvas.drawText(text,getWidth() / 2-mRect.width()/2,getHeight()  / 2+mRect.height()/2,mPaint);

    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    @Override
    public void onClick(View view) {
       if(isUp){
           num++;
       }else{
           num--;
       }
        if(num==0){
            isUp=true;
        }
        if(num==20){
            isUp=false;
        }
        invalidate();
    }
}
