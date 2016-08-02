package com.qiwei.hospital.utils.Bean;

/**
 * 开发人：黄启位
 * 时间：2016.7.9
 * 功能：满意度调查Bean
 */

public class MyddcBean {

 private String myddcid,myddcmc,myddcqybz;

    public Boolean getIsimg2() {
        return Isimg2;
    }

    public int getFenshu() {
        return fenshu;
    }

    public void setFenshu(int fenshu) {
        this.fenshu = fenshu;
    }

    public Boolean getIsimg5() {
        return Isimg5;
    }
    private int fenshu=1;
    public void setIsimg5(Boolean isimg5) {
        Isimg5 = isimg5;
    }

    public Boolean getIsimg4() {

        return Isimg4;
    }

    public void setIsimg4(Boolean isimg4) {
        Isimg4 = isimg4;
    }

    public Boolean getIsimg3() {

        return Isimg3;
    }

    public void setIsimg3(Boolean isimg3) {
        Isimg3 = isimg3;
    }

    public void setIsimg2(Boolean isimg2) {
        Isimg2 = isimg2;
    }

    public Boolean getIsimg1() {
        return Isimg1;

    }

    public void setIsimg1(Boolean isimg1) {
        Isimg1 = isimg1;
    }

    private Boolean Isimg1=true,Isimg2=false,Isimg3=false,Isimg4=false,Isimg5=false;

    public MyddcBean(){}

    public String getMyddcmc() {
        return myddcmc;
    }

    public String getMyddcqybz() {
        return myddcqybz;
    }

    public void setMyddcqybz(String myddcqybz) {
        this.myddcqybz = myddcqybz;
    }

    public void setMyddcmc(String myddcmc) {
        this.myddcmc = myddcmc;
    }

    public String getMyddcid() {
        return myddcid;

    }

    public void setMyddcid(String myddcid) {
        this.myddcid = myddcid;
    }

    public MyddcBean(String myddcid, String myddcmc, String myddcqybz){
     this.myddcid=myddcid;
        this.myddcmc=myddcmc;this.myddcqybz=myddcqybz;
}}
