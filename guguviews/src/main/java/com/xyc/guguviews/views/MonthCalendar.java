package com.xyc.guguviews.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.xyc.guguviews.R;
import com.xyc.guguviews.delegate.IMonthCalendarClickListener;
import com.xyc.guguviews.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gugu on 2018/1/7.
 */

public class MonthCalendar extends LinearLayout implements View.OnClickListener {
    private LinearLayout llJanuary;
    private LinearLayout llFebruary;
    private LinearLayout llMarch;
    private LinearLayout llApril;
    private LinearLayout llMay;
    private LinearLayout llJune;
    private LinearLayout llJuly;
    private LinearLayout llAugust;
    private LinearLayout llSeptember;
    private LinearLayout llOctober;
    private LinearLayout llNovember;
    private LinearLayout llDecember;
    private ImageView ivLeftArrow;
    private ImageView ivRightArrow;
    private TextView tvYear;
    private int curYear;
    private int month;
    private List<LinearLayout> mLinearLayoutList;

    public MonthCalendar(Context context) {
        super(context);
    }

    private IMonthCalendarClickListener mListener;

    public MonthCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.calendar_view_layout, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        initData();
    }

    public void setOnMonthClickListener(IMonthCalendarClickListener listener) {
        mListener = listener;
    }

    private void initData() {
        curYear = DateUtils.getInstance().getCurrentYear();
        month = DateUtils.getInstance().getCurrentMonth();
        tvYear.setText(String.valueOf(curYear));
        initDaySelect();
        setDaySelect(month - 1);
    }

    public int getCurYear() {
        return curYear;
    }

    public int getMonth() {
        return month;
    }

    private void initView() {
        ivLeftArrow = (ImageView) findViewById(R.id.ivLeftArrow);
        ivRightArrow = (ImageView) findViewById(R.id.ivRightArrow);
        tvYear = (TextView) findViewById(R.id.tvYear);

        llJanuary = (LinearLayout) findViewById(R.id.llJanuary);
        llFebruary = (LinearLayout) findViewById(R.id.llFebruary);
        llMarch = (LinearLayout) findViewById(R.id.llMarch);
        llApril = (LinearLayout) findViewById(R.id.llApril);
        llMay = (LinearLayout) findViewById(R.id.llMay);
        llJune = (LinearLayout) findViewById(R.id.llJune);
        llJuly = (LinearLayout) findViewById(R.id.llJuly);
        llAugust = (LinearLayout) findViewById(R.id.llAugust);
        llSeptember = (LinearLayout) findViewById(R.id.llSeptember);
        llOctober = (LinearLayout) findViewById(R.id.llOctober);
        llNovember = (LinearLayout) findViewById(R.id.llNovember);
        llDecember = (LinearLayout) findViewById(R.id.llDecember);

        ivLeftArrow.setOnClickListener(this);
        ivRightArrow.setOnClickListener(this);
        llJanuary.setOnClickListener(this);
        llFebruary.setOnClickListener(this);
        llMarch.setOnClickListener(this);
        llApril.setOnClickListener(this);
        llMay.setOnClickListener(this);
        llJune.setOnClickListener(this);
        llJuly.setOnClickListener(this);
        llAugust.setOnClickListener(this);
        llSeptember.setOnClickListener(this);
        llOctober.setOnClickListener(this);
        llNovember.setOnClickListener(this);
        llDecember.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.llJanuary) {
            month = 1;

        } else if (i == R.id.llFebruary) {
            month = 2;

        } else if (i == R.id.llMarch) {
            month = 3;

        } else if (i == R.id.llApril) {
            month = 4;

        } else if (i == R.id.llMay) {
            month = 5;

        } else if (i == R.id.llJune) {
            month = 6;

        } else if (i == R.id.llJuly) {
            month = 7;

        } else if (i == R.id.llAugust) {
            month = 8;

        } else if (i == R.id.llSeptember) {
            month = 9;

        } else if (i == R.id.llOctober) {
            month = 10;

        } else if (i == R.id.llNovember) {
            month = 11;

        } else if (i == R.id.llDecember) {
            month = 12;

        } else if (i == R.id.ivLeftArrow) {
            if (curYear <= 2000) { // 年份小于2000年就不再翻页了
                return;
            }
            curYear = curYear - 1;
            tvYear.setText(String.valueOf(curYear));

        } else if (i == R.id.ivRightArrow) {
            curYear = curYear + 1;
            tvYear.setText(String.valueOf(curYear));

        } else {
        }
        if(mListener!=null){
            mListener.getTime(curYear,month);
        }
        setDaySelect(month - 1);
    }

    public void resetTime() {////重置按钮，默认回到当前年月
        curYear = DateUtils.getInstance().getCurrentYear();
        month = DateUtils.getInstance().getCurrentMonth();
        tvYear.setText(String.valueOf(curYear));
        setDaySelect(month - 1);
        if(mListener!=null){
            mListener.getTime(curYear,month);
        }
    }

    private void setDaySelect(int position) {
        if (mLinearLayoutList == null || mLinearLayoutList.size() == 0) {
            return;
        }
        for (int i = 0; i < mLinearLayoutList.size(); i++) {
            if (position == i) {
                mLinearLayoutList.get(position).setSelected(true);
            } else {
                mLinearLayoutList.get(i).setSelected(false);
            }
        }
    }

    private void initDaySelect() {
        mLinearLayoutList = new ArrayList<>();
        mLinearLayoutList.add(llJanuary);
        mLinearLayoutList.add(llFebruary);
        mLinearLayoutList.add(llMarch);
        mLinearLayoutList.add(llApril);
        mLinearLayoutList.add(llMay);
        mLinearLayoutList.add(llJune);
        mLinearLayoutList.add(llJuly);
        mLinearLayoutList.add(llAugust);
        mLinearLayoutList.add(llSeptember);
        mLinearLayoutList.add(llOctober);
        mLinearLayoutList.add(llNovember);
        mLinearLayoutList.add(llDecember);

    }

}
