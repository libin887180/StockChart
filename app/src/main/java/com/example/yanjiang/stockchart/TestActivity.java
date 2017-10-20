package com.example.yanjiang.stockchart;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.yanjiang.stockchart.api.ConstantTest;
import com.example.yanjiang.stockchart.bean.DataParse;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by libin on 2017/10/20.
 */

public class TestActivity extends Activity {

    @Bind(R.id.line_chart)
    LineChart lineChart;
    private XAxis xAxis;
    private YAxis axisLeft;
    private YAxis axisRight;
    private DataParse mData;
    private TestDataParse mData1;
    private LineDataSet d1, d2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        initChart();
        /*离线测试数据*/
        getOffLineData();
    }

    private void initChart() {
        lineChart.setScaleEnabled(false);
        lineChart.setDrawBorders(true);
        lineChart.setBorderWidth(1);
        lineChart.setBorderColor(getResources().getColor(R.color.minute_zhoutv));
        lineChart.setDescription("");
        Legend lineChartLegend = lineChart.getLegend();
        lineChartLegend.setEnabled(false);

        //x轴
        xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setLabelsToSkip(59);


        //左边y
        axisLeft = lineChart.getAxisLeft();
        axisLeft.setLabelCount(5, true);
        axisLeft.setDrawLabels(true);

        //y轴样式
        this.axisLeft.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                DecimalFormat mFormat = new DecimalFormat("#0.00");
                return mFormat.format(value);
            }
        });
        //右边y
        this.axisRight = lineChart.getAxisRight();
        this.axisRight.setLabelCount(5, true);
        this.axisRight.setDrawLabels(true);
        this.axisRight.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                DecimalFormat mFormat = new DecimalFormat("#0.00%");
                return mFormat.format(value);
            }
        });

        this.axisRight.setStartAtZero(false);
        this.axisRight.setDrawGridLines(false);
        this.axisRight.setDrawAxisLine(false);
        //背景线
        this.xAxis.setGridColor(getResources().getColor(R.color.minute_zhoutv));
        this.xAxis.setAxisLineColor(getResources().getColor(R.color.minute_zhoutv));
        this.axisLeft.setGridColor(getResources().getColor(R.color.minute_zhoutv));
        this.axisRight.setAxisLineColor(getResources().getColor(R.color.minute_zhoutv));

    }

    private void getOffLineData() {

        TestResponse response = new Gson().fromJson(ConstantTest.TEST, TestResponse.class);
        List<TestData> body = response.getBody();
        mData1.getDatas().addAll(body);
        setData(mData1);
    }


    private void setData(TestDataParse mData) {
        if (mData.getDatas().size() == 0) {
            lineChart.setNoDataText("暂无数据");
            return;
        }
        //设置y左右两轴最大最小值
        axisLeft.setAxisMinValue(mData.getMin());
        axisLeft.setAxisMaxValue(mData.getMax());
        axisRight.setAxisMinValue(mData.getPercentMin());
        axisRight.setAxisMaxValue(mData.getPercentMax());


        //基准线
        LimitLine ll = new LimitLine(0);
        ll.setLineWidth(1f);
        ll.setLineColor(Color.RED);
        ll.enableDashedLine(10f, 10f, 0f);
        ll.setLineWidth(1);
        axisLeft.addLimitLine(ll);


        ArrayList<Entry> lineCJEntries = new ArrayList<Entry>();
        ArrayList<String> dateList = new ArrayList<String>();
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < mData.getDatas().size(); i++) {
            //避免数据重复，skip也能正常显示
            if (mData.getDatas().get(i).getTime().equals("13:30")) {
                continue;
            }
            lineCJEntries.add(new Entry(mData.getDatas().get(i).get, i));
            dateList.add(mData.getDatas().get(i).getTime());
        }
        d1 = new LineDataSet(lineCJEntries, "成交价");
        d2 = new LineDataSet(lineJJEntries, "均价");

        d1.setCircleRadius(0);
        d2.setCircleRadius(0);
        d1.setColor(Color.BLUE);
        d2.setColor(Color.RED);
        d1.setHighLightColor(Color.BLACK);
        d2.setHighlightEnabled(false);
        d1.setDrawFilled(true);

        //谁为基准
        d1.setAxisDependency(YAxis.AxisDependency.LEFT);
        // d2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);
        sets.add(d2);
        LineData cd = new LineData(dateList, sets);
        lineChart.setData(cd);

        lineChart.invalidate();//刷新图
    }
}
