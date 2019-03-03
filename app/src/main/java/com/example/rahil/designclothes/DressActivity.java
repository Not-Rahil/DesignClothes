package com.example.rahil.designclothes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;

public class DressActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ptt1,ptt2,ptt3,ptt4,ptt5,ptt6,ptt7,ptt8;
    ImageView ptt11,ptt21,ptt31,ptt41,ptt51,ptt61,ptt71,ptt81;
    Intent i ;
    int num = 0;
    ConstraintLayout mylayout;
    Button saveBtn;
    int imgnum = 0;
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
        ptt11 = findViewById(R.id.ptt11);
        ptt21 = findViewById(R.id.ptt21);
        ptt31 = findViewById(R.id.ptt31);
        ptt41 = findViewById(R.id.ptt41);
        ptt51 = findViewById(R.id.ptt51);
        ptt61 = findViewById(R.id.ptt61);
        ptt71 = findViewById(R.id.ptt71);
        ptt81 = findViewById(R.id.ptt81);


        saveBtn = findViewById(R.id.saveBtn);
        mylayout = findViewById(R.id.mylayout);
        saveBtn.setOnClickListener(this);

        ptt1.setImageAlpha(0);
        ptt2.setImageAlpha(0);
        ptt3.setImageAlpha(0);
        ptt4.setImageAlpha(0);
        ptt5.setImageAlpha(0);
        ptt6.setImageAlpha(0);
        ptt7.setImageAlpha(0);
        ptt8.setImageAlpha(0);
        ptt11.setImageAlpha(0);
        ptt21.setImageAlpha(0);
        ptt31.setImageAlpha(0);
        ptt41.setImageAlpha(0);
        ptt51.setImageAlpha(0);
        ptt61.setImageAlpha(0);
        ptt71.setImageAlpha(0);
        ptt81.setImageAlpha(0);

        ptt1.setOnClickListener(this);
        ptt2.setOnClickListener(this);
        ptt3.setOnClickListener(this);
        ptt4.setOnClickListener(this);
        ptt5.setOnClickListener(this);
        ptt6.setOnClickListener(this);
        ptt7.setOnClickListener(this);
        ptt8.setOnClickListener(this);
        ptt11.setOnClickListener(this);
        ptt21.setOnClickListener(this);
        ptt31.setOnClickListener(this);
        ptt41.setOnClickListener(this);
        ptt51.setOnClickListener(this);
        ptt61.setOnClickListener(this);
        ptt71.setOnClickListener(this);
        ptt81.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        i = new Intent(v.getContext(), camera.class);

        switch (v.getId()) {

            case R.id.ptt1:
            case R.id.ptt11:
                startActivityForResult(i, 1);
                break;
            case R.id.ptt2:
            case R.id.ptt21:
                startActivityForResult(i, 2);
                break;
            case R.id.ptt3:
            case R.id.ptt31:
                startActivityForResult(i, 3);
                break;
            case R.id.ptt4:
            case R.id.ptt41:
                startActivityForResult(i, 4);
                break;
            case R.id.ptt5:
            case R.id.ptt51:
                startActivityForResult(i, 5);
                break;
            case R.id.ptt6:
            case R.id.ptt61:
                startActivityForResult(i, 6);
                break;
            case R.id.ptt7:
            case R.id.ptt71:
                startActivityForResult(i, 7);
                break;
            case R.id.ptt8:
            case R.id.ptt81:
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
            case R.id.saveBtn:
                saveImage(mylayout);

        }
    }

    private void saveImage(ConstraintLayout v) {
        //creating bitmap from view
        Bitmap b = Bitmap.createBitmap(v.getWidth() , v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.drawColor(Color.WHITE);
        // v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);

        //saving to storage
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "designclothes");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }
        File tempfile = new File(Environment.getExternalStorageDirectory()+"/designClothes/shirt"+imgnum+".jpg");
        while(tempfile.exists()){
            imgnum++;
            tempfile = new File(Environment.getExternalStorageDirectory()+"/designClothes/shirt"+imgnum+".jpg");
        }
        final File file = new File(Environment.getExternalStorageDirectory()+"/designClothes/shirt"+imgnum+".jpg");

        try {
            FileOutputStream out = new FileOutputStream(file);
            b.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
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
                ptt11.setImageAlpha(255);
                ptt11.setImageBitmap(b);
                break;
            case 2:
                ptt2.setImageAlpha(255);
                ptt2.setImageBitmap(b);
                ptt21.setImageAlpha(255);
                ptt21.setImageBitmap(b);
                break;
            case 3:
                ptt3.setImageAlpha(255);
                ptt3.setImageBitmap(b);
                ptt31.setImageAlpha(255);
                ptt31.setImageBitmap(b);
                break;
            case 4:
                ptt4.setImageAlpha(255);
                ptt4.setImageBitmap(b);
                ptt41.setImageAlpha(255);
                ptt41.setImageBitmap(b);
                break;
            case 5:
                ptt5.setImageAlpha(255);
                ptt5.setImageBitmap(b);
                ptt51.setImageAlpha(255);
                ptt51.setImageBitmap(b);
                break;
            case 6:
                ptt6.setImageAlpha(255);
                ptt6.setImageBitmap(b);
                ptt61.setImageAlpha(255);
                ptt61.setImageBitmap(b);
                break;
            case 7:
                ptt7.setImageAlpha(255);
                ptt7.setImageBitmap(b);
                ptt71.setImageAlpha(255);
                ptt71.setImageBitmap(b);
                break;
            case 8:
                ptt8.setImageAlpha(255);
                ptt8.setImageBitmap(b);
                ptt81.setImageAlpha(255);
                ptt81.setImageBitmap(b);
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
