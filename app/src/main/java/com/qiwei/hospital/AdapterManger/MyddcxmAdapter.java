package com.qiwei.hospital.AdapterManger;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qiwei.hospital.R;
import com.qiwei.hospital.utils.Bean.KsandysBean;
import com.qiwei.hospital.utils.Bean.MyddcBean;
import com.qiwei.hospital.utils.comprehensive.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class MyddcxmAdapter extends CommonAdapter<MyddcBean> {
public MyddcxmAdapter(Context context, List<MyddcBean> datas){
    super(context, datas, R.layout.item_myddc);
}



    @Override
    public void convert(ViewHolder holder,final MyddcBean myddcBean) {
         holder.setText(R.id.item_tv_myddc, myddcBean.getMyddcmc());
        final ImageView imageView1=holder.getView(R.id.item_img1_myddc);
        final ImageView imageView2=holder.getView(R.id.item_img2_myddc);
        final ImageView imageView3=holder.getView(R.id.item_img3_myddc);
        final ImageView imageView4=holder.getView(R.id.item_img4_myddc);
        final ImageView imageView5=holder.getView(R.id.item_img5_myddc);

        final TextView textView=holder.getView(R.id.item_tv_myddc_fs);
        if(!myddcBean.getIsimg1()){
            textView.setText("1");
            imageView1.setImageResource(R.mipmap.btn_myd_true_img);}
        else{
            textView.setText("1");
            imageView1.setImageResource(R.mipmap.btn_myd_true_img);
        }
        if(myddcBean.getIsimg2()){
            textView.setText("2");
            myddcBean.setIsimg1(true);
            myddcBean.setIsimg2(true);
            imageView2.setImageResource(R.mipmap.btn_myd_true_img);
        }
       else if(!myddcBean.getIsimg2()){
            textView.setText("1");
            imageView1.setImageResource(R.mipmap.btn_myd_true_img);
            imageView2.setImageResource(R.mipmap.btn_myd_false_img);}

        if(myddcBean.getIsimg3()){
            textView.setText("3");
            imageView3.setImageResource(R.mipmap.btn_myd_true_img);
        }
        else if(!myddcBean.getIsimg3()&&myddcBean.getIsimg2()){
            textView.setText("2");
            imageView3.setImageResource(R.mipmap.btn_myd_false_img);
        }
        else if(!myddcBean.getIsimg3()&&!myddcBean.getIsimg2()){
            textView.setText("1");
            imageView3.setImageResource(R.mipmap.btn_myd_false_img);
            imageView2.setImageResource(R.mipmap.btn_myd_false_img);
        }
        if(myddcBean.getIsimg4()){
            textView.setText("4");
            imageView4.setImageResource(R.mipmap.btn_myd_true_img);
        }
        else if (!myddcBean.getIsimg4()&&myddcBean.getIsimg3()){
            textView.setText("3");
            imageView4.setImageResource(R.mipmap.btn_myd_false_img);
            imageView3.setImageResource(R.mipmap.btn_myd_true_img);
        }
        else if(!myddcBean.getIsimg4()&&!myddcBean.getIsimg3()&&myddcBean.getIsimg2()){
            textView.setText("2");
            imageView4.setImageResource(R.mipmap.btn_myd_false_img);
        }
        else if(!myddcBean.getIsimg4()&&!myddcBean.getIsimg3()&&!myddcBean.getIsimg2()){
            textView.setText("1");
            imageView4 .setImageResource(R.mipmap.btn_myd_false_img);
            imageView2.setImageResource(R.mipmap.btn_myd_false_img);
        }
         if(myddcBean.getIsimg5()){
             textView.setText("5");
             imageView5.setImageResource(R.mipmap.btn_myd_true_img);
         }
        else if(!myddcBean.getIsimg5()&&myddcBean.getIsimg4()){
             textView.setText("4");
             imageView5.setImageResource(R.mipmap.btn_myd_false_img);
         }
         else if (!myddcBean.getIsimg5()&&!myddcBean.getIsimg4()&&myddcBean.getIsimg3()){
             textView.setText("3");
             imageView5  .setImageResource(R.mipmap.btn_myd_false_img);
             imageView3.setImageResource(R.mipmap.btn_myd_true_img);
         }
         else if(!myddcBean.getIsimg5()&&!myddcBean.getIsimg4()&&!myddcBean.getIsimg3()&&myddcBean.getIsimg2()){
             textView.setText("2");
             imageView5.setImageResource(R.mipmap.btn_myd_false_img);
         }
         else if(!myddcBean.getIsimg5()&&!myddcBean.getIsimg4()&&!myddcBean.getIsimg3()&&!myddcBean.getIsimg2()){
             textView.setText("1");
             imageView5 .setImageResource(R.mipmap.btn_myd_false_img);
             imageView2.setImageResource(R.mipmap.btn_myd_false_img);
         }

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myddcBean.setFenshu(1);
                myddcBean.setIsimg1(true);;
                imageView1.setImageResource(R.mipmap.btn_myd_true_img);
                textView.setText("1");
                myddcBean.setIsimg2(false);
                imageView2.setImageResource(R.mipmap.btn_myd_false_img);
                myddcBean.setIsimg3(false);
                imageView3.setImageResource(R.mipmap.btn_myd_false_img);
                myddcBean.setIsimg4(false);
                imageView4.setImageResource(R.mipmap.btn_myd_false_img);
                myddcBean.setIsimg5(false);
                imageView5.setImageResource(R.mipmap.btn_myd_false_img);

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myddcBean.setFenshu(2);
                textView.setText("2");
                myddcBean.setIsimg2(true);
                imageView2.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg3(false);
                imageView3.setImageResource(R.mipmap.btn_myd_false_img);
                myddcBean.setIsimg4(false);
                imageView4.setImageResource(R.mipmap.btn_myd_false_img);
                myddcBean.setIsimg5(false);
                imageView5.setImageResource(R.mipmap.btn_myd_false_img);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("3");
                myddcBean.setFenshu(3);
                myddcBean.setIsimg2(true);
                imageView2.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg3(true);
                imageView3.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg4(false);
                imageView4.setImageResource(R.mipmap.btn_myd_false_img);
                myddcBean.setIsimg5(false);
                imageView5.setImageResource(R.mipmap.btn_myd_false_img);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("4");
                myddcBean.setIsimg2(true);
                myddcBean.setFenshu(4);
                imageView2.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg3(true);
                imageView3.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg4(true);
                imageView4.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg5(false);
                imageView5.setImageResource(R.mipmap.btn_myd_false_img);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myddcBean.setFenshu(5);
                textView.setText("5");
                myddcBean.setIsimg2(true);
                imageView2.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg3(true);
                imageView3.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg4(true);
                imageView4.setImageResource(R.mipmap.btn_myd_true_img);
                myddcBean.setIsimg5(true);
                imageView5.setImageResource(R.mipmap.btn_myd_true_img);
            }
        });






    }


}
