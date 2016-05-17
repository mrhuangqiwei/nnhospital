package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/25.
 */
public class YspbBean {


    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getZcmc() {
        return zcmc;
    }

    public void setZcmc(String zcmc) {
        this.zcmc = zcmc;
    }

    public String getXbsj() {
        return xbsj;
    }

    public void setXbsj(String xbsj) {
        this.xbsj = xbsj;
    }

    public String getSbrq() {
        return sbrq;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public String getSbsj() {
        return sbsj;
    }

    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }

    public String getBcfabm() {
        return bcfabm;

    }

    public void setBcfabm(String bcfabm) {
        this.bcfabm = bcfabm;
    }

    public String getBcfamc() {
        return bcfamc;
    }

    public void setBcfamc(String bcfamc) {
        this.bcfamc = bcfamc;
    }

    public String getYszh() {
        return yszh;

    }

    public String getYsxm() {
        return ysxm;
    }

    public void setYsxm(String ysxm) {
        this.ysxm = ysxm;
    }

    public void setYszh(String yszh) {
        this.yszh = yszh;
    }

    private String yszh;
    private String ysxm;
    private String bcfamc;
    private String bcfabm;
    private String xq;
    private String sbsj;
    private String xbsj;
    private String zcmc;
    private String  sbrq;
    private String  sbdd;
    private  String ksmc;
    private String yydjrq;
    private  String yyghrq;
    private String yyyxrq;
    public YspbBean(){}

    public String getSbdd() {
        return sbdd;
    }

    public void setSbdd(String sbdd) {
        this.sbdd = sbdd;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public String getYydjrq() {
        return yydjrq;
    }

    public void setYydjrq(String yydjrq) {
        this.yydjrq = yydjrq;
    }

    public String getYyyxrq() {
        return yyyxrq;
    }

    public void setYyyxrq(String yyyxrq) {
        this.yyyxrq = yyyxrq;
    }

    public String getYyghrq() {

        return yyghrq;
    }

    public void setYyghrq(String yyghrq) {
        this.yyghrq = yyghrq;
    }

    public YspbBean(String czybm, String ysxm, String bcfamc, String bcfabm,String zcmc, String xq,
                    String sbsj, String xbsj,String sbrq,String sbdd,String ksmc,String yydjrq,String yyghrq,String yyyxrq ){
        this.yszh=czybm;this.ysxm=ysxm;this.bcfamc=bcfamc;this.bcfabm=bcfabm;

        this.xq=xq;this.sbsj=sbsj;this.xbsj=xbsj;this.zcmc=zcmc;
        this.sbrq=sbrq;

        this.sbdd=sbdd;
        this.ksmc=ksmc;
        this.yydjrq=yydjrq;
        this.yyghrq=yyghrq;
        this.yyyxrq=yyyxrq;

    }
}
