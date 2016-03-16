package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzzycfBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class MzZyCfAdapter extends CommonAdapter<MzzycfBean>  {
   public  MzZyCfAdapter(Context context,List<MzzycfBean>datas){
       super(context,datas, R.layout.item_mzzycf_list);
   }

    @Override
    public void convert(ViewHolder holder,MzzycfBean bean) {
        holder.setText(R.id.item_list_mzzycf_yp1,bean.getMzzyyp1());
        holder.setText(R.id.item_list_mzzycf_sl1,bean.getMzzycfsl1());

    }
}
