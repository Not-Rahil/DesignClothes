package com.example.rahil.designclothes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class DressActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ptt1,ptt2,ptt3,ptt4,ptt5,ptt6,ptt7,ptt8;
    Intent i ;
    int num = 0;
    int pttpath1 = R.string.ptt1;
    int pttpath2 = R.string.ptt2;
    int pttpath3 = R.string.ptt3;
    int pttpath4 = R.string.ptt4;
    int pttpath5 = R.string.ptt5;
    int pttpath6 = R.string.ptt6;
    int pttpath7 = R.string.ptt7;
    int pttpath8 = R.string.ptt8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dress);
        ptt1 = findViewById(R.id.ptt1);
        ptt2 = findViewById(R.id.ptt2);
        ptt3 = findViewById(R.id.ptt3);
        ptt4 = findViewById(R.id.ptt4);
        ptt5 = findViewById(R.id.ptt5);
        ptt6 = findViewById(R.id.ptt6);
        ptt7 = findViewById(R.id.ptt7);
        ptt8 = findViewById(R.id.ptt8);
/*
        ptt1.setImageAlpha(0);
        ptt2.setImageAlpha(0);
        ptt3.setImageAlpha(0);
        ptt4.setImageAlpha(0);
        ptt5.setImageAlpha(0);
        ptt6.setImageAlpha(0);
        ptt7.setImageAlpha(0);
        ptt8.setImageAlpha(0);
*/
        ptt1.setOnClickListener(this);
        ptt2.setOnClickListener(this);
        ptt3.setOnClickListener(this);
        ptt4.setOnClickListener(this);
        ptt5.setOnClickListener(this);
        ptt6.setOnClickListener(this);
        ptt7.setOnClickListener(this);
        ptt8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        i = new Intent(v.getContext(), camera.class);

        switch (v.getId()) {

            case R.id.ptt1:
                startActivityForResult(i, 1);
                break;
            case R.id.ptt2:
                startActivityForResult(i, 2);
                break;
            case R.id.ptt3:
                startActivityForResult(i, 3);
                break;
            case R.id.ptt4:
                startActivityForResult(i, 4);
                break;
            case R.id.ptt5:
                startActivityForResult(i, 5);
                break;
            case R.id.ptt6:
                startActivityForResult(i, 6);
                break;
            case R.id.ptt7:
                startActivityForResult(i, 7);
                break;
            case R.id.ptt8:
                startActivityForResult(i, 8);
                break;
            /*case R.id.rightsleeve:
            case R.id.leftsleeve:
                startActivityForResult(i, 2);
                break;
            case R.id.rightcorner:
            case R.id.leftcorner:
                startActivityForResult(i, 3);
                break;
            case R.id.collar:
                startActivityForResult(i, 4);
                break;
                */
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        num = data.getIntExtra("image", 0);
        switch (requestCode) {
            case 1:
                ImageOperations(num, pttpath1, 1);
                break;
            case 2:
                ImageOperations(num, pttpath2, 2);
                break;
            case 3:
                ImageOperations(num, pttpath3, 3);
                break;
            case 4:
                ImageOperations(num, pttpath4, 4);
                break;
            case 5:
                ImageOperations(num, pttpath5, 5);
                break;
            case 6:
                ImageOperations(num, pttpath6, 6);
                break;
            case 7:
                ImageOperations(num, pttpath7, 7);
                break;
            case 8:
                ImageOperations(num, pttpath8, 8);
                break;
          /*  case 3:
                ImageOperations(num, strcorner, 3);
                break;
            case 4:
                ImageOperations(num, strcollar, 4);
                break;
        */
        }

    }

    private void ImageOperations(int num, int strpath, int img) {
        String photoPath = Environment.getExternalStorageDirectory() + "/designClothes/pic" + num + ".jpg";

        Bitmap rotated = BitmapFactory.decodeFile(photoPath);
        //rotated = Bitmap.createScaledBitmap(rotated , olay.getWidth(), olay.getHeight(), true);
        Bitmap bitmap = rotateBitmap(rotated);
        Bitmap b = convertToTorso(bitmap, strpath);
        //  Bitmap c;
        //Matrix matrix;
        // c = Bitmap.createScaledBitmap(b,b.getWidth()/2,b.getHeight()/2,true);Log.d("isize2", b.getWidth()+" "+b.getHeight());
        switch (img) {
            case 1:
                ptt1.setImageAlpha(255);
                ptt1.setImageBitmap(b);
                break;
            case 2:
                ptt2.setImageAlpha(255);
                ptt2.setImageBitmap(b);
                break;
            case 3:
                ptt3.setImageAlpha(255);
                ptt3.setImageBitmap(b);
                break;
            case 4:
                ptt4.setImageAlpha(255);
                ptt4.setImageBitmap(b);
                break;
            case 5:
                ptt5.setImageAlpha(255);
                ptt5.setImageBitmap(b);
                break;
            case 6:
                ptt6.setImageAlpha(255);
                ptt6.setImageBitmap(b);
                break;
            case 7:
                ptt7.setImageAlpha(255);
                ptt7.setImageBitmap(b);
                break;
            case 8:
                ptt8.setImageAlpha(255);
                ptt8.setImageBitmap(b);
                break;
            /*case 2:
                leftPart.setImageAlpha(255);
                leftPart.setImageBitmap(b);
                break;
            case 3:
                //       matrix = new Matrix();
                //     matrix.preScale(-1.0f, 1.0f);
                //   c = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
                collarshirtleft.setImageAlpha(255);
                collarshirtright.setImageAlpha(255);
                collarshirtleft.setImageBitmap(b);
                collarshirtright.setImageBitmap(b);
                break;
            case 4:
                shirtback.setImageAlpha(255);
                shirtback.setImageBitmap(b);
                break; */
            /* case 2:
                matrix = new Matrix();
                matrix.preScale(-1.0f, 1.0f);
                c = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
                rsleeve.setImageAlpha(255);
                lsleeve.setImageAlpha(255);
                lsleeve.setImageBitmap(b);
                rsleeve.setImageBitmap(c);
                break;
            case 3:
                matrix = new Matrix();
                matrix.preScale(-1.0f, 1.0f);
                c = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), matrix, true);
                rightcorner.setImageAlpha(255);
                leftcorner.setImageAlpha(255);
                rightcorner.setImageBitmap(b);
                leftcorner.setImageBitmap(b);
                break;
            case 4:
                collar.setImageAlpha(255);
                collar.setImageBitmap(b);
                break;
        */
        }

    }


    private Bitmap convertToTorso(Bitmap src, int strpath) {
        Path p = resizePath(PathParser.createPathFromPathData(getString(strpath)), src.getWidth(), src.getHeight());
        return BitmapUtils.getCroppedBitmap(src, p);
    }


    private Bitmap rotateBitmap(Bitmap photo) {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedPhoto = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
        //   rotatedPhoto = Bitmap.createScaledBitmap(rotatedPhoto, olay.getWidth(), olay.getHeight(), true);
        //Bitmap rotatedPhoto = Bitmap.createScaledBitmap(rotatedPhoto1,photo.getWidth()/2,photo.getHeight()/2,false);
        return rotatedPhoto;
    }

    public static Path resizePath(Path path, float width, float height) {
        RectF bounds = new RectF(0, 0, width, height);
        Path resizedPath = new Path(path);
        RectF src = new RectF();
        resizedPath.computeBounds(src, true);

        Matrix resizeMatrix = new Matrix();
        resizeMatrix.setRectToRect(src, bounds, Matrix.ScaleToFit.CENTER);
        resizedPath.transform(resizeMatrix);

        return resizedPath;
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
