package com.qiwei.hospital.AdapterManger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzzycfBean;
import com.qiwei.hospital.utils.Bean.item_mzxybean;

import java.util.List;

public class MListItem1Adapter extends BaseAdapter
{
    List<item_mzxybean> list;
    Context context;
    LayoutInflater mInflater;

    public MListItem1Adapter(Context c, List<item_mzxybean> list){
        this.context = c;
        this.list = list;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        // TODO Auto-generated method stub
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {

        Holder holder;
        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.item_xycf, null);
            holder = new Holder();
            holder.mYpmc = (TextView) convertView.findViewById(R.id.tv_xycf_ypmc);
            holder.mYpgg=(TextView)convertView.findViewById(R.id.tv_xycf_ypgg);
            holder.mYpsl=(TextView)convertView.findViewById(R.id.tv_xycf_ypzl);
            holder.mYpdw=(TextView)convertView.findViewById(R.id.tv_xycf_ypdw);
            holder.mFyjl=(TextView)convertView.findViewById(R.id.tv_xycf_ypjl);
            holder.mJldw=(TextView)convertView.findViewById(R.id.tv_xycf_jldw);
            holder.mFyff=(TextView)convertView.findViewById(R.id.tv_xycf_fuff);
            holder.mFypc=(TextView)convertView.findViewById(R.id.tv_xycf_fypc);

            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
      item_mzxybean bean =list.get(position);
       holder.mYpmc.setText(bean.getmYpmc());
        holder.mYpgg.setText(bean.getmYpgg());
        holder.mYpsl.setText(bean.getmYpzl());
        holder.mYpdw.setText(bean.getmYfdw());
        holder.mFyjl.setText(bean.getmFyjl());
        holder.mJldw.setText(bean.getmJldwmc());
        holder.mFyff.setText(bean.getmTjmc());
        holder.mFypc.setText(bean.getmPcmc());
        return convertView;
    }

    class Holder
    {
        TextView mYpmc;
        TextView mYpgg;
        TextView mYpsl;
        TextView mYpdw;
        TextView mFyjl;
        TextView mJldw;
        TextView mFyff;
        TextView mFypc;
    }

}
