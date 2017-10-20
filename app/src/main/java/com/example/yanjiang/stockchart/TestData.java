package com.example.yanjiang.stockchart;

/**
 * Created by libin on 2017/10/20.
 */

public class TestData {


    /**
     * estnav : 1.1001
     * estchngpct : 0.02
     * estchng : 2.0E-4
     * time : 20171017151100
     */

    private double estnav;
    private double estchngpct;
    private double estchng;
    private String time;

    public double getEstnav() {
        return estnav;
    }

    public void setEstnav(double estnav) {
        this.estnav = estnav;
    }

    public double getEstchngpct() {
        return estchngpct;
    }

    public void setEstchngpct(double estchngpct) {
        this.estchngpct = estchngpct;
    }

    public double getEstchng() {
        return estchng;
    }

    public void setEstchng(double estchng) {
        this.estchng = estchng;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
