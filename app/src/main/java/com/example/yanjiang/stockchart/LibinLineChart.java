package com.example.yanjiang.stockchart;

import android.content.Context;
import android.util.AttributeSet;

import com.example.yanjiang.stockchart.bean.DataParse;
import com.example.yanjiang.stockchart.mychart.MyBottomMarkerView;
import com.example.yanjiang.stockchart.mychart.MyLeftMarkerView;
import com.example.yanjiang.stockchart.mychart.MyRightMarkerView;
import com.example.yanjiang.stockchart.mychart.MyXAxis;
import com.example.yanjiang.stockchart.mychart.MyXAxisRenderer;
import com.example.yanjiang.stockchart.mychart.MyYAxis;
import com.example.yanjiang.stockchart.mychart.MyYAxisRenderer;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.highlight.Highlight;

/**
 * 作者：ajiang
 * 邮箱：1025065158
 * 博客：http://blog.csdn.net/qqyanjiang
 */
public class LibinLineChart extends LineChart {
    private MyLeftMarkerView myMarkerViewLeft;
    private MyRightMarkerView myMarkerViewRight;
    private MyBottomMarkerView mMyBottomMarkerView;
    private DataParse minuteHelper;

    public LibinLineChart(Context context) {
        super(context);
    }

    public LibinLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LibinLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        /*此两处不能重新示例*/
        mXAxis = new MyXAxis();
        mAxisLeft = new MyYAxis(YAxis.AxisDependency.LEFT);
        mXAxisRenderer = new MyXAxisRenderer(mViewPortHandler, (MyXAxis) mXAxis, mLeftAxisTransformer, this);
        mAxisRendererLeft = new MyYAxisRenderer(mViewPortHandler, (MyYAxis) mAxisLeft, mLeftAxisTransformer);

        mAxisRight = new MyYAxis(YAxis.AxisDependency.RIGHT);
        mAxisRendererRight = new MyYAxisRenderer(mViewPortHandler, (MyYAxis) mAxisRight, mRightAxisTransformer);

    }

    /*返回转型后的左右轴*/
    @Override
    public MyYAxis getAxisLeft() {
        return (MyYAxis) super.getAxisLeft();
    }

    @Override
    public MyXAxis getXAxis() {
        return (MyXAxis) super.getXAxis();
    }


    @Override
    public MyYAxis getAxisRight() {
        return (MyYAxis) super.getAxisRight();
    }

    public void setMarker(MyLeftMarkerView markerLeft, MyRightMarkerView markerRight,MyBottomMarkerView markerBottom, DataParse minuteHelper) {
        this.myMarkerViewLeft = markerLeft;
        this.myMarkerViewRight = markerRight;
        this.mMyBottomMarkerView=markerBottom;
        this.minuteHelper = minuteHelper;
    }

    public void setHighlightValue(Highlight h) {
        if (mData == null)
            mIndicesToHighlight = null;
        else {
            mIndicesToHighlight = new Highlight[]{
                    h};
        }
        invalidate();
    }

}
