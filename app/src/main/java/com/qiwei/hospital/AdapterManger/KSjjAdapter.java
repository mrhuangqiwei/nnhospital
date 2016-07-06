package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.FriendBean;
import com.qiwei.hospital.utils.Bean.KsandysBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class KSjjAdapter extends CommonAdapter<KsandysBean>{
public KSjjAdapter(Context context, List<KsandysBean> datas){
    super(context,datas, R.layout.item_ksjj);
}



    @Override
    public void convert(ViewHolder holder,KsandysBean ksjjBean) {
   holder.setText(R.id.tv_item_ryrq,"    "+ksjjBean.getKsmc());



    }
}
