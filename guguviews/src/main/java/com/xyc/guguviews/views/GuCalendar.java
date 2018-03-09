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
import com.xyc.guguviews.delegate.IGuCalendarDayListener;


import java.util.List;

/**
 * Created by gugu on 2018/1/10.
 */

public class GuCalendar extends LinearLayout {

    private ImageView iv_left;
    private ImageView iv_right;
    private MonthDateView monthDateView;
    private TextView tv_date;
    private TextView tv_today;
    private TextView tv_week;
    private IGuCalendarDayListener mListener;

    public GuCalendar(Context context) {
        super(context);
    }

    public GuCalendar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.gu_calendar_layout, this);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        iv_left = (ImageView) findViewById(R.id.iv_left);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
        tv_date = (TextView) findViewById(R.id.date_text);
        tv_week = (TextView) findViewById(R.id.week_text);
        tv_today = (TextView) findViewById(R.id.tv_today);
        monthDateView.setTextView(tv_date, tv_week);
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
                if(mListener==null){
                    return;
                }
                int month = monthDateView.getmSelMonth();
                int year = monthDateView.getmSelYear();
                int day = monthDateView.getmSelDay();
                mListener.onDayClick(year,month+1,day);// 月份默认拿到的比实际小1

               // Toast.makeText(getApplication(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
            }
        });

        setOnlistener();

    }
    public void setOnDayClickListener(IGuCalendarDayListener listener){
        mListener = listener;
    }

    public void setRemarkView(List<Integer> list) {
        if (monthDateView == null) {
            return;
        }
        monthDateView.setDaysHasThingList(list);
    }

    private void setOnlistener() {
        iv_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onLeftClick();
            }
        });

        iv_right.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onRightClick();
            }
        });

        tv_today.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.setTodayToView();
            }
        });
    }

}
