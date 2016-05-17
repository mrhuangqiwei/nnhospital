package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.MyYyghBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MyYyAdapter extends CommonAdapter<MyYyghBean>{
public MyYyAdapter(Context context, List<MyYyghBean> datas){
    super(context,datas, R.layout.item_myyyinfo);
}



    @Override
         public void convert(ViewHolder holder,MyYyghBean myYyghBean) {
         holder.setText(R.id.tv_item_myyyinfo_brxm,myYyghBean.getBrxm());
        holder.setText(R.id.item_tv_myyinfo_ys,myYyghBean.getYyysxm());
        holder.setText(R.id.tv_item_yyinfo_jzrq,myYyghBean.getYyrq());
    }
}
