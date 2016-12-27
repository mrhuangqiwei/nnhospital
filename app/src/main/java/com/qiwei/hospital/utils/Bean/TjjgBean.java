package com.qiwei.hospital.utils.Bean;

/**体检结果Bean**/
public class TjjgBean {
    public String getYsxm() {
        return ysxm;
    }


    public String getZhmc() {
        return zhmc;

    }

    public void setZhmc(String zhmc) {
        this.zhmc = zhmc;
    }

    public String getKsmc() {

        return ksmc;
    }

    public void setKsmc(String ksmc) {
        this.ksmc = ksmc;
    }

    public void setYsxm(String ysxm) {

        this.ysxm = ysxm;
    }

    public String getTjzbjg() {
        return tjzbjg;
    }

    public void setTjzbjg(String tjzbjg) {
        this.tjzbjg = tjzbjg;
    }

    private String ysxm,ksmc,zhmc,tjzbjg;

    public TjjgBean(){}
    public TjjgBean(String ysxm, String ksmc,String zhmc,String tjzbjg){
      this.ysxm=ysxm;this.ksmc=ksmc;this.zhmc=zhmc;this.tjzbjg=tjzbjg;
    }
}
