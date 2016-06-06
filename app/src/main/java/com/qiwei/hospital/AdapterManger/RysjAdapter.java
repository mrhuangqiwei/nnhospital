package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.RysjBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class RysjAdapter extends CommonAdapter<RysjBean>{
public RysjAdapter(Context context, List<RysjBean> datas){
    super(context,datas, R.layout.item_ryrq);
}



    @Override
    public void convert(ViewHolder holder,RysjBean rysjBean) {
   holder.setText(R.id.tv_item_ryrq,rysjBean.getRysj());



    }
}
