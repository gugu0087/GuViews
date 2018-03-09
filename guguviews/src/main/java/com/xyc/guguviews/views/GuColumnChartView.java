package com.xyc.guguviews.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.xyc.guguviews.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.ColumnChartView;

/**
 * Created by hasee on 2018/2/2.
 */

public class GuColumnChartView extends LinearLayout {

    private ColumnChartView columnChartView;

    public GuColumnChartView(Context context) {
        super(context);
    }

    public GuColumnChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.column_chart_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        columnChartView = (ColumnChartView) findViewById(R.id.columnChartView);

    }

    public void setColumnChartData(List<String> xValuesList, List<Integer> valuesList) {
        generateData(xValuesList, valuesList);
    }

    private void generateData(List<String> xValuesList, List<Integer> valuesList) {
        //每个集合显示几条柱子
        int numSubcolumns = 2;
        //显示多少个集合
        int numColumns = valuesList.size();
        //保存所有的柱子
        List<Column> columns = new ArrayList<>();
        //保存每个竹子的值
        List<SubcolumnValue> values;
        List<AxisValue> axisXValues = new ArrayList<>();
        //对每个集合的柱子进行遍历
        for (int i = 0; i < numColumns; ++i) {
            values = new ArrayList<>();
            //循环所有柱子（list）
            for (int j = 0; j < numSubcolumns; ++j) {
                //创建一个柱子，然后设置值和颜色，并添加到list中
                if (j == 0) {
                    SubcolumnValue subcolumnValue = new SubcolumnValue(valuesList.get(i), Color.parseColor("#ff6600"));
                    values.add(subcolumnValue);
                } else {
                    values.add(new SubcolumnValue(valuesList.get(i), Color.parseColor("#303F9F")));

                }
                //设置X轴的柱子所对应的属性名称
                axisXValues.add(new AxisValue(i).setLabel(xValuesList.get(i)));
            }

            //将每个属性的拥有的柱子，添加到Column中
            Column column = new Column(values);
            //是否显示每个柱子的Lable
            column.setHasLabels(true);
            //设置每个柱子的Lable是否选中，为false，表示不用选中，一直显示在柱子上
            column.setHasLabelsOnlyForSelected(false);
            //将每个属性得列全部添加到List中
            columns.add(column);
        }

        //设置Columns添加到Data中
        ColumnChartData data = new ColumnChartData(columns);
        //设置X轴显示在底部，并且显示每个属性的Lable，字体颜色为黑色，X轴的名字为“学历”，每个柱子的Lable斜着显示，距离X轴的距离为8
        data.setAxisXBottom(new Axis(axisXValues).setHasLines(true).setTextColor(Color.BLACK).setHasTiltedLabels(true).setMaxLabelChars(8));
        //属性值含义同X轴
        //data.setAxisYLeft(new Axis().setHasLines(true).setName("人数").setTextColor(Color.BLACK).setMaxLabelChars(5));
        //最后将所有值显示在View中

        columnChartView.setColumnChartData(data);

        Viewport v = new Viewport(columnChartView.getMaximumViewport());
        v.left = -1;
        v.right = 5;
        v.bottom = 0;

        columnChartView.setCurrentViewport(v);

    }
}
