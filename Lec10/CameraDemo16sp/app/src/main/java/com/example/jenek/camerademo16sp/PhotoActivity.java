package com.example.jenek.camerademo16sp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import stanford.androidlib.*;

public class PhotoActivity extends AppCompatActivity {
    private static final int REQ_CODE_TAKE_PICTURE = 30210; // 1-65535


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
    }

    public void takePictureClick(View view) {
        // Stanford Android library
        //requestPermissions("android.permission.CAMERA");

        Intent picIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(picIntent, REQ_CODE_TAKE_PICTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQ_CODE_TAKE_PICTURE){
            // I have come back from taking a photo
            Bitmap bmp = (Bitmap) intent.getExtras().get("data");

            ImageView photo = (ImageView) findViewById(R.id.photo);
            photo.setImageBitmap(bmp);

        }
    }
}
