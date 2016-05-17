package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzzlczBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MzzlAdapter extends CommonAdapter<MzzlczBean>{
public  MzzlAdapter(Context context,List<MzzlczBean> datas){
    super(context,datas, R.layout.item_mz_zlcz);
}



    @Override
    public void convert(ViewHolder holder, MzzlczBean mzzlczBean) {
   holder.setText(R.id.tv_zlcz_xmmc,mzzlczBean.getXmmc());
        holder.setText(R.id.tv_zlcz_sl,mzzlczBean.getSl());
        holder.setText(R.id.tv_zlcz_dj,mzzlczBean.getDj());
        holder.setText(R.id.tv_zlcz_je,mzzlczBean.getJe());


    }
}
