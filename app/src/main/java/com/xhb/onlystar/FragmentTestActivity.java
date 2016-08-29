package com.xhb.onlystar;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.xhb.R;
import com.xhb.fragment.TestFragment;

public class FragmentTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        FragmentManager manager=getFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        TestFragment testFragment=TestFragment.newInstance("徐辉波",10);
        transaction.add(R.id.add_fragment,testFragment).commit();
        //TextView tv=(TextView)findViewById(R.id.item_text);
       // tv.setText("to be or not to be");

        //transaction.remove(testFragment).commit();
        Fragment fragment=manager.findFragmentById(R.id.fragment_one);
        if(fragment instanceof TestFragment){
            Toast.makeText(this, "true", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show();
        }
    }
}
