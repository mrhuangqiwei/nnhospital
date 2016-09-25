package com.qiwei.hospital.utils.Bean;

/**创建时间：2016-9-20

 * 开发人：黄启位
 *功能：医生排班bean根据时间所排班次
/**常用就诊人**/
public class YSBCBean {
private String yzrq,sbsj,xbsj,xhzs,xyzs,czybm,czyxm,zcmc,xq,mzsbdd,ksmc,ksbm,yydjsj,yyghsj,yyyxsj;


    public YSBCBean(){}
    public YSBCBean(String yzrq, String sbsj,String xbsj,String xhzs,String xyzs,String czybm,String czyxm,String zcmc,String xq,String mzsbdd,String ksmc,String ksbm,String yydjsj,String yyghrj,String yyyxsj){
 this.yzrq=yzrq;this.sbsj=sbsj;this.xbsj=xbsj;this.xhzs=xhzs;this.xyzs=xyzs;this.czybm=czybm;
        this.czyxm=czyxm;this.zcmc=zcmc;this.xq=xq;this.mzsbdd=mzsbdd;this.ksmc=ksmc;this.ksbm=ksbm;this.yydjsj=yydjsj;
      this.yyghsj=yyghsj;this.yyyxsj=yyyxsj;
    }

    public String getCzyxm() {
        return czyxm;
    }

    public String getKsbm() {
        return ksbm;
    }

    public String getYyyxsj() {
        return yyyxsj;
    }

    public void setYyyxsj(String yyyxsj) {
        this.yyyxsj = yyyxsj;
    }

    public String getYyghsj() {
        return yyghsj;
    }

    public void setYyghsj(String yyghsj) {
        this.yyghsj = yyghsj;
    }

    public String getYydjsj() {
        return yydjsj;

    }

    public void setYydjsj(String yydjsj) {
        this.yydjsj = yydjsj;
    }

    public void setKsbm(String ksbm) {
        this.ksbm = ksbm;
    }

    public String getKsmc() {
        return ksmc;

    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public String getMzsbdd() {

        return mzsbdd;
    }

    public void setMzsbdd(String mzsbdd) {
        this.mzsbdd = mzsbdd;
    }

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

    public void setCzyxm(String czyxm) {

        this.czyxm = czyxm;
    }

    public String getCzybm() {
        return czybm;

    }

    public void setCzybm(String czybm) {
        this.czybm = czybm;
    }

    public String getXbsj() {
        return xbsj;
    }

    public String getXyzs() {
        return xyzs;
    }

    public void setXyzs(String xyzs) {
        this.xyzs = xyzs;
    }

    public String getXhzs() {
        return xhzs;
    }

    public void setXhzs(String xhzs) {
        this.xhzs = xhzs;
    }

    public void setXbsj(String xbsj) {
        this.xbsj = xbsj;
    }

    public String getSbsj() {
        return sbsj;

    }

    public void setSbsj(String sbsj) {
        this.sbsj = sbsj;
    }

    public String getYzrq() {
        return yzrq;
    }

    public void setYzrq(String yzrq) {
        this.yzrq = yzrq;
    }
}
