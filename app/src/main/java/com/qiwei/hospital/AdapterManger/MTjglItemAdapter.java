package com.qiwei.hospital.AdapterManger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzzycfBean;
import com.qiwei.hospital.utils.Bean.TjglzbjgBean;

import java.util.List;

public class MTjglItemAdapter extends BaseAdapter
{
    List<TjglzbjgBean> list;
    Context context;
    LayoutInflater mInflater;

    public MTjglItemAdapter(Context c, List<TjglzbjgBean> list){
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
            convertView = mInflater.inflate(R.layout.item_inner_tigl, null);
            holder = new Holder();
            holder.mxmmc = (TextView) convertView.findViewById(R.id.tv_tjgl_xmmc);
            holder.xmjg=(TextView)convertView.findViewById(R.id.tv_tjgl_tjjg);
            holder.xmdw = (TextView) convertView.findViewById(R.id.tv_tjgl_tjdw);
            holder.ckfw=(TextView)convertView.findViewById(R.id.tv_tjgl_ckfw);
            holder.ts=(TextView)convertView.findViewById(R.id.tv_tjgl_ts);
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
       TjglzbjgBean bean =list.get(position);
        holder.mxmmc.setText(bean.getXmmc());
        holder.xmjg.setText(bean.getJcjg());
        holder.xmdw.setText(bean.getXmdw());
        holder.ts.setText(bean.getYcts());
        //int k = Integer.parseInt(bean.getCkxx());
        //int m=Integer.parseInt(bean.getCksx());
        if(!(bean.getCkxx().equals("0.00")&&(bean.getCkxx().equals("0.00")))){
        holder.ckfw.setText(bean.getCkxx()+"--"+bean.getCksx());}
        else {holder.ckfw.setText("");}
        return convertView;
    }

    class Holder
    {

        TextView mxmmc;
        TextView xmjg;
        TextView xmdw;
        TextView ckfw;
        TextView ts;
    }

}
