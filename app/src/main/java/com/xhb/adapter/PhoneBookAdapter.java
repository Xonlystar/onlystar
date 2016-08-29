package com.xhb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xhb.R;
import com.xhb.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 * Create date on 15/11/4.
 *
 * @author Conquer
 * @version 1.0
 */
public class PhoneBookAdapter extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private List<UserInfo> mUserInfos = new ArrayList<>();

    public PhoneBookAdapter(Context context, List<UserInfo> userInfos) {
        mContext = context;
        mUserInfos = userInfos;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // 有多少条数据
        return mUserInfos.size();
    }

    @Override
    public Object getItem(int position) {
        // 返回某一条数据对象
        return mUserInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {


        return super.getItemViewType(position);
    }

    /**
     * 每一行数据显示在界面，用户能够看到时
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 返回一个视图

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_phone_book_friend, null);
            viewHolder = new ViewHolder();
            // 获取控件
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.name_text_view);
            viewHolder.ageTextView = (TextView) convertView.findViewById(R.id.age_text_view);
            viewHolder.avatarImageView = (ImageView) convertView.findViewById(R.id.avatar_image_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // 和数据之间进行绑定
        viewHolder.nameTextView.setText(mUserInfos.get(position).getUserName());
        viewHolder.ageTextView.setText(String.valueOf(mUserInfos.get(position).getAge()));
        viewHolder.avatarImageView.setImageResource(R.mipmap.ic_launcher);

        return convertView;

    }


    class ViewHolder {
        TextView nameTextView;
        TextView ageTextView;
        ImageView avatarImageView;
    }


    /**
     * 刷新数据
     *
     * @param userInfos
     */
    public void refreshData(List<UserInfo> userInfos) {
        mUserInfos = userInfos;
    }
}
