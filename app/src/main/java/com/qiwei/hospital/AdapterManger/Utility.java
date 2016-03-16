package com.qiwei.hospital.AdapterManger;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * ���listviewǶ�� ScrollView ��ͻ ��Listview ��ʾ��ȫ������
 * */
public class Utility
{

    public static void setListViewHeightBasedOnChildren(ListView listView)
    {
        //��ȡListView��Ӧ��Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
        {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++)
        { //listAdapter.getCount()�������������Ŀ
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); //��������View �Ŀ��
            totalHeight += listItem.getMeasuredHeight(); //ͳ������������ܸ߶�
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        //listView.getDividerHeight()��ȡ�����ָ���ռ�õĸ߶�
        //params.height���õ�����ListView������ʾ��Ҫ�ĸ߶�
        listView.setLayoutParams(params);
    }

}
