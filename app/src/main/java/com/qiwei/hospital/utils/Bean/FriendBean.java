package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/25.
 */
/**常用就诊人**/
public class FriendBean {


    private String sfzh;
    private String brxm;
    private  String brnl;

    public String getSfzh() {
        return sfzh;
    }

    public String getBrnl() {
        return brnl;
    }

    public String getPh() {
        return ph;
    }

    public String getBrdh() {
        return brdh;
    }

    public void setBrdh(String brdh) {
        this.brdh = brdh;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getBrjtzzl() {
        return brjtzzl;

    }

    public void setBrjtzzl(String brjtzzl) {
        this.brjtzzl = brjtzzl;
    }

    public String getBrxb() {

        return brxb;
    }

    public void setBrxb(String brxb) {
        this.brxb = brxb;
    }

    public void setBrnl(String brnl) {
        this.brnl = brnl;
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

    private  String brxb;
    private  String brjtzzl;
    private  String ph;
    private  String brdh;

    public FriendBean(){}
    public FriendBean(String sfzh1, String brxm1, String brnl1, String brxb1, String brjtzzl1, String ph1,String brdh1){
       this.sfzh=sfzh1;
        this.brxm=brxm1;
        this.brnl=brnl1;
        this.brxb=brxb1;
        this.brjtzzl=brjtzzl1;
        this.ph=ph1;
        this.brdh=brdh1;
    }
}
