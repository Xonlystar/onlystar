package com.xhb.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.xhb.R;
/**
 * Created by onlystar on 2016/7/28.
 */

public class MyProgressBar extends View {

    private Paint paint;//画笔
    //圆环相关成员变量
    private int roundW;// 圆环宽
    private int roundColor;//圆环颜色

    //圆弧相关成员变量
    private int progress;// 当前进度值
    private int progressColor;//进度圆弧颜色
    private int maxProgress = 100;// 最大进度值

    //实心圆相关成员变量
    private int circleColor;//实心圆颜色

    //百分比文本相关成员变量
    private int textColor;//字体颜色
    private int textSize;// 字体大小

    public MyProgressBar(Context context) {
        this(context, null);
    }

    public MyProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    /**
     * 初始化方法
     */
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        paint = new Paint();
        //初始化圆环宽，这里考虑了适配把15dp进行了对应平台的像素转换。
        //roundW = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics());
        //textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 25, getResources().getDisplayMetrics());

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MyProgressBar);

        roundW = (int) typedArray.getDimension(R.styleable.MyProgressBar_roundW, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15, getResources().getDisplayMetrics()));
        roundColor = typedArray.getColor(R.styleable.MyProgressBar_roundColor, Color.parseColor("#11339ED4"));
        progress = typedArray.getInt(R.styleable.MyProgressBar_progress, 0);
        progressColor = typedArray.getColor(R.styleable.MyProgressBar_progressColor, Color.parseColor("#339ED4"));

        circleColor = typedArray.getColor(R.styleable.MyProgressBar_circleColor, Color.parseColor("#336799"));

        textColor = typedArray.getColor(R.styleable.MyProgressBar_textcolor, Color.parseColor("#ffffff"));
        textSize = (int) typedArray.getDimension(R.styleable.MyProgressBar_textsize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 25, getResources().getDisplayMetrics()));

        typedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制浅色圆环
        // 1.圆心（x,y）坐标值
        int centerX = getWidth() / 2;
        int centerY = centerX;
        // 2.圆环半径
        int radius1 = (centerX - roundW / 2);
        // 3.设置画大圆环颜色
        paint.setColor(roundColor);
        // 4.设置画笔的风格
        paint.setStyle(Paint.Style.STROKE);
        // 5.设置画圆环的宽度
        paint.setStrokeWidth(roundW);
        // 6.消除锯齿
        paint.setAntiAlias(true);
        // 7.画圆环
        canvas.drawCircle(centerX, centerY, radius1, paint);

       // 绘制深色进度圆弧
        // 1.设置圆孤的宽度
        paint.setStrokeWidth(roundW);
        // 2.设置圆孤进度的颜色
        paint.setColor(progressColor);
        // 3.定义圆弧的形状和大小区域界限
        RectF oval = new RectF(centerX - radius1, centerY - radius1, centerX + radius1, centerY + radius1);
        // 4.设置空心样式
        paint.setStyle(Paint.Style.STROKE);
        // 5.根据进度画圆弧
        canvas.drawArc(oval, 0, (float) 360 * progress / (float) maxProgress, false, paint);

         // 绘制深蓝色实心圆
        // 1.实心圆半径
        int radius2 = centerX - roundW;
        // 2.实心圆颜色
        paint.setColor(circleColor);
        // 3.设置画笔风格为实心
        paint.setStyle(Paint.Style.FILL);
        // 4.画实心圆
        canvas.drawCircle(centerX, centerY, radius2, paint);

        // 绘制百分比文本
        // 1.设置无边框
        paint.setStrokeWidth(0);
        // 2.设置字体颜色
        paint.setColor(textColor);
        paint.setAntiAlias(true);
        // 3.设置字体大小
        paint.setTextSize(textSize);
        // 4.设置字体
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        // 5.计算进度百分比
        int percent = (int) (((float) progress / (float) maxProgress) * 100);
        // 6.测量字体宽度
        float textWidth = paint.measureText(percent + "%");
        // 7.画出进度百分比文本
        canvas.drawText(percent + "%", centerX - textWidth / 2, centerY + textSize / 2, paint);
    }

    /**
     * 设置圆环宽度
     * @param roundW
     */
    public void setRoundW(int roundW) {
        this.roundW = roundW;
    }
    /**
     * 设置圆环颜色
     * @param roundColor
     */
    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }
    /**
     * 设置进度圆弧颜色
     * @param progressColor
     */
    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }
    /**
     * 设置最大进度值
     * @param maxProgress
     */
    public void setMaxProgress(int maxProgress) {
        this.maxProgress = maxProgress;
    }
    /**
     * 设置实心圆颜色
     * @param circleColor
     */
    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }
    /**
     * 设置百分比进度文本颜色
     * @param textColor
     */
    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
    /**
     * 设置百分比进度文本字体大小
     * @param textSize
     */
    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    /**
     * 更新进度和界面的方法
     *
     * @param progress
     */
    public void setProgress(int progress) {
        if (progress < 0) {
            progress = 0;
        } else {
            this.progress = progress;
        }
        invalidate();
    }
}
