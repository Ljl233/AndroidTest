package com.ljl.cameraalbumtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;

    private ImageView picture;
    private Button button;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        picture = findViewById(R.id.picture);//获取ImageView实例
        button = findViewById(R.id.take_photo);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建File对象，用于储存拍照后的图片
                //getExternalCacheDir()得到应用关联缓存目录：SD卡中专门用于存放当前应用缓存数据的位置
                Log.e("缓存路径", String.valueOf(getExternalCacheDir()));
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT >= 24) {//Android 7.0
                    imageUri = FileProvider.getUriForFile(MainActivity.this,
                            "com.ljl,cameraalbumtest.fileprovider", outputImage);
                    //调用getUriForFile方法将File对象转换成一个封装过的Uri对象
                    //从Android7.0开始直接使用本地真实路径的Uri被认为是不安全的，会抛出FileUriExposedException异常
                    //FileProvider是一种特殊的内容提供器，选择性的将封装过的Uri共享给外部，提高安全性
                } else {
                    imageUri = Uri.fromFile(outputImage);//将File转换成Uri对象。标识着图片的本地真实路径
                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//输入指定图片的输出地址
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }

    //Button 的点击事件 ，onClick属性，调用摄像头
    public void buttonOnClick(View view) {

    }

    //startActivityForResult（）来启动活动，因此拍完照后会有结果返回onActivityResult（）
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(imageUri));
                        //将jpg照片解析成BitMap对象，然后设置成ImageView中显示
                        picture.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
