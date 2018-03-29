package com.xyc.guguviews.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyc.guguviews.R;

import java.util.List;

/**
 * Created by hasee on 2018/2/1.
 */

public class SlideAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<SlideItem> mSlideItems;


    public SlideAdapter(Context context, List<SlideItem> slideItems) {
        mContext = context;
        mSlideItems = slideItems;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mSlideItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mSlideItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.slide_menu_item, null);
            viewHolder.itemName = ((TextView) convertView.findViewById(R.id.itemName));
            viewHolder.tvRedPoint = ((TextView) convertView.findViewById(R.id.tvRedPoint));
            viewHolder.itemIcon = ((ImageView) convertView.findViewById(R.id.itemIcon));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SlideItem slideItem = mSlideItems.get(position);
        if (slideItem != null) {
            viewHolder.itemName.setText(slideItem.getTitle());
            if (slideItem.getIconId() != 0) {
                viewHolder.itemIcon.setVisibility(View.VISIBLE);
                viewHolder.itemIcon.setImageResource(slideItem.getIconId());
            } else {
                viewHolder.itemIcon.setVisibility(View.GONE);
            }
            if (slideItem.isShowRedPoint()) {
                viewHolder.tvRedPoint.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tvRedPoint.setVisibility(View.GONE);
            }
        }
        return convertView;
    }

    private class ViewHolder {
        TextView itemName;
        ImageView itemIcon;
        TextView tvRedPoint;
    }
}
