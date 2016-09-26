package com.qiwei.hospital.utils.httplelper;

import android.os.Handler;
import android.os.Message;

import com.qiwei.hospital.utils.comprehensive.LoadingDialogManager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/28.
 */
public class MsgNetUtil {
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
     private Handler handler;

    private  String funname;
    private int msg1;
    private HttpConnSoap Soap = new HttpConnSoap();

  public MsgNetUtil(String funname1, Handler handler1, ArrayList<String> arrayList1, ArrayList<String> brrayList1,int msg){
      arrayList.clear();
      brrayList.clear();
      crrayList.clear();
      this.arrayList=arrayList1;
      this.brrayList=brrayList1;
      this.handler=handler1;
      this.funname=funname1;
      this.msg1=msg;
      LoadingDialogManager.getInstance().showDialog();
      new Thread() {
          @Override
          public void run() {
              try {

                  crrayList = Soap.GetWebServre(funname, arrayList, brrayList);
                  //  Log.e("JBXX2------------>", crrayList.toString());

                  Message message=handler.obtainMessage(msg1);
                  message.obj = crrayList;
                  handler.sendMessage(message);
                  LoadingDialogManager.getInstance().dismissDialog();

              } catch (Exception e) {
              }
          }
      }.start();


  }
 public   void function(String funname1,Handler handler1,ArrayList<String> arrayList1 ,ArrayList<String> brrayList1) {


        //  listclear();
        arrayList.clear();
        brrayList.clear();
        crrayList.clear();

     this.arrayList=arrayList1;
     this.brrayList=brrayList1;
     this.handler=handler1;
     this.funname=funname1;
        LoadingDialogManager.getInstance().showDialog();
        new Thread() {
            @Override
            public void run() {
                try {

                    crrayList = Soap.GetWebServre(funname, arrayList, brrayList);
                    //  Log.e("JBXX2------------>", crrayList.toString());


                    Message successMsg=new Message();


                    successMsg.obj = crrayList;
                     handler.sendMessage(successMsg);
                    LoadingDialogManager.getInstance().dismissDialog();

                } catch (Exception e) {
                }
            }
        }.start();

   }
}
