package com.xhb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xhb.R;

import java.util.List;

/**
* Created by onlystar on 2016/8/22.
*/
public class ListViewAdapter extends BaseAdapter {

   private ListViewAdapter.ViewHolder holder;
   private List<String> list;
   private Context context;

   public ListViewAdapter(Context context, List<String> list) {
       this.list = list;
       this.context = context;
   }

   @Override
   public int getCount() {
       return list.size();
   }

   @Override
   public Object getItem(int position) {
       return null;
   }

   @Override
   public long getItemId(int position) {
       return 0;
   }

   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null) {
           holder = new ListViewAdapter.ViewHolder();
           convertView = LayoutInflater.from(context).inflate(
                   R.layout.list_item, null);
           holder.text = (TextView) convertView.findViewById(R.id.item_text);
           convertView.setTag(holder);
       } else {
           holder = (ListViewAdapter.ViewHolder) convertView.getTag();
       }
       holder.text.setText(list.get(position));
       return convertView;
   }

   private static class ViewHolder {
       TextView text;
   }

}
