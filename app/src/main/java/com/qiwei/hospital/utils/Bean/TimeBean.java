package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/25.
 */
/**常用就诊人**/
public class TimeBean {
    private String brid;
    private String ghxh;
    private  String ylkh;

    public String getBrid() {
        return brid;
    }

    public String getYlkh() {
        return ylkh;
    }

    public String getZylx() {
        return zylx;
    }

    public void setZylx(String zylx) {
        this.zylx = zylx;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getGhrq() {

        return ghrq;
    }

    public void setGhrq(String ghrq) {
        this.ghrq = ghrq;
    }

    public void setYlkh(String ylkh) {
        this.ylkh = ylkh;
    }

    public String getGhxh() {
        return ghxh;

    }

    public void setGhxh(String ghxh) {
        this.ghxh = ghxh;
    }

    public void setBrid(String brid) {
        this.brid = brid;
    }

    private  String ghrq;   private  String sfzh;String zylx;



    public TimeBean(){}
    public TimeBean(String brid, String ghxh, String ylkh, String ghrq, String sfzh, String zylx){
    this.brid=brid;this.ghxh=ghxh;this.ylkh=ylkh;this.ghrq=ghrq;this.sfzh=sfzh;this.zylx=zylx;
    }
}
