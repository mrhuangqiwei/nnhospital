package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.LisBean;
import com.qiwei.hospital.utils.Bean.LismxBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7
 */
public class Lismxdapter extends CommonAdapter<LismxBean>{
public Lismxdapter(Context context, List<LismxBean> datas){
    super(context,datas, R.layout.item_lis_jg);
}
 @Override
    public void convert(ViewHolder holder,LismxBean lismxBean) {
   holder.setText(R.id.lis_item_xmmc,lismxBean.getZwmc());
     holder.setText(R.id.lis_item_jc,lismxBean.getYwmc());
     if(lismxBean.getZt().equals("1")){
         holder.setImageResource(R.id.lis_item_zt,R.mipmap.img_up);
     }
     else  if(lismxBean.getZt().equals("0")){
         holder.setImageResource(R.id.lis_item_zt,R.mipmap.img_down);
     }
     else { }
     holder.setText(R.id.lis_item_jg,lismxBean.getJg());
     holder.setText(R.id.lis_item_dw,lismxBean.getDw());
     holder.setText(R.id.lis_item_ckfw,lismxBean.getCkz_t());
    }
}
