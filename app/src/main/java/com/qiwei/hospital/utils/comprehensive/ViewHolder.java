
package com.qiwei.hospital.utils.comprehensive;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qiwei.hospital.AdapterManger.CommonAdapter;
import com.qiwei.hospital.utils.Bean.MzzycfBean;


/**
 * Created by Administrator on 2016/3/7.
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private  int mPosition;
    private  View mConvertView;
    public ViewHolder(Context context ,ViewGroup parent,int layoutid,int position){
        this.mPosition=position;
        this.mViews=new SparseArray<>();
        mConvertView= LayoutInflater.from(context).inflate(layoutid,parent,false);
        mConvertView.setTag(this);


    }
    public  static  ViewHolder get(Context context,View convertView,
                                   ViewGroup parent,int layoutid,int position){
        if(convertView==null){
            return new ViewHolder(context,parent,layoutid,position);
        }else {
            ViewHolder holder=(ViewHolder)convertView.getTag();
            holder.mPosition=position;
            return holder;
        }
    }

    /**
     * 通过viewID获取控件
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view =mViews.get(viewId);
        if(view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }


    public View getmConvertView(){
        return  mConvertView;
    }

    /**
     * 设置TextView的值
     * @param viewId
     * @param text
     * @return
     */
    public  ViewHolder setText(int viewId,String text){
        TextView tv=getView(viewId);
        tv.setText(text);
        return  this;
    }
    public ViewHolder setImageResource(int viewId,Bitmap bitmap){
        ImageView view=getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }
    public  ViewHolder  setMzbllist(int viewId, CommonAdapter<MzzycfBean> adapter){
        ListView view=getView(viewId);
      view.setAdapter(adapter);
        return this;
    }



    public ViewHolder setImageURI(int viewId,String url){
        ImageView view=getView(viewId);
        //Imageloader.getInstance.load(view,url);view.setImageResource(resId);
        return this;
    }


}
