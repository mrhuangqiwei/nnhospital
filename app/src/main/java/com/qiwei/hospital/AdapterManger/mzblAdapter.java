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
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class mzblAdapter extends BaseAdapter {
    private MzZyCfAdapter mzZyCfAdapter;
    private List<MzzycfBean> mDatas;
    private LayoutInflater mInflater;
    private List<MzblBean>mdata;
    private Context c;

    public   mzblAdapter(Context context ,List<MzblBean>datas){
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
    convertView=mInflater.inflate(R.layout.activity_mzzycf,parent,false);
    holder=new Holder();
    holder.mLczd=(TextView)convertView.findViewById(R.id.zycf_tv_lczd);
    holder.mZyyf=(TextView)convertView.findViewById(R.id.mzzycf_tv_zyyf);
    holder.mZyfs=(TextView)convertView.findViewById(R.id.mzzycf_tv_zyfs);
    holder.mZylist=(ListView)convertView.findViewById(R.id.mzzycf_list_cf);

    convertView.setTag(holder);
}
else
{
    holder = (Holder) convertView.getTag();
}

        if(mdata.get(position)!=null){

            MzblBean mzblBean=mdata.get(position);
            holder.mLczd.setText(mzblBean.getLczd());
            holder.mZyyf.setText(mzblBean.getYf());
            holder.mZyfs.setText(mzblBean.getZyfs());
            mDatas=new ArrayList<MzzycfBean>();
            for(int i=0;i<mzblBean.getListdata().size();i=i+3){
                MzzycfBean bean =new MzzycfBean(mzblBean.getListdata().get(i),mzblBean.getListdata().get(i+1),mzblBean.getListdata().get(i+2));
                mDatas.add(bean);
            }
            MListItemAdapter adapter = new MListItemAdapter(c, mDatas);
            holder.mZylist.setAdapter(adapter);
            Utility.setListViewHeightBasedOnChildren(holder.mZylist);
        }

        return convertView;
    }


    private  class  Holder {
        TextView mLczd;
        TextView mZyyf;
        TextView mZyfs;
        ListView mZylist;
    }












}
