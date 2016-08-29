package com.xhb.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xhb.R;

/**
 * 生命周期
 * 1.oncreate
 * 2.oncreateView
 * 3.onPused
 */

public class TestFragment extends Fragment {


    private String mName;
    private int mNum;

    public static TestFragment newInstance(String name, int num){
       TestFragment testFragment=new TestFragment();
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        bundle.putInt("num",num);
        testFragment.setArguments(bundle);
        return  testFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null){
            mName= bundle.getString("name");
            mNum= bundle.getInt("num");
        }
 }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.list_item,container,false);
        TextView tv= (TextView) view.findViewById(R.id.item_text);
        if(mName!=null&&mNum!=0)
        tv.setText(mName+"----"+mNum);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
