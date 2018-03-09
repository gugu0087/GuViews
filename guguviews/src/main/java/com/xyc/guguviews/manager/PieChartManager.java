package com.xyc.guguviews.manager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

/**
 * Created by hasee on 2018/3/7.
 */

public class PieChartManager {
    private PieChart pieChart;

    public PieChartManager(PieChart pieChart) {
        this.pieChart = pieChart;

    }

    private void initPieChart() {
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setNoDataText("没数据");
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);// 设置pieChart图表展示动画效果
    }

    public void showPieChartView(List<PieEntry> pieEntryList, List<Integer> colorIds, String label) {
        initPieChart();
        if (pieEntryList == null) {
            return;
        }
        PieDataSet pieDataSet = new PieDataSet(pieEntryList, label);
        if (colorIds != null) {
            pieDataSet.setColors(colorIds);
        }
        pieDataSet.setDrawValues(false);
        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);
        pieData.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                int i = (int) value;
                return i + "";
            }
        });
        pieChart.setData(pieData);
        pieChart.highlightValues(null);
        pieChart.setDrawEntryLabels(false);

        pieChart.getDescription().setEnabled(false);
        //刷新
        pieChart.invalidate();
    }

    /**
     * set center content
     *
     * @param centerText
     */
    public void setPieChartCenterText(String centerText) {
        pieChart.setCenterText(centerText);
    }
}
