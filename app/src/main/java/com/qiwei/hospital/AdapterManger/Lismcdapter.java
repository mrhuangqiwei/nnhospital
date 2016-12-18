package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.LisBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class Lismcdapter extends CommonAdapter<LisBean>{
public Lismcdapter(Context context, List<LisBean> datas){
    super(context,datas, R.layout.item_lis_mc);
}
 @Override
    public void convert(ViewHolder holder,LisBean lisBean) {
   holder.setText(R.id.item_tv_lis_mc,lisBean.getMc());



    }
}
