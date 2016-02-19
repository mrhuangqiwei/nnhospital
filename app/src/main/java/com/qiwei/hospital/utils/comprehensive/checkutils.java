package com.qiwei.hospital.utils.comprehensive;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/2/5.
 */
public class checkutils {


    /**
     * 检查并Toast提示错误的密码长度
     * @param password
     * @param name
     * @return
     */
    public static boolean checkLength(EditText password, String name) {
        boolean ret = true;
        String toastString = "";
        Context context = password.getContext();
        int minLength = 6;
        int maxLength =12;
        String passwordString = password.getText().toString();
        if (passwordString.isEmpty()) {
            toastString = "不能为空";
            ret = false;
        } else if (passwordString.length() < minLength) {
            toastString = String.format("不能少于%d位", minLength);
            ret = false;
        } else if (passwordString.length() > maxLength) {
            toastString = String.format("不能多于%d位", maxLength);
            ret = false;
        }
        if (!ret) {
            Toast.makeText(context, name + toastString, Toast.LENGTH_SHORT).show();
            password.requestFocus();
        }
        return ret;
    }

}
