package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/25.
 */
/**常用就诊人**/
public class MzsjBean {

    private String brid;
    private  String ghxh;

    public String getGhsj() {
        return ghsj;
    }

    public void setGhsj(String ghsj) {
        this.ghsj = ghsj;
    }

    private String ghsj;

    public String getGhxh() {
        return ghxh;
    }

    public void setGhxh(String ghxh) {
        this.ghxh = ghxh;
    }

    public String getBrid() {

        return brid;
    }

    public void setBrid(String brid) {
        this.brid = brid;
    }

    public MzsjBean(){}
    public MzsjBean(String brid, String ghxh,String ghsj){
      this.brid=brid;this.ghxh=ghxh;
        this.ghsj=ghsj;
    }
}
