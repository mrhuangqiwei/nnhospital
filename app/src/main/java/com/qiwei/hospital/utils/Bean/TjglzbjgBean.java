package com.qiwei.hospital.utils.Bean;
/**体检管理指标结果**/
public class TjglzbjgBean {
private String xmbm,zhbm,ckxx,cksx,ycts,zhmc,xmmc,xmdw,ysxm,jcjg,tjbh;

    public TjglzbjgBean(){}

    public String getTjbh() {
        return tjbh;
    }

    public void setTjbh(String tjbh) {
        this.tjbh = tjbh;
    }

    public String getJcjg() {
        return jcjg;
    }

    public void setJcjg(String jcjg) {
        this.jcjg = jcjg;
    }

    public String getYsxm() {
        return ysxm;
    }

    public void setYsxm(String ysxm) {
        this.ysxm = ysxm;
    }

    public String getYcts() {
        return ycts;

    }

    public String getXmdw() {
        return xmdw;
    }

    public void setXmdw(String xmdw) {
        this.xmdw = xmdw;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getZhmc() {

        return zhmc;
    }

    public void setZhmc(String zhmc) {
        this.zhmc = zhmc;
    }

    public void setYcts(String ycts) {
        this.ycts = ycts;
    }

    public String getCksx() {

        return cksx;
    }

    public void setCksx(String cksx) {
        this.cksx = cksx;
    }

    public String getCkxx() {

        return ckxx;
    }

    public void setCkxx(String ckxx) {
        this.ckxx = ckxx;
    }

    public String getZhbm() {

        return zhbm;
    }

    public void setZhbm(String zhbm) {
        this.zhbm = zhbm;
    }

    public String getXmbm() {
        return xmbm;

    }

    public void setXmbm(String xmbm) {
        this.xmbm = xmbm;
    }

    public TjglzbjgBean(String xmbm,String zhbm,String ckxx,String cksx,String ycts,String zhmc,String xmmc,String xmdw,String ysxm,String jcjg,String tjbh ){
this.xmbm=xmbm;
        this.zhbm=zhbm;

        this.ckxx=ckxx;
        this.cksx=cksx;this.ycts=ycts;this.zhmc=zhmc;this.xmmc=xmmc;this.xmdw=xmdw;this.ysxm=ysxm;this.jcjg=jcjg;this.tjbh=tjbh;


    }
}
