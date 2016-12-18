package nnxzyy.common.Tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 开发时间 2016-10-30
 * 开发人：黄启位
 * 功能描述：从数据流中获得数据
 */
public class StreamTool {
    /*
    * 从数据流中获得数据
    */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static Bitmap convertStringToIcon(String st)
    {Bitmap bitmap = null;
        try
        {
            byte[] bitmapArray;
        bitmapArray = Base64.decode(st, Base64.DEFAULT);
            bitmap =BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            String path = "CameraTest";

            saveImage(bitmap, path, "12345",100);
            Log.e("-------222","rrrrrrrrr");
            return bitmap;
        }
        catch (Exception e)
        {
            return null;
        }

    }
    //保存图片
    public static void saveImage(Bitmap bmp, String path, String filename, int quality)
    {

        if (bmp != null) {
            try {
                                /* 文件夹不存在就创建 */
                File f = new File(Environment.getExternalStorageDirectory(),path);
                if (!f.exists()) {
                    f.mkdir();
                }
                                /* 保存图片文件 */
                File n = null;
                n = new File(f, filename + ".jpg");
                FileOutputStream bos = new FileOutputStream(n.getAbsolutePath());
                                /* 文件转换 */
                bmp.compress(Bitmap.CompressFormat.JPEG, quality, bos);
                                /* 调用flush()方法，更新BufferStream */
                bos.flush();
                                /* 结束OutputStream */
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!bmp.isRecycled())
            bmp.recycle();
    }
}
