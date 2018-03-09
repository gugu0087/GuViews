package com.xyc.guguviews.views;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.xyc.guguviews.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by hasee on 2018/2/2.
 */

public class GuLineChartView extends LinearLayout {

    private Line line;
    private LineChartView lineChartView;
    private List<PointValue> mPointValues = new ArrayList<>();
    private List<AxisValue> mAxisXValues = new ArrayList<>();
    private Axis axisX;
    private int mVisibleCount = 5;

    public GuLineChartView(Context context) {
        super(context);
    }

    public GuLineChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.line_chart_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        lineChartView = (LineChartView) findViewById(R.id.lineChartView);
        initLineOptions();
        initAxisOptions();
        initChartViewOptions();
    }

    public void updateLineChartData(List<String> xValuesList, List<Float> valuesList) {
        getAxisXLables(xValuesList);
        getAxisPoints(valuesList);
        setChartData();
    }

    private void setChartData() {
        line.setValues(mPointValues);
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        lineChartView.setLineChartData(data);
        Viewport v = new Viewport(lineChartView.getMaximumViewport());
        v.left = 0;
        v.right = mVisibleCount;
        lineChartView.setCurrentViewport(v);
    }

    /**
     * 一次显示多少条数据
     * @param count
     */
    public void setVisibleCount(int count) {
        mVisibleCount = count;
    }

    /**
     * 设置X 轴的显示
     */
    private void getAxisXLables(List<String> xValuesList) {
        for (int i = 0; i < xValuesList.size(); i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(xValuesList.get(i)));
        }
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints(List<Float> valuesList) {
        for (int i = 0; i < valuesList.size(); i++) {
            mPointValues.add(new PointValue(i, valuesList.get(i)));
        }
    }

    /**
     * 初始化折线
     */
    private void initLineOptions() {
        line = new Line().setColor(Color.parseColor("#FFCD41"));
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        // line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
    }

    private void initAxisOptions() {
        //坐标轴
        //X轴
        axisX = new Axis();
        axisX.setHasTiltedLabels(true);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        //data.setAxisXTop(axisX);  //x 轴在顶部
        axisX.setHasLines(true); //x 轴分割线
    }

    private void initChartViewOptions() {
        //设置行为属性，支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL);

        lineChartView.setHorizontalScrollBarEnabled(true);
        lineChartView.setMaxZoom((float) 2);//最大方法比例
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        // lineChartView.setLineChartData(data);
        lineChartView.setVisibility(View.VISIBLE);

    }
}
