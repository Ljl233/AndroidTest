package com.ljl.runtimepermissiontest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 1. 检查权限
 * 2. 获取权限
 * 3. 跳转相册，相机
 * 4. 获取url
 * 5. 根据url加载图片
 */
public class UpImageActivity extends AppCompatActivity {

    private SimpleDraweeView mIvLoadImage;
    private String[] mPermissions = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
    private static final int REQUEST_CODE_READ = 1;
    private static final int REQUEST_CODE_WRITE = 2;
    private static final int REQUEST_CODE_CAMERA = 3;
    private static final int CHOOSE_PHOTO = 4;
    private static final int TAKE_PHOTO = 5;
    private static final String TAG = "UpImageActivity";
    private String currentPhotoPath;
    File image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_image);

        mIvLoadImage = findViewById(R.id.image_up);
        mIvLoadImage.setOnClickListener(v -> {
            if (checkAndRequestPermission()) {
                Toast.makeText(this, "已授权。。", Toast.LENGTH_SHORT).show();
                showPopWindow();
            } else Toast.makeText(this, "请授权。。。", Toast.LENGTH_SHORT).show();
//            checkAndRequestPermission();
//            choosePhoto();
        });
    }

    private void showPopWindow() {
        View popView = LayoutInflater.from(this).inflate(R.layout.pop_photo_and_camera, null);
        Button camera = popView.findViewById(R.id.pop_camera);
        Button photo = popView.findViewById(R.id.pop_photo);
        Button dismiss = popView.findViewById(R.id.pop_dismiss);

        //获取手机的宽高
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels / 3;
        final PopupWindow popupWindow = new PopupWindow(popView, width, height, true);

        //点击外部 弹窗消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(popView.getRootView(), Gravity.BOTTOM, 0, 0);

        camera.setOnClickListener(v -> {
            takePhoto();
        });

        photo.setOnClickListener(v -> {
            popupWindow.dismiss();
            openAlbum();
        });

        dismiss.setOnClickListener(v -> {
            popupWindow.dismiss();
        });
    }

    private void openAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {//判断是否有能够处理这个Action的应用
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(
                        this,
                        "com.ljl.runtimepermissiontest.fileprovider",//和file_paths里的一样
                        photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK)
            switch (requestCode) {
                case CHOOSE_PHOTO:
                    Uri uri = data.getData();
                    Log.e(TAG, "choose photo uri:" + uri);
                    mIvLoadImage.setImageURI(uri);
                    break;
                case TAKE_PHOTO:
                    Uri uri1 = new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME)
                            .path(currentPhotoPath)
                            .build();
                    Log.e(TAG, "uri1:" + uri1);
                    mIvLoadImage.setImageURI(uri1);

                    break;
            }
    }

    //1. 检查权限
    boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    //2. 获取权限
    private boolean checkAndRequestPermission() {
        boolean hasRead = false, hasWrite = false, hasCamera = false;
        if (!hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE))
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_READ);
        else hasRead = true;
        if (!hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE);
        else hasWrite = true;
        if (!hasPermission(Manifest.permission.CAMERA))
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_CAMERA);
        else hasCamera = true;

        return hasCamera && hasRead && hasWrite;
    }

    private File createImageFile() throws IOException {
        //1. set the unique name
        String dataStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + dataStamp + "_";
        //2. set the image file address
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);//getExternalFilesDir是获得外部SD卡的路径

        Log.e(TAG, "storageDir:" + storageDir.toString());
        image = File.createTempFile(
                imageFileName,  //prefix
                ".jpg",   //suffix
                storageDir      //directory
        );
        currentPhotoPath = image.getAbsolutePath();
        Log.e(TAG, "currentPhotoPath:" + currentPhotoPath.toString());
        return image;
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = mIvLoadImage.getWidth();
        int targetH = mIvLoadImage.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        mIvLoadImage.setImageBitmap(bitmap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_READ:
            case REQUEST_CODE_WRITE:
            case REQUEST_CODE_CAMERA:
        }
    }
}
