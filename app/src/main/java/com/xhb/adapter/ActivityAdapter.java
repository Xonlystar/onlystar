package com.xhb.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhb.R;
import com.xhb.bean.ActivityListInfo;
import com.xhb.onlystar.MainActivity;

import java.util.List;

/**
 * Created by onlystar on 2016/8/22.
 */
public class ActivityAdapter extends BaseAdapter {

    private Context mContext;
    private List<ActivityListInfo> mInfos;

    public ActivityAdapter(Context context, List<ActivityListInfo> infos) {
        this.mContext = context;
        this.mInfos = infos;
        Log.i("myLog", "infos:" + infos);
    }

    @Override
    public int getCount() {
        return mInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            holder = new Holder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.item_text);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.item_img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.mTextView.setText(mInfos.get(position).getName());
        holder.mTextView.setSingleLine();
        holder.mImageView.setVisibility(View.GONE);
        return convertView;
    }

    public class Holder {
        TextView mTextView;
        ImageView mImageView;
    }
}
