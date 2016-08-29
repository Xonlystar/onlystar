package com.xhb.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhb.R;

/**
 * Created by onlystar on 2016/8/1.
 */

public class MyProgressDialog extends Dialog {

    private ImageView loadingImg;
    private TextView loadingMsg;

    public MyProgressDialog(Context context) {
        this(context, R.style.my_progress_dialog);
    }

    public MyProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context, themeResId);
    }

    private void init(Context context, int themeResId) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.my_progress_dialog_item,null,false);
        loadingImg= (ImageView) view.findViewById(R.id.loadingImg);
        loadingMsg= (TextView) view.findViewById(R.id.loadingMsg);

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.my_progress_dialog);
        loadingImg.setAnimation(animation);
        setContentView(view);
    }


    public void setMsg(CharSequence msg){
        this.loadingMsg.setText(msg);

    }
}
