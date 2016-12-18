package com.qiwei.hospital.utils.Bean;
/**Pacx结果对象**/
public class PacxjgBean {
    private  String laybe1,laybe2;
    public PacxjgBean(){}
    public PacxjgBean(String laybe1, String laybe2){
       this.laybe1=laybe1;
        this.laybe2=laybe2;
    }

    public String getLaybe1() {
        return laybe1;
    }

    public String getLaybe2() {
        return laybe2;
    }

    public void setLaybe2(String laybe2) {
        this.laybe2 = laybe2;
    }

    public void setLaybe1(String laybe1) {
        this.laybe1 = laybe1;
    }
}
