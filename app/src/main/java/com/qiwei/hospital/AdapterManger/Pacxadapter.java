package com.qiwei.hospital.AdapterManger;

import android.content.Context;
import android.util.Log;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.PacxBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class Pacxadapter extends CommonAdapter<PacxBean>{
public Pacxadapter(Context context, List<PacxBean> datas){
    super(context,datas, R.layout.item_pacx_mc);
}
 @Override
    public void convert(ViewHolder holder,PacxBean pacxBean) {
  holder.setText(R.id.item_tv_pacx_mc, pacxBean.getCLASSNAME());




    }
}
