package com.qiwei.hospital.utils.Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/15.
 */
public class MzblBean {
    private  String lczd;
    private  String yf;
    private String zyfs;
    private ArrayList<String> listdata=new ArrayList<String>();
    public MzblBean(){

    }

    public ArrayList<String> getListdata() {
        return listdata;
    }

    public void setListdata(ArrayList<String> listdata) {
        this.listdata = listdata;
    }

    public String getZyfs() {

        return zyfs;
    }

    public void setZyfs(String zyfs) {
        this.zyfs = zyfs;
    }

    public String getYf() {

        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getLczd() {
        return lczd;
    }

    public void setLczd(String lczd) {
        this.lczd = lczd;
    }

    public MzblBean(String yf,String zyfs,String lczd,ArrayList<String>Listdata){
      this.lczd=lczd;
      this.yf=yf;
      this.zyfs=zyfs;
      this.listdata=Listdata;

  }

}
