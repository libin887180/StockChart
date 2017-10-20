package com.example.yanjiang.stockchart.service;

import android.util.SparseArray;

/**
 * Created by libin on 2017/10/20.
 */

public class MXAxis {
    private SparseArray<String> labels;
    public SparseArray<String> getXLabels() {
        return labels;
    }
    public void setXLabels(SparseArray<String> labels) {
        this.labels = labels;
    }
}
