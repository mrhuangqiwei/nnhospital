package com.qiwei.hospital.utils.Bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/15.
 */
public class TeamBean {

    private ArrayList<String> listdata=new ArrayList<String>();
    private ArrayList<String>Teamlist=new ArrayList<String>();
    public TeamBean(){

    }

    public ArrayList<String> getTeamlist() {
        return Teamlist;
    }


    public ArrayList<String> getListdata() {
        return listdata;
    }

    public void setListdata(ArrayList<String> listdata) {
        this.listdata = listdata;
    }

    public TeamBean(ArrayList<String>listtemp,ArrayList<String>Listdata){

        this.listdata=Listdata;
        this.Teamlist=listtemp;
    }

}
