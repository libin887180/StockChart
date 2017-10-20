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
    private int decreasingColor;
    private int increasingColor;
    private String stockExchange;
    private SparseArray<String> xValuesLabel=new SparseArray<>();
    private int firstDay = 10;



    public float getMin() {
        return baseValue - permaxmin;
    }

    public float getMax() {
        return baseValue + permaxmin;
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
