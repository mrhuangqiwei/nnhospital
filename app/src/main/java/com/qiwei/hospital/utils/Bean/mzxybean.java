package com.qiwei.hospital.utils.Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/21.
 */
public class mzxybean {
    public String getLczd() {
        return lczd;
    }

    public String getBrfb() {
        return brfb;
    }

    public void setBrfb(String brfb) {
        this.brfb = brfb;
    }

    public ArrayList<String> getListdata() {
        return listdata;
    }

    public void setListdata(ArrayList<String> listdata) {
        this.listdata = listdata;
    }

    public void setLczd(String lczd) {
        this.lczd = lczd;
    }

    private  String lczd;
    private String brfb;
    private ArrayList<String> listdata=new ArrayList<String>();
    public  mzxybean(){

    }
    public mzxybean(String lczd,String brfb,ArrayList<String> Listdata){
        this.lczd=lczd;
        this.brfb=brfb;
        this.listdata=Listdata;
    }
}
