package com.xyc.guguviews.views;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xyc.guguviews.R;

import java.util.List;

/**
 * Created by hasee on 2018/2/1.
 */

public class SlideMenuLayout extends LinearLayout implements AdapterView.OnItemClickListener, DrawerLayout.DrawerListener {
    private Context mContext;
    private ListView slideLv;
    private List<SlideItem> itemList;
    private AdapterView.OnItemClickListener mListener;
    private DrawerLayout.DrawerListener mLayoutListener;
    private LinearLayout llDrawer;
    private DrawerLayout drawerLayout;
    private LinearLayout llContentView;
    private SlideAdapter slideAdapter;

    public SlideMenuLayout(Context context) {
        super(context);
        mContext = context;
    }

    public SlideMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.slide_menu_layout, this);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initData() {
        if (itemList == null || itemList.size() == 0) {
            return;
        }
        slideAdapter = new SlideAdapter(mContext, itemList);
        slideLv.setAdapter(slideAdapter);
    }

    public void updateAdapter() {
        if (slideAdapter == null) {
            return;
        }
        slideAdapter.notifyDataSetChanged();
    }


    private void initView() {
        slideLv = (ListView) findViewById(R.id.slideLv);
        llDrawer = (LinearLayout) findViewById(R.id.llDrawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        llContentView = (LinearLayout) findViewById(R.id.llContentView);
        slideLv.setOnItemClickListener(this);
        drawerLayout.addDrawerListener(this);
    }

    public void setItemClickListener(AdapterView.OnItemClickListener listener) {
        mListener = listener;
    }

    public void setContentView(View contentView) {
        if (contentView != null) {
            llContentView.removeAllViews();
        }
        llContentView.addView(contentView);
    }

    public SlideItem getItemById(int itemId) {
        if (itemList == null) {
            return null;
        }
        for (int i = 0; i < itemList.size(); i++) {
            if (itemId == itemList.get(i).getItemId()) {
                return itemList.get(i);
            }
        }
        return null;
    }

    /**
     * 添加数据
     *
     * @return
     */
    public void addListData(List<SlideItem> slideItemList) {
        itemList = slideItemList;
        initData();
    }

    /**
     * 滑动监听事件
     *
     * @param layoutListener
     */
    public void setOnDrawerLayoutListener(DrawerLayout.DrawerListener layoutListener) {
        mLayoutListener = layoutListener;
    }

    public List<SlideItem> getItemList() {
        return itemList;
    }

    /**
     * 设置抽屉页面的背景颜色
     *
     * @param colorId
     */
    public void setSlideMenuBackGroundColor(int colorId) {
        if (colorId != 0 && llDrawer != null) {
            llDrawer.setBackgroundColor(mContext.getResources().getColor(colorId));
        }
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        if (mLayoutListener != null) {
            mLayoutListener.onDrawerSlide(drawerView, slideOffset);
        }
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        if (mLayoutListener != null) {
            mLayoutListener.onDrawerOpened(drawerView);
        }
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        if (mLayoutListener != null) {
            mLayoutListener.onDrawerClosed(drawerView);
        }
    }

    @Override
    public void onDrawerStateChanged(int newState) {
        if (mLayoutListener != null) {
            mLayoutListener.onDrawerStateChanged(newState);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null) {
            mListener.onItemClick(parent, view, position, id);
            //drawerLayout.closeDrawers();
        }
    }

    public void closeDrawerPage() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawers();
        }
    }

    public void openDrawerPage() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(Gravity.START, true);
        }
    }

}
