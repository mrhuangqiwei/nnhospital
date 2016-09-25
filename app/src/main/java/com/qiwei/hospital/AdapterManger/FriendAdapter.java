package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.MzzlczBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class FriendAdapter extends CommonAdapter<FriendBean>{
public FriendAdapter(Context context, List<FriendBean> datas){
    super(context,datas, R.layout.item_freind);
}
 @Override
    public void convert(ViewHolder holder,FriendBean friendBean) {
   holder.setText(R.id.tv_item_friend,friendBean.getBrxm());



    }
}
