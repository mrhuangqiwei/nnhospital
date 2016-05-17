package com.qiwei.hospital.AdapterManger;

import android.content.Context;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.MzzlczBean;
import com.qiwei.hospital.utils.Bean.item_yy_ksBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class YyksAdapter extends CommonAdapter<item_yy_ksBean> {
    public  YyksAdapter(Context context,List<item_yy_ksBean> datas){
        super(context,datas, R.layout.item_yy_ks);
    }

    @Override
    public void convert(ViewHolder holder, item_yy_ksBean item_yy_ksBean) {
        holder.setText(R.id.tv_list_yyks,item_yy_ksBean.getKsmc());
    }
}
