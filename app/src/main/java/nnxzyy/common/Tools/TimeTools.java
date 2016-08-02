package nnxzyy.common.Tools;

import android.util.Log;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**开发人：黄启位
 * 开发时间 7.10
 * 功能时间工具
 * Created by Administrator on 2016/7/10.
 */
public class TimeTools {
    public static String callTime() {
        long backTime = new Date().getTime();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(backTime));
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        String time = "" + year + month + date + hour + minute + second;
        //Log.i("CurrentTime", "^^^^^^^^^^^^^" + time + "^^^^^^^^^^^^^");
        return time;
    }

    /**
     * 获取当前时间返回值为：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
}
