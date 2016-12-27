package com.qiwei.hospital.AdapterManger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzblBean;
import com.qiwei.hospital.utils.Bean.MzzycfBean;
import com.qiwei.hospital.utils.Bean.TjglzbjgBean;
import com.qiwei.hospital.utils.Bean.TjjgBean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/15.
 */
public class TjzbjgAdapter extends BaseAdapter {
    private List<MzzycfBean> mDatas;
    private LayoutInflater mInflater;
    private List<TjjgBean>mdata;
    private Context c;

    public TjzbjgAdapter(Context context, List<TjjgBean> datas){
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
    convertView=mInflater.inflate(R.layout.item_tjjg,parent,false);
    holder=new Holder();
    holder.mKsmc=(TextView)convertView.findViewById(R.id.tjgl_ksmc);
    holder.mZhmc=(TextView)convertView.findViewById(R.id.tjgl_zhmc);
    holder.mYsxm=(TextView)convertView.findViewById(R.id.tjgl_tjysxm);
    holder.mListView=(ListView)convertView.findViewById(R.id.list_tjgl_inner);

    convertView.setTag(holder);
}
else
{
    holder = (Holder) convertView.getTag();
}

        if(mdata.get(position)!=null){

            TjjgBean tjjgBean=mdata.get(position);
            holder.mKsmc.setText(tjjgBean.getKsmc());
            holder.mZhmc.setText(tjjgBean.getZhmc());
            holder.mYsxm.setText(tjjgBean.getYsxm());
            String  tjzb=tjjgBean.getTjzbjg();
            try{
                JSONObject object =new JSONObject(tjzb);
                final List<TjglzbjgBean> tjglzbjgBeans =new ArrayList<TjglzbjgBean>();
                JSONArray lisData=object.getJSONArray("GetTjzbjg");
                for(int i=0;i<lisData.length();i++){
                    JSONObject lismc=lisData.getJSONObject(i);
                    TjglzbjgBean tjglzbjgBean = JSON.parseObject(lismc.toString(), TjglzbjgBean.class);
                    tjglzbjgBeans.add(tjglzbjgBean);}
                MTjglItemAdapter mTjglItemAdapter=new MTjglItemAdapter(c,tjglzbjgBeans);
                holder.mListView.setAdapter(mTjglItemAdapter);
                Utility.setListViewHeightBasedOnChildren(holder.mListView);
            }
            catch (Exception e){
            }
        }

        return convertView;
    }


    private  class  Holder {
        TextView mKsmc;
        TextView mZhmc;
        TextView mYsxm;
        ListView mListView;
    }

    private void parseJson(String json) {

    }










}
