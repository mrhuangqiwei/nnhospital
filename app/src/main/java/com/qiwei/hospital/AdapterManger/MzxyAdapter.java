package com.qiwei.hospital.AdapterManger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzblBean;
import com.qiwei.hospital.utils.Bean.MzzycfBean;
import com.qiwei.hospital.utils.Bean.item_mzxybean;
import com.qiwei.hospital.utils.Bean.mzxybean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class MzxyAdapter extends BaseAdapter {
    private MzZyCfAdapter mzZyCfAdapter;
    private List<item_mzxybean> mDatas;
    private LayoutInflater mInflater;
    private List<mzxybean>mdata;
    private Context c;


    public   MzxyAdapter(Context context ,List<mzxybean> datas){
        this.c=context;
        this.mdata=datas;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.item_xycf_title,parent,false);
            holder=new Holder();
            holder.mLczd=(TextView)convertView.findViewById(R.id.tv_xycf_title_lczd);
            holder.mFb=(TextView)convertView.findViewById(R.id.tv_xycf_title_fb);

            holder.mZylist=(ListView)convertView.findViewById(R.id.list_xycf_title);

            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
        if(mdata.get(position)!=null){

            mzxybean mzblBean=mdata.get(position);
            holder.mLczd.setText(mzblBean.getLczd());
            holder.mFb.setText(mzblBean.getBrfb());

            mDatas=new ArrayList<item_mzxybean>();
            for(int i=0;i<mzblBean.getListdata().size();i=i+8){
                item_mzxybean bean =new item_mzxybean(mzblBean.getListdata().get(i),mzblBean.getListdata().get(i+1),mzblBean.getListdata().get(i+2),
                        mzblBean.getListdata().get(i+3),mzblBean.getListdata().get(i+4),mzblBean.getListdata().get(i+5),mzblBean.getListdata().get(i+6),mzblBean.getListdata().get(i+7));
                mDatas.add(bean);
            }
            //Log.e("这是mData",mDatas.toString());
            MListItem1Adapter adapter = new MListItem1Adapter(c, mDatas);

            holder.mZylist.setAdapter(adapter);
            Utility.setListViewHeightBasedOnChildren(holder.mZylist);
        }

        return convertView;

    }
    private  class  Holder {
        TextView mLczd;
        TextView mFb;
        ListView mZylist;
    }

}
