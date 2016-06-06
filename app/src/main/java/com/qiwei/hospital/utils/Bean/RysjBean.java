package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/25.
 */
/**常用就诊人**/
public class RysjBean {

    private String rysj;
    private  String zyh;

    public String getRysj() {
        return rysj;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    public void setRysj(String rysj) {
        this.rysj = rysj;
    }

    public RysjBean(){}
    public RysjBean(String rysj,String zyh){
       this.rysj=rysj;
        this.zyh=zyh;
    }
}
