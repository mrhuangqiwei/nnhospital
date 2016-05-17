package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MzzlczBean {
    private  String xmmc;
    private String sl;

    public String getXmmc() {
        return xmmc;
    }

    public String getDj() {
        return dj;
    }

    public String getJe() {
        return je;
    }

    public void setJe(String je) {
        this.je = je;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getSl() {
        return sl;

    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    private String dj;
    private String je;
    public MzzlczBean(){}
    public MzzlczBean(String xmmc,String sl,String dj,String je){
        this.xmmc=xmmc;
        this.sl=sl;
        this.dj=dj;
        this.je=je;
    }
}
