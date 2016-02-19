package com.qiwei.hospital.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnKeyListener;
import android.widget.ImageView;

import com.qiwei.hospital.R;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Main2Activity extends Activity {
private EditText urlText;
    private Button gobtn;
    String bitmapURL="http://image.so.com/v?q=png%E5%9B%BE%E7%89%87&src=srp&fromurl=http%3A%2F%2Fwww.sc115.com%2Fpsys%2F114823.html#q=png%E5%9B%BE%E7%89%87&src=srp&fromurl=http%3A%2F%2Fwww.sc115.com%2Fpsys%2F114823.html&lightboxindex=11&id=c4d830e4644f6c729ed0212457aaf0ed&multiple=0&itemindex=0&dataindex=11";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t2_main);
        urlText=(EditText)findViewById(R.id.edit_t2_main);
        gobtn=(Button)findViewById(R.id.btn_t2_main);
gobtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new Thread(){
            public void run()
            {
                try{getBitmapurl();

                } catch (Exception e) {
                }
            }
        }.start();
    }
});

      /**  gobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    public void run()
                    {
                        try{
                            openBrowser();
                        } catch (Exception e) {
                        }
                    }
                }.start();


            }
        });
        urlText.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    new Thread(){
                        public void run()
                        {
                            try{
                                openBrowser();
                            } catch (Exception e) {
                            }
                        }
                    }.start();
                    return true;
                }
                return false;
            }
        });


    }
private void openBrowser(){
    Uri uri=Uri.parse(urlText.getText().toString());
    Intent intent =new Intent(Intent.ACTION_VIEW,uri);
    startActivity(intent);
}
       **/
}

public void getBitmapurl(){
    try {
        URL myurl=new URL(bitmapURL);
        URLConnection myconn=myurl.openConnection();
        InputStream in=myconn.getInputStream();
        Bitmap bmp= BitmapFactory.decodeStream(in);
        ImageView iv=(ImageView)findViewById(R.id.img_t2_main);
        iv.setImageBitmap(bmp);

    }catch (Exception e){
        e.printStackTrace();
    }
}
}
