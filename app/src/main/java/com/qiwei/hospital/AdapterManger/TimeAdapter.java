package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.RysjBean;
import com.qiwei.hospital.utils.Bean.TimeBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class TimeAdapter extends CommonAdapter<TimeBean>{
public TimeAdapter(Context context, List<TimeBean> datas){
    super(context,datas, R.layout.item_ryrq);
}



    @Override
    public void convert(ViewHolder holder,TimeBean rysjBean) {
   holder.setText(R.id.tv_item_ryrq,rysjBean.getGhrq());



    }
}
