package com.xhb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhb.R;
import com.xhb.onlystar.MainActivity;

/**
 * Created by onlystar on 2016/8/22.
 */
public class MyListAdapter extends BaseAdapter {
    private static final String TAG = "myLog";
    private Context context;
    private LayoutInflater mInflater;
    private String[] text;

    public MyListAdapter(Context context, String[] text) {
        this.context = context;
        this.text = text;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int i) {
        return text[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.img = (ImageView) convertView.findViewById(R.id.item_img);
            viewHolder.text = (TextView) convertView.findViewById(R.id.item_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(text[i]);
        return convertView;
    }

    static class ViewHolder {
        ImageView img;
        TextView text;
    }
}
