package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.FriendmxBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class FriendmxAdapter extends CommonAdapter<FriendmxBean>{
public FriendmxAdapter(Context context, List<FriendmxBean> datas){
    super(context,datas, R.layout.item_freind);
}
 @Override
    public void convert(ViewHolder holder,FriendmxBean friendBean) {
   holder.setText(R.id.tv_item_friend,friendBean.getBrxm());



    }
}
