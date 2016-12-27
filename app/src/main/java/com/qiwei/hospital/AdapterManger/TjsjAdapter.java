package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.TjglbhBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class TjsjAdapter extends CommonAdapter<TjglbhBean>{
public TjsjAdapter(Context context, List<TjglbhBean> datas){
    super(context,datas, R.layout.item_ryrq);
}
 @Override
    public void convert(ViewHolder holder,TjglbhBean tjglbhBean) {
   holder.setText(R.id.tv_item_ryrq,tjglbhBean.getTjrq());

    }
}
