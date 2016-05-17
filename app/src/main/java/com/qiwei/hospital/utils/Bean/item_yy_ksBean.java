package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/30.
 */
public class item_yy_ksBean {
    private  String ksbm;
    private String ksmc;
    public item_yy_ksBean(String ksbm,String ksmc){
        this.ksbm=ksbm;
        this.ksmc=ksmc;
    }

    public String getKsbm() {
        return ksbm;
    }

    public String getKsmc() {
        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public void setKsbm(String ksbm) {
        this.ksbm = ksbm;
    }
}
