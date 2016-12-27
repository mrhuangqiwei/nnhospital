package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.RysjBean;
import com.qiwei.hospital.utils.Bean.TjglbhBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 *开发人：黄启位
 * 时间2016-12-26
 */
public class Tjgl_jbxxAdapter extends CommonAdapter<TjglbhBean>{
public Tjgl_jbxxAdapter(Context context, List<TjglbhBean> datas){
    super(context,datas, R.layout.item_tjbr);
}



    @Override
    public void convert(ViewHolder holder,TjglbhBean rysjBean) {
   holder.setText(R.id.tv_item_tjgl_brxm,rysjBean.getXm());



    }
}
