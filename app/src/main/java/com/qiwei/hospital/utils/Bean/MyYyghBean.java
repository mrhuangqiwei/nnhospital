package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/25.
 */
/**常用就诊人**/
public class MyYyghBean {

  private  String yyghid;
    private String brid;
    private String sfzh;
    private String brxm;
    private String yyyszh;
    private String yyysxm;
    private  String kswz;
private  String yyghrq;
    private String brdh;
private  String ksmc;
    private  String yyrq;
    public MyYyghBean(){}

    public String getSfzh() {
        return sfzh;
    }

    public String getYyrq() {
        return yyrq;
    }

    public void setYyrq(String yyrq) {
        this.yyrq = yyrq;
    }

    public String getBrdh() {

        return brdh;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public void setBrdh(String brdh) {
        this.brdh = brdh;

    }

    public String getYyghrq() {
        return yyghrq;

    }

    public void setYyghrq(String yyghrq) {
        this.yyghrq = yyghrq;
    }

    public String getYyysxm() {

        return yyysxm;
    }

    public void setYyysxm(String yyysxm) {
        this.yyysxm = yyysxm;
    }

    public String getYyyszh() {
        return yyyszh;
    }

    public void setYyyszh(String yyyszh) {
        this.yyyszh = yyyszh;
    }

    public String getBrxm() {
        return brxm;
    }

    public void setBrxm(String brxm) {
        this.brxm = brxm;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;

    }

    public String getYyghid() {
        return yyghid;
    }

    public String getBrid() {
        return brid;
    }

    public void setBrid(String brid) {
        this.brid = brid;
    }

    public void setYyghid(String yyghid) {
        this.yyghid = yyghid;
    }

    public MyYyghBean(String brxm1,String sfzh1,String yyyszh,String yyysxm,String yyghrq,String brdh,String ksmc,String yyrq,String yyghid,String brid,String kswz){

        this.brxm=brxm1;
        this.sfzh=sfzh1;

        this.yyyszh=yyyszh;
        this.yyysxm=yyysxm;
        this.yyghrq=yyghrq;
        this.brdh=brdh;

        this.ksmc=ksmc;
        this.yyrq=yyrq;
        this.yyghid=yyghid;
        this.brid=brid;
        this.kswz=kswz;
    }

    public String getKswz() {
        return kswz;
    }

    public void setKswz(String kswz) {
        this.kswz = kswz;
    }
}
