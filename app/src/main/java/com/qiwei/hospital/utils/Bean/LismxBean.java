package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/12、7.
 */
/**检验明细**/

public class LismxBean {
private  String jyxh,lx,bah,bz,xh,value_N,value_L,value_T,jg,
    n_min,n_max,zt,zwmc,ywmc,sjlx,dw,cklx,xsws,value_N_1,tjdw,yblx,ckz_t;

    public LismxBean(){}

    public String getZt() {
        return zt;
    }

    public String getYblx() {
        return yblx;
    }

    public String getCkz_t() {
        return ckz_t;
    }

    public void setCkz_t(String ckz_t) {
        this.ckz_t = ckz_t;
    }

    public void setYblx(String yblx) {

        this.yblx = yblx;
    }

    public String getTjdw() {

        return tjdw;
    }

    public void setTjdw(String tjdw) {
        this.tjdw = tjdw;
    }

    public String getValue_N_1() {
        return value_N_1;

    }

    public void setValue_N_1(String value_N_1) {
        this.value_N_1 = value_N_1;
    }

    public String getXsws() {
        return xsws;
    }

    public void setXsws(String xsws) {
        this.xsws = xsws;
    }

    public String getCklx() {
        return cklx;
    }

    public void setCklx(String cklx) {
        this.cklx = cklx;
    }

    public String getDw() {
        return dw;

    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getSjlx() {
        return sjlx;
    }

    public void setSjlx(String sjlx) {
        this.sjlx = sjlx;
    }

    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc;
    }

    public String getZwmc() {
        return zwmc;
    }

    public void setZwmc(String zwmc) {
        this.zwmc = zwmc;
    }

    public void setZt(String zt) {

        this.zt = zt;
    }

    public String getN_min() {
        return n_min;
    }

    public String getN_max() {
        return n_max;
    }

    public void setN_max(String n_max) {
        this.n_max = n_max;
    }

    public void setN_min(String n_min) {
        this.n_min = n_min;
    }

    public String getJg() {
        return jg;

    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getValue_T() {
        return value_T;

    }

    public void setValue_T(String value_T) {
        this.value_T = value_T;
    }

    public String getValue_L() {
        return value_L;

    }

    public void setValue_L(String value_L) {
        this.value_L = value_L;
    }

    public String getXh() {

        return xh;
    }

    public String getValue_N() {
        return value_N;
    }

    public void setValue_N(String value_N) {
        this.value_N = value_N;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getBah() {
        return bah;
    }

    public void setBah(String bah) {
        this.bah = bah;
    }

    public String getLx() {
        return lx;

    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public LismxBean(String jyxh, String lx,String bah,String bz,String xh,String value_N,String value_L,String value_T,
                     String jg,String n_min,String n_max,String zt,String zwmc,String ywmc,String sjlx,String dw,String cklx,
                     String xsws,String value_N_1,String tjdw,String yblx,String ckz_t){
       this.jyxh=jyxh;this.lx=lx;this.bah=bah;this.bz=bz;this.xh=xh;this.value_N=value_N;this.value_L=value_L;
        this.value_T=value_T;this.jg=jg;this.n_min=n_min;this.n_max=n_max;this.zt=zt;this.zwmc=zwmc;
        this.ywmc=ywmc;this.sjlx=sjlx;this.dw=dw;this.cklx=cklx;this.xsws=xsws;this.value_N_1=value_N_1;
        this.tjdw=tjdw;this.yblx=yblx;this.ckz_t=ckz_t;
    }

    public String getJyxh() {
        return jyxh;
    }

    public void setJyxh(String jyxh) {
        this.jyxh = jyxh;
    }
}
