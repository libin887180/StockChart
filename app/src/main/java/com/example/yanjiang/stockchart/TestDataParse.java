package com.example.yanjiang.stockchart;

import android.util.SparseArray;

import java.util.ArrayList;

public class TestDataParse {
    private ArrayList<TestData> datas = new ArrayList<>();
    private float baseValue;
    private float permaxmin;
    private float volmax;
    private SparseArray<String> dayLabels;
    private String code = "sz002081";
    private SparseArray<String> xValuesLabel = new SparseArray<>();

    public void parseData() {
        if (datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                double cha = datas.get(i).getEstnav() - baseValue;
                if(i==0){
                    baseValue = (float) datas.get(i).getEstnav();
                }else {
                    if (Math.abs(cha) > permaxmin) {
                        permaxmin = (float) Math.abs(cha);
                    }
                    volmax = Math.max((float) datas.get(i).getEstnav(), volmax);

                }
            }
        }

    }

    public float getMin() {
//        return baseValue - (permaxmin+0.05f);
        return baseValue - (permaxmin);
    }

    public float getMax() {
//        return baseValue + (permaxmin+0.05f);
        return baseValue + (permaxmin);
    }

    public float getPercentMax() {
        return permaxmin / baseValue;
    }

    public float getPercentMin() {
        return -getPercentMax();
    }


    public float getVolmax() {
        return volmax;
    }


    public ArrayList<TestData> getDatas() {
        return datas;
    }

    public SparseArray<String> getXValuesLabel() {
        return xValuesLabel;
    }
}
