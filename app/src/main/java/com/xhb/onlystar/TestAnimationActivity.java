package com.xhb.onlystar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.xhb.R;

public class TestAnimationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_alpha,btn_translate,btn_rotate,btn_scale,btn_set;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        btn_alpha= (Button) findViewById(R.id.btn_alpha);
        btn_translate= (Button) findViewById(R.id.btn_translate);
        btn_rotate= (Button) findViewById(R.id.btn_rotate);
        btn_scale= (Button) findViewById(R.id.btn_scale);
        btn_set= (Button) findViewById(R.id.btn_set);
        mImageView = (ImageView) findViewById(R.id.imageView1);

        btn_alpha.setOnClickListener(this);
        btn_translate.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_set.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_alpha:
                alphaAnim();
                break;
            case R.id.btn_translate:
                translateAnim();
                break;
            case R.id.btn_rotate:
                rotateAnim();
                break;
            case R.id.btn_scale:
                scaleAnim();
                break;
            case R.id.btn_set:
                setAnim();
                break;
        }
    }

    private void alphaAnim() {
        AlphaAnimation anim=new AlphaAnimation(0.1f,1.0f);
        anim.setDuration(5000);
        anim.setFillAfter(true);//动画结束是否停留在最后一帧
        anim.setFillBefore(false);
        mImageView.startAnimation(anim);
        //mImageView.setAnimation(anim);
    }

    private void translateAnim() {
        //TranslateAnimation anim=new TranslateAnimation(20,50,20,120);
        TranslateAnimation anim=new TranslateAnimation(Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0.5f,
                Animation.RELATIVE_TO_PARENT,0f,Animation.RELATIVE_TO_PARENT,0.5f);
        anim.setDuration(5000);
        anim.setRepeatCount(2);//执行3次默认有一次
        anim.setRepeatMode(Animation.REVERSE);//REVERSE从末尾倒播  RESTART从头开始
        mImageView.startAnimation(anim);
    }

    private void rotateAnim() {
        RotateAnimation anim= (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotateanim);
        mImageView.startAnimation(anim);
    }

    private void scaleAnim() {
       // Animation anim=new ScaleAnimation(0f,1f,0f,1f,Animation.RELATIVE_TO_PARENT,0.5f,Animation.RELATIVE_TO_PARENT,0.5f);
        Animation anim=new ScaleAnimation(0f,2f,0f,2f);
        anim.setDuration(5000);
        //anim.setFillBefore(true);
        //anim.setFillAfter(true);
        mImageView.startAnimation(anim);
    }


    public void setAnim(){
        Animation scaleAnimation=new ScaleAnimation(0f,1f,0f,1f);
        Animation alphaAnimation=new AlphaAnimation(0.1f,1.0f);
        AnimationSet animationSet=new AnimationSet(true);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(5000);
        mImageView.startAnimation(animationSet);
    }
}
