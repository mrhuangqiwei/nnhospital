package com.qiwei.hospital.utils.Bean;

/**
 * Created by Administrator on 2016/3/21.
 */
public class item_mzxybean {
    private String mYpmc;
    private  String mYpgg;
    private String mYpzl;
    private String mYfdw;
    private String mFyjl;
    private String mJldwmc;
    private String mTjmc;
    private String mPcmc;

    public String getmJldwmc() {
        return mJldwmc;
    }

    public String getmPcmc() {
        return mPcmc;
    }

    public void setmPcmc(String mPcmc) {
        this.mPcmc = mPcmc;
    }

    public void setmJldwmc(String mJldwmc) {
        this.mJldwmc = mJldwmc;

    }

    public String getmTjmc() {
        return mTjmc;
    }

    public void setmTjmc(String mTjmc) {
        this.mTjmc = mTjmc;
    }

    public String getmYpgg() {
        return mYpgg;

    }

    public String getmFyjl() {
        return mFyjl;
    }

    public void setmFyjl(String mFyjl) {
        this.mFyjl = mFyjl;
    }

    public String getmYpzl() {
        return mYpzl;
    }

    public String getmYfdw() {
        return mYfdw;
    }

    public void setmYfdw(String mYfdw) {
        this.mYfdw = mYfdw;
    }

    public void setmYpzl(String mYpzl) {
        this.mYpzl = mYpzl;

    }

    public void setmYpgg(String mYpgg) {
        this.mYpgg = mYpgg;
    }

    public item_mzxybean(){


    }

    public String getmYpmc() {
        return mYpmc;
    }

    public void setmYpmc(String mYpmc) {
        this.mYpmc = mYpmc;
    }

    public item_mzxybean(String ypmc,String ypgg,String ypzl,String yfdw,String fyjl,String jldwmc,String tjmc,String pcmc){

        this.mYpmc=ypmc;
        this.mYpgg=ypgg;
        this.mYpzl=ypzl;
        this.mYfdw=yfdw;
        this.mFyjl=fyjl;
        this.mJldwmc=jldwmc;
        this.mTjmc=tjmc;
        this.mPcmc=pcmc;
    }
}
