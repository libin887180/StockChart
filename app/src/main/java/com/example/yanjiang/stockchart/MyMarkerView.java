package com.example.yanjiang.stockchart;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.ArrayList;

/**
 * Created by libin on 2017/10/23.
 */

public class MyMarkerView extends MarkerView {
    private TextView markerTv;
    ArrayList<String> dateList;
    public MyMarkerView(Context context, int layoutResource, ArrayList<String> dateList ) {
        super(context, layoutResource);
        markerTv = (TextView) findViewById(R.id.marker_tv);
        this.dateList = dateList;
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        markerTv.setText(e.getVal()+"   "+dateList.get(e.getXIndex()));
    }

    @Override
    public int getXOffset(float xpos) {
        return 30;
    }

    @Override
    public int getYOffset(float ypos) {
        return -100;
    }
}
