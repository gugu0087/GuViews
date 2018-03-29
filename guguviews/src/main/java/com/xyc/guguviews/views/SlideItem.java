package com.xyc.guguviews.views;

/**
 * Created by hasee on 2018/2/1.
 */

public class SlideItem {
    private String title;
    private int iconId;
    private int itemId;
    private boolean isShowRedPoint;

    public boolean isShowRedPoint() {
        return isShowRedPoint;
    }

    public void setShowRedPoint(boolean showRedPoint) {
        isShowRedPoint = showRedPoint;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

}
