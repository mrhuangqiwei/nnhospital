package com.qiwei.hospital.utils.Bean;

/**
 * 开发人：黄启位
 * 时间：2016.7.1
 * 功能：科室介绍Bean
 */

public class KsandysBean {

 private String Kabm,Ksmc,Ksjjmx;


    public String getKabm() {
        return Kabm;
    }

    public void setKabm(String kabm) {
        Kabm = kabm;
    }

    public String getKsjjmx() {
        return Ksjjmx;
    }

    public void setKsjjmx(String ksjjmx) {
        Ksjjmx = ksjjmx;
    }

    public String getKsmc() {
        return Ksmc;
    }

    public void setKsmc(String ksmc) {
        Ksmc = ksmc;
    }

    public KsandysBean(){}
    public KsandysBean(String kabm, String ksmc, String ksjjmx){
       this.Kabm=kabm;
        this.Ksmc=ksmc;
        this.Ksjjmx=ksjjmx;
}}
