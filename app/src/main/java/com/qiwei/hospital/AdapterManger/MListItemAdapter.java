package com.qiwei.hospital.AdapterManger;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzzycfBean;

public class MListItemAdapter extends BaseAdapter
{
    List<MzzycfBean> list;
    Context context;
    LayoutInflater mInflater;
    
    public MListItemAdapter(Context c, List<MzzycfBean> list){
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
            convertView = mInflater.inflate(R.layout.item_mzzycf_list, null);
            holder = new Holder();
            holder.mYpmc = (TextView) convertView.findViewById(R.id.item_list_mzzycf_yp1);
            holder.mYpsl=(TextView)convertView.findViewById(R.id.item_list_mzzycf_sl1);
            convertView.setTag(holder);
        }
        else
        {
            holder = (Holder) convertView.getTag();
        }
       MzzycfBean bean =list.get(position);
       holder.mYpmc.setText(bean.getMzzyyp1());
        holder.mYpsl.setText(bean.getMzzycfsl1());

         

      
        return convertView;
    }

    class Holder
    {
        TextView mYpmc;
        TextView mYpsl;
    }

}
