package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/9.
 */
public class MzzycfBean {
    private String mzzyyp1;



    public String getMzzycfdw1() {
        return mzzycfdw1;
    }

    public String getMzzycfsl1() {
        return mzzycfsl1;
    }





    public void setMzzycfsl1(String mzzycfsl1) {

        this.mzzycfsl1 = mzzycfsl1;
    }


    public void setMzzycfdw1(String mzzycfdw1) {
        this.mzzycfdw1 = mzzycfdw1;
    }




    private String mzzycfdw1;

    private String mzzycfsl1;

    public String getMzzyyp1() {
        return mzzyyp1;
    }

    public void setMzzyyp1(String mzzyyp1) {
        this.mzzyyp1 = mzzyyp1;
    }



    public MzzycfBean(){

    }
    public MzzycfBean(String mzzyyp1,String mzzycfsl1,String mzzycfdw1){
        super();
        this.mzzyyp1=mzzyyp1;

        this.mzzycfdw1=mzzycfdw1;

        this.mzzycfsl1=mzzycfsl1;

    }
}
