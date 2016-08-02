package com.qiwei.hospital.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qiwei.hospital.ActivityHelper.BaseActivity;
import com.qiwei.hospital.AdapterManger.CommonAdapter;
import com.qiwei.hospital.AdapterManger.YyksAdapter;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.YspbBean;
import com.qiwei.hospital.utils.Bean.item_yy_ksBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;
import com.qiwei.hospital.utils.httplelper.NetUtil;
import com.qiwei.hospital.utils.httplelper.NetUtilBool;

import java.util.ArrayList;
import java.util.List;

public class YyysActivity extends BaseActivity{
    private ListView mListView;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ArrayList<String> brrayList = new ArrayList<String>();
    private ArrayList<String> crrayList = new ArrayList<String>();
    //上午上班
    private ArrayList<String>drrayList = new ArrayList<String>();
    //下午上班
    private ArrayList<String> errayList = new ArrayList<String>();
    private ArrayList<String> frrayList = new ArrayList<String>();
    private ArrayList<String> grrayList = new ArrayList<String>();
    private ArrayList<String> grrayList1 = new ArrayList<String>();
    private ArrayList<String> grrayList2 = new ArrayList<String>();
    private ArrayList<String> grrayList3 = new ArrayList<String>();
    private ArrayList<String> grrayList4 = new ArrayList<String>();
    private ArrayList<String> grrayList5 = new ArrayList<String>();
    private ArrayList<String> grrayList6 = new ArrayList<String>();
    private ArrayList<String> grrayList7 = new ArrayList<String>();

    private ArrayList<String> krrayList1 = new ArrayList<String>();
    private ArrayList<String> krrayList2 = new ArrayList<String>();
    private ArrayList<String>krrayList3 = new ArrayList<String>();
    private ArrayList<String>krrayList4 = new ArrayList<String>();
    private ArrayList<String>krrayList5 = new ArrayList<String>();
    private ArrayList<String> krrayList6 = new ArrayList<String>();
    private ArrayList<String>krrayList7 = new ArrayList<String>();

    private ArrayList<String> grrayList8 = new ArrayList<String>();
    private ArrayList<String> grrayList9 = new ArrayList<String>();

    private List<YspbBean> mdatas;
    private List<YspbBean> mdatas1;
/** 星期**/
    private TextView mTvxq1;
    private TextView mTvxq2;
    private TextView mTvxq3;
    private TextView mTvxq4;
    private TextView mTvxq5;
    private TextView mTvxq6;
    private TextView mTvxq7;

    private TextView rq1;
    private TextView rq2;
    private TextView rq3;
    private TextView rq4;
    private TextView rq5;
    private TextView rq6;
    private TextView rq7;
private GridView  mGride;
    private GridView  mGride1;

    private  ArrayList<Integer> listd=new ArrayList<Integer>();
    private  ArrayList<Integer> listd1=new ArrayList<Integer>();
    private  ArrayList<Integer> listd2=new ArrayList<Integer>();
    private  ArrayList<ArrayList<String>> listd3=new ArrayList<ArrayList<String>>();
    private NetUtil netUtil;
    private NetUtilBool netUtilBool;
    private YyksAdapter yyysAdapter;
    private Handler yyhandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            crrayList=(ArrayList<String>)msg.obj;
            Log.e("--------->",crrayList.toString());
            if(crrayList.size()<19){
                Toast.makeText(YyysActivity.this,"亲，没有找到您需要的数据！",Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
               // Log.e("------------->",crrayList.toString());
            for (int i = 0; i < crrayList.size(); i++) {
                if (crrayList.get(i).equals("shangban")) {
                    listd.add(i);
                }
            }
            for(int j=listd.get(0)+1;j<listd.get(1);j++){
                drrayList.add(crrayList.get(j));
            }


            for(int k=listd.get(1)+1;k<crrayList.size();k++){
                errayList.add(crrayList.get(k));
            }
            for (int i = 0; i < drrayList.size(); i++) {
                if (drrayList.get(i).equals("xingqi")) {
                    listd1.add(i);
                }
            }
           for(int i1=listd1.get(0)+1;i1<listd1.get(1);i1++){
               grrayList1.add(drrayList.get(i1));
           }
            for(int i1=listd1.get(1)+1;i1<listd1.get(2);i1++){
                grrayList2.add(drrayList.get(i1));
            }
            for(int i1=listd1.get(2)+1;i1<listd1.get(3);i1++){
                grrayList3.add(drrayList.get(i1));
            }
            for(int i1=listd1.get(3)+1;i1<listd1.get(4);i1++){
                grrayList4.add(drrayList.get(i1));
            }
            for(int i1=listd1.get(4)+1;i1<listd1.get(5);i1++){
                grrayList5.add(drrayList.get(i1));
            }

            for(int i1=listd1.get(5)+1;i1<listd1.get(6);i1++){
                grrayList6.add(drrayList.get(i1));
            }
            for(int i1=listd1.get(6)+1;i1<listd1.get(7);i1++){
                grrayList7.add(drrayList.get(i1));
            }
            int[]                                                 arry={grrayList1.size(),grrayList2.size(),grrayList3.size(),grrayList4.size(),grrayList5.size(),grrayList6.size(),grrayList7.size()};

            int max = arry[0];
            for(int i=0;i<arry.length;i++){
                if(arry[i]>max)
                    max = arry[i];
            }
            String tc=" ";

            leather(grrayList1, tc, max);
            leather(grrayList2, tc, max);
            leather(grrayList3, tc, max);
            leather(grrayList4, tc, max);
            leather(grrayList5, tc, max);
            leather(grrayList6, tc, max);
            leather(grrayList7, tc, max);
            /** 设置星期**/
            mTvxq1.setText(grrayList1.get(5));
            mTvxq2.setText(grrayList2.get(5));
            mTvxq3.setText(grrayList3.get(5));
            mTvxq4.setText(grrayList4.get(5));
            mTvxq5.setText(grrayList5.get(5));
            mTvxq6.setText(grrayList6.get(5));
            mTvxq7.setText(grrayList7.get(5));
            rq1.setText(grrayList1.get(8));
            rq2.setText(grrayList2.get(8));
            rq3.setText(grrayList3.get(8));
            rq4.setText(grrayList4.get(8));
            rq5.setText(grrayList5.get(8));
            rq6.setText(grrayList6.get(8));
            rq7.setText(grrayList7.get(8));



           // 数据填充
            mdatas=new  ArrayList<YspbBean>();
            for(int i=0;i<=max-14;i=i+14){
             YspbBean yspbBean=new YspbBean(grrayList1.get(i),grrayList1.get(i+1),
             grrayList1.get(i+2),grrayList1.get(i+3),grrayList1.get(i+4),grrayList1.get(i+5),
                     grrayList1.get(i+6),grrayList1.get(i+7),grrayList1.get(i+8),grrayList1.get(i+9),grrayList1.get(i+10),grrayList1.get(i+11),grrayList1.get(i+12),grrayList1.get(i+13));
                mdatas.add(yspbBean);
                yspbBean=new YspbBean(grrayList2.get(i),grrayList2.get(i+1),
                        grrayList2.get(i+2),grrayList2.get(i+3),grrayList2.get(i+4),grrayList2.get(i+5),grrayList2.get(i+6),grrayList2.get(i+7),
                        grrayList2.get(i+8),grrayList2.get(i+9),grrayList2.get(i+10),grrayList2.get(i+11),grrayList2.get(i+12),grrayList2.get(i+13));
                mdatas.add(yspbBean);
               yspbBean=new YspbBean(grrayList3.get(i),grrayList3.get(i+1),
                        grrayList3.get(i+2),grrayList3.get(i+3),grrayList3.get(i+4),grrayList3.get(i+5),grrayList3.get(i+6),
                       grrayList3.get(i+7),grrayList3.get(i+8),grrayList3.get(i+9),grrayList3.get(i+10),grrayList3.get(i+11),grrayList3.get(i+12),grrayList3.get(i+13));
                mdatas.add(yspbBean);
                 yspbBean=new YspbBean(grrayList4.get(i),grrayList4.get(i+1),
                        grrayList4.get(i+2),grrayList4.get(i+3),grrayList4.get(i+4),grrayList4.get(i+5),grrayList4.get(i+6),grrayList4.get(i+7),
                         grrayList4.get(i+8),grrayList4.get(i+9),grrayList4.get(i+10),grrayList4.get(i+11),grrayList4.get(i+12),grrayList4.get(i+13));
                mdatas.add(yspbBean);
              yspbBean=new YspbBean(grrayList5.get(i),grrayList5.get(i+1),
                        grrayList5.get(i+2),grrayList5.get(i+3),grrayList5.get(i+4),grrayList5.get(i+5),grrayList5.get(i+6),
                      grrayList5.get(i+7),grrayList5.get(i+8),grrayList5.get(i+9),grrayList5.get(i+10),grrayList5.get(i+11),grrayList5.get(i+12),grrayList5.get(i+13));
                mdatas.add(yspbBean);
                yspbBean=new YspbBean(grrayList6.get(i),grrayList6.get(i+1),
                        grrayList6.get(i+2),grrayList6.get(i+3),grrayList6.get(i+4),grrayList6.get(i+5),grrayList6.get(i+6),
                        grrayList6.get(i+7),grrayList6.get(i+8),grrayList6.get(i+9),grrayList6.get(i+10),grrayList6.get(i+11),grrayList6.get(i+12),grrayList6.get(i+13));
                mdatas.add(yspbBean);

                yspbBean=new YspbBean(grrayList7 .get(i),grrayList7.get(i + 1),
                        grrayList7.get(i+2),grrayList7.get(i + 3),grrayList7.get(i+4),grrayList7.get(i+5),grrayList7.get(i+6),
                        grrayList7 .get(i + 7),grrayList7.get(i+8),grrayList7.get(i+9),grrayList7.get(i + 10),grrayList7.get(i+11),grrayList7.get(i+12),grrayList7.get(i+13));

                mdatas.add(yspbBean);

            }
            Log.e("gr---->", grrayList1.toString());
            Log.e("ls---->",listd1.toString());

            yspbAdapter adapter=new yspbAdapter(YyysActivity.this,mdatas);
            mGride.setAdapter(adapter);
            mGride.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent= new Intent();
                    intent.setClass(YyysActivity.this, Yygh_ysxx.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("yszh", mdatas.get(position).getYszh());
                    bundle.putString("djrq", mdatas.get(position).getYydjrq());
                    bundle.putString("ghrq", mdatas.get(position).getYyghrq());
                    bundle.putString("yxrq", mdatas.get(position).getYyyxrq());
                    bundle.putString("ysxm", mdatas.get(position).getYsxm());
                  //  Log.e("--->",mdatas.get(position).getYsxm());
                    bundle.putString("yszh", mdatas.get(position).getYszh());
                    bundle.putString("sbsj", mdatas.get(position).getSbsj());
                    bundle.putString("xbsj", mdatas.get(position).getXbsj());
                    bundle.putString("sbrq", mdatas.get(position).getSbrq());
                    bundle.putString("xq", mdatas.get(position).getXq());
                    bundle.putString("dd", mdatas.get(position).getSbdd());
                    bundle.putString("ksmc", mdatas.get(position).getKsmc());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
/**下午排班**/
            for (int i = 0; i < errayList.size(); i++) {
                if (errayList.get(i).equals("xingqi")) {
                    listd2.add(i);
                }
            }
            /**下午数据**/
            inintdatafter(krrayList1,0);
            inintdatafter(krrayList2,1);
            inintdatafter(krrayList3,2);
            inintdatafter(krrayList4,3);
            inintdatafter(krrayList5,4);
            inintdatafter(krrayList6,5);
            inintdatafter(krrayList7,6);
            int[] arry1={krrayList1.size(),krrayList2.size(),krrayList3.size(),krrayList4.size(),krrayList5.size(),krrayList6.size(),krrayList7.size()};

            int max1 = arry[0];
            for(int i=0;i<arry1.length;i++){
                if(arry[i]>max1)
                    max1 = arry[i];
            }
            leather(krrayList1, tc, max1);
            leather(krrayList2, tc, max1);
            leather(krrayList3, tc, max1);
            leather(krrayList4, tc, max1);
            leather(krrayList5, tc, max1);
            leather(krrayList6, tc, max1);
            leather(krrayList7, tc, max1);
            mdatas1=new  ArrayList<YspbBean>();
            for(int i=0;i<=max1-14;i=i+14){
            initaf(krrayList1,mdatas1,i);
            initaf(krrayList2,mdatas1,i);
            initaf(krrayList3,mdatas1,i);
            initaf(krrayList4,mdatas1,i);
            initaf(krrayList5,mdatas1,i);
            initaf(krrayList6,mdatas1,i);
            initaf(krrayList7, mdatas1,i);}

            yspbAdapter adapter1=new yspbAdapter(YyysActivity.this,mdatas1);
            mGride1.setAdapter(adapter1);
            mGride1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent= new Intent();
                    intent.setClass(YyysActivity.this, Yygh_ysxx.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("yszh", mdatas1.get(position).getYszh());
                    bundle.putString("djrq", mdatas1.get(position).getYydjrq());
                    bundle.putString("ghrq", mdatas1.get(position).getYyghrq());
                    bundle.putString("yxrq", mdatas1.get(position).getYyyxrq());
                    bundle.putString("ysxm", mdatas1.get(position).getYsxm());
                    //Log.e("--->",mdatas1.get(position).getYsxm());
                    bundle.putString("yszh", mdatas1.get(position).getYszh());
                    bundle.putString("sbsj", mdatas1.get(position).getSbsj());
                    bundle.putString("xbsj", mdatas1.get(position).getXbsj());
                    bundle.putString("sbrq", mdatas1.get(position).getSbrq());
                    bundle.putString("xq", mdatas1.get(position).getXq());
                    bundle.putString("dd", mdatas1.get(position).getSbdd());
                    bundle.putString("ksmc", mdatas1.get(position).getKsmc());
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            });

        }}
    };

    private void initaf(ArrayList<String> krray, List<YspbBean> mdatas1,int i) {
        YspbBean yspbBean1=new YspbBean(krray.get(i+0),krray.get(i+1),krray.get(i+2),krray.get(i+3),krray.get(i+4),krray.get(i+5),krray.get(i+6),krray.get(i+7),krray.get(i+8),krray.get(i+9),krray.get(i+10),krray.get(i+11),krray.get(i+12),krray.get(i+13));
        mdatas1.add(yspbBean1);
    }

    /**下午数据解析**/
    private void inintdatafter(ArrayList<String>arry,int k) {
        for(int i1=listd2.get(k)+1;i1<listd2.get(k+1);i1++){
            arry.add(errayList.get(i1));
        }
    }

    @Override
    protected void initEnvironment() {
        
    }

    @Override
    protected void initViews() {
        mGride=(GridView)findViewById(R.id.yspb_gride);
        mTvxq1=(TextView)findViewById(R.id.tv_ysgh_ys_layout1);
        mTvxq2=(TextView)findViewById(R.id.tv_ysgh_ys_layout2);
        mTvxq3=(TextView)findViewById(R.id.tv_ysgh_ys_layout3);
        mTvxq4=(TextView)findViewById(R.id.tv_ysgh_ys_layout4);
        mTvxq5=(TextView)findViewById(R.id.tv_ysgh_ys_layout5);
        mTvxq6=(TextView)findViewById(R.id.tv_ysgh_ys_layout6);
        mTvxq7=(TextView)findViewById(R.id.tv_ysgh_ys_layout7);
        rq1=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq1);
        rq2=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq2);
        rq3=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq3);
        rq4=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq4);
        rq5=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq5);
        rq6=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq6);
        rq7=(TextView)findViewById(R.id.tv_ysgh_ys_layout_rq7);
        mGride1=(GridView)findViewById(R.id.yspb_gride1);

        inintdata();
    }

    private void inintdata() {
        Bundle bundle = this.getIntent().getExtras();
        String name = bundle.getString("ksbm");

        arrayList.clear();
        brrayList.clear();
        arrayList.add("ksbm");
        brrayList.add(name);
        String name1="getKsyssbsj";
        netUtil=new NetUtil(name1,yyhandler,arrayList,brrayList);
    }

    @Override
    protected int getLayoutId() {
        return (R.layout.yygh_ys_layout);
    }


    private void  leather(ArrayList<String> arr,String str,int k){
   if(arr.size()<k){
       for(int i=arr.size();i<k;i++){
           arr.add(str);
       }
   }

    }

    class yspbAdapter extends CommonAdapter<YspbBean> {
        public yspbAdapter(Context context, List<YspbBean>datas) {
            super(context, datas, R.layout.item_grideview);
        }


        @Override
        public void convert(ViewHolder holder, YspbBean yspbBean) {
            holder.setText(R.id.item_grid_ysxm, yspbBean.getYsxm());
            holder.setText(R.id.item_grid_yszc,yspbBean.getZcmc());
        }
    }




}
