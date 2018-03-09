package com.xyc.guguviews.manager;


import android.graphics.Color;
import android.graphics.Matrix;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2018/3/7.
 */

public class BarChartManager {
    private BarChart mBarChart;
    private YAxis leftAxis;
    private YAxis rightAxis;
    private XAxis xAxis;
    private int defaultColor;
    private float mRefenceData;

    public BarChartManager(BarChart barChart) {
        this.mBarChart = barChart;
        leftAxis = mBarChart.getAxisLeft();
        rightAxis = mBarChart.getAxisRight();
        xAxis = mBarChart.getXAxis();
    }

    /**
     * When the number of columns exceeds a certain number the charts can move
     * @param valuesSize
     */
    public void setMoveCharts(int valuesSize) {
        Matrix m = new Matrix();
        m.postScale(getScalePercent(valuesSize), 1f);//两个参数分别是x,y轴的缩放比例。例如：将x轴的数据放大为之前的1.5倍
        mBarChart.getViewPortHandler().refresh(m, mBarChart, false);//将图表动画显示之前进行缩放
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        //背景颜色
        mBarChart.setBackgroundColor(Color.WHITE);
        //网格
        mBarChart.setDrawGridBackground(false);
        //背景阴影
        mBarChart.setDrawBarShadow(false);
        mBarChart.setHighlightFullBarEnabled(false);
        mBarChart.setNoDataText("如果传给MPAndroidChart的数据为空，那么你将看到这段文字。CrazyBoy");

        //显示边界
        mBarChart.setDrawBorders(true);

        //设置动画效果
        mBarChart.animateY(1000, Easing.EasingOption.Linear);
        mBarChart.animateX(1000, Easing.EasingOption.Linear);
        //折线图例 标签 设置
        Legend legend = mBarChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(11f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setForm(Legend.LegendForm.NONE);
        //XY轴的设置
        //X轴设置显示位置在底部
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);

        xAxis.setAxisMinimum(0.5f);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
        rightAxis.setAxisMinimum(0f);
    }

    /**
     * Get the color of the substandard pillar
     *
     * @param yAxisValues
     * @param color
     * @return
     */
    private List<Integer> getNoReachLevelBarColor(List<Float> yAxisValues, int color) {
        if (yAxisValues == null || color == 0) {
            return null;
        }
        List<Integer> colorList = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            if (yAxisValues.get(i) < mRefenceData) {
                colorList.add(defaultColor);
            } else {
                colorList.add(color);
            }
        }
        return colorList;
    }

    /**
     * Complement the data
     *
     * @param xAxisValues
     * @param yAxisValues
     * @return
     */
    private List<BarEntry> getCompleteData(List<Float> xAxisValues, List<Float> yAxisValues) {
        List<BarEntry> entries = new ArrayList<>();

        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new BarEntry(xAxisValues.get(i), yAxisValues.get(i)));
        }
        if (xAxisValues.size() < 7) {
            for (int i = xAxisValues.size() + 1; i < 8; i++) {
                entries.add(new BarEntry((float) i, 0));
            }
        }
        return entries;
    }

    /**
     * 展示柱状图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showBarChart(List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        if (xAxisValues == null || yAxisValues == null) {
            return;
        }
        initLineChart();
        List<BarEntry> entries = getCompleteData(xAxisValues, yAxisValues);
        List<Integer> colorList = getNoReachLevelBarColor(yAxisValues, color);
        // 每一个BarDataSet代表一类柱状图
        BarDataSet barDataSet = new BarDataSet(entries, label);
        if (colorList != null) {
            barDataSet.setColors(colorList);
        } else {
            barDataSet.setColor(color);
        }
        barDataSet.setValueTextSize(9f);
        barDataSet.setFormLineWidth(1f);
        barDataSet.setFormSize(10.f);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(barDataSet);
        BarData data = new BarData(dataSets);

        //设置X轴的刻度数
        data.setBarWidth(0.5f); // set the space size between the pillar of space

        xAxis.setLabelCount(xAxisValues.size(), false);

        mBarChart.setDescription(null);
        mBarChart.setData(data);

    }

    /**
     * Set the color of the substandard pillar
     *
     * @param colorId
     */
    public void setNoReachLevelColor(int colorId) {
        this.defaultColor = colorId;
    }

    /**
     * value's size
     * 6.98  Do not ask why,this is a golden ratio
     *
     * @param size
     * @return
     */
    public float getScalePercent(int size) {
        return (float) (size / (6.98));
    }

    /**
     * show  Multiple pillar
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param labels
     * @param colours
     */
    public void showBarChart(List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        BarData data = new BarData();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {

                entries.add(new BarEntry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            BarDataSet barDataSet = new BarDataSet(entries, labels.get(i));

            barDataSet.setColor(colours.get(i));
            barDataSet.setValueTextColor(colours.get(i));
            barDataSet.setValueTextSize(10f);
            barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
            data.addDataSet(barDataSet);
        }
        int amount = yAxisValues.size();

        float groupSpace = 0.12f; //柱状图组之间的间距
        float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
        float barWidth = (float) ((1 - 0.12) / amount / 10 * 9); // x4 DataSet

        // (0.2 + 0.02) * 4 + 0.08 = 1.00 -> interval per "group"
        xAxis.setLabelCount(xAxisValues.size() - 1, false);
        data.setBarWidth(barWidth);
        data.groupBars(0, groupSpace, barSpace);
        mBarChart.setData(data);
    }


    /**
     * set the max and min values of hte yAxis
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        mBarChart.invalidate();
    }

    /**
     * set the max and min values of hte xAxis
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, false);

        mBarChart.invalidate();
    }

    /**
     * set the High limit line
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        mRefenceData = high;
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * set the Low limit line
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        mRefenceData = low;
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(1f);
        hightLimit.enableDashedLine(5, 3, 0);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        mBarChart.invalidate();
    }

    /**
     * set the description of  charts
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        mBarChart.setDescription(description);
        mBarChart.invalidate();
    }
}
