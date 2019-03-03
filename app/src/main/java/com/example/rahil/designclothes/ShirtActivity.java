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

public class ShirtActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView rightPart, leftPart, collarshirtleft, collarshirtright, shirtback;
    Intent i ;
    int num = 0;
    int rightpath = R.string.Shirtrightpath;
    int leftpath = R.string.Shirtleftpath;
    int collarshirtpath = R.string.collarshirt;
    int shirtbackpath = R.string.shirtback;

    ConstraintLayout mylayout;
    Button saveBtn;
    int imgnum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shirt);
        rightPart= findViewById(R.id.rightPart);
        leftPart= findViewById(R.id.leftPart);
        collarshirtleft = findViewById(R.id.collarshirtleft);
        collarshirtright = findViewById(R.id.collarshirtright);
        shirtback = findViewById(R.id.shirtback);

        saveBtn = findViewById(R.id.saveBtn);
        mylayout = findViewById(R.id.mylayout);
        saveBtn.setOnClickListener(this);


        rightPart.setImageAlpha(0);
        leftPart.setImageAlpha(0);
        collarshirtleft.setImageAlpha(0);
        collarshirtright.setImageAlpha(0);
        shirtback.setImageAlpha(0);

        rightPart.setOnClickListener(this);
        leftPart.setOnClickListener(this);
        collarshirtleft.setOnClickListener(this);
        collarshirtright.setOnClickListener(this);
        shirtback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        i = new Intent(v.getContext(), camera.class);

        switch (v.getId()) {

            case R.id.rightPart:
                startActivityForResult(i, 1);
                break;
            case R.id.leftPart:
                startActivityForResult(i, 2);
                break;
            case R.id.collarshirtright:
            case R.id.collarshirtleft:
                startActivityForResult(i, 3);
                break;
            case R.id.shirtback:
                startActivityForResult(i, 4);
                break;
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
                ImageOperations(num, rightpath, 1);
                break;
            case 2:
                ImageOperations(num, leftpath, 2);
                break;
            case 3:
                ImageOperations(num, collarshirtpath, 3);
                break;
            case 4:
                ImageOperations(num, shirtbackpath, 4);
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
                rightPart.setImageAlpha(255);
                rightPart.setImageBitmap(b);
                break;
            case 2:
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
                break;
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



}
