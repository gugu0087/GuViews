package com.xyc.guviews;

import android.content.Context;
import android.widget.TextView;

import com.xyc.guguviews.views.ComBaseAdapter;
import com.xyc.guguviews.views.GuViewHolder;

import java.util.List;

/**
 * Created by hasee on 2018/3/17.
 */

public class TestAdapter extends ComBaseAdapter<User> {
    private List<User> datas;

    public TestAdapter(List<User> datas) {
        super(datas);
        this.datas = datas;
    }

    @Override
    protected void setUI(GuViewHolder holder, int position, Context context) {
        TextView name = holder.getItemView(R.id.name);
        TextView age = holder.getItemView(R.id.age);
        User user = datas.get(position);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_layout;
    }
}
