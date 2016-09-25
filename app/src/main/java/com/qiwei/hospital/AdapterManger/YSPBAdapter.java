package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.YSBCBean;
import com.qiwei.hospital.utils.Bean.YspbBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * 创建时间2016-9-20
 * 开发人：黄启位
 * 功能描述：adapter 网格显示医生排班信息
 */
class YSPBAdapter extends CommonAdapter<YSBCBean> {
    public YSPBAdapter(Context context, List<YSBCBean> datas) {
        super(context, datas, R.layout.item_grideview);
    }


    @Override
    public void convert(ViewHolder holder, YSBCBean  ysbcBean) {
        holder.setText(R.id.item_grid_ysxm, ysbcBean.getCzyxm());
        holder.setText(R.id.item_grid_yszc,ysbcBean.getZcmc());
    }
}