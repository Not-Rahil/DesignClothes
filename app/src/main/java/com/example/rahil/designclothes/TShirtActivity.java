package com.example.rahil.designclothes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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

public class TShirtActivity extends AppCompatActivity implements View.OnClickListener {
    // ImageView tshirt;
    int strTorso = R.string.torso;
    int strleftsleeve = R.string.leftsleeve;
    int strcorner = R.string.cornersleeve;
    ConstraintLayout mylayout;
    int strcollar = R.string.collar;
    ImageView olay, lsleeve, rsleeve, leftcorner, rightcorner, collar , tshirtstatic;
    Intent i;
    Button saveBtn;
    int imgnum = 0;
    int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tshirt);
        //   tshirt = findViewById(R.id.tshirt_main);
        olay = findViewById(R.id.torso);
        lsleeve = findViewById(R.id.leftsleeve);
        rsleeve = findViewById(R.id.rightsleeve);
        leftcorner = findViewById(R.id.leftcorner);
        rightcorner = findViewById(R.id.rightcorner);
        collar = findViewById(R.id.collar);
        tshirtstatic = findViewById(R.id.tshirt_static);


        saveBtn = findViewById(R.id.saveBtn);
        mylayout = findViewById(R.id.mylayout);
        saveBtn.setOnClickListener(this);

        olay.setImageAlpha(0);
        lsleeve.setImageAlpha(0);
        rsleeve.setImageAlpha(0);
        leftcorner.setImageAlpha(0);
        rightcorner.setImageAlpha(0);
        collar.setImageAlpha(0);
        olay.setOnClickListener(this);
        lsleeve.setOnClickListener(this);
        rsleeve.setOnClickListener(this);
        leftcorner.setOnClickListener(this);
        rightcorner.setOnClickListener(this);

        collar.setOnClickListener(this);



    }

    public void onClick(View v) {
        i = new Intent(v.getContext(), camera.class);

        switch (v.getId()) {

            case R.id.torso:
                startActivityForResult(i, 1);
                break;
            case R.id.rightsleeve:
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
            case R.id.saveBtn:
                saveImage(mylayout);

        }
    }

    private void saveImage(ConstraintLayout v) {
        //creating bitmap from view
        Bitmap b = Bitmap.createBitmap(v.getWidth() , v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
       // v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);

        //saving to storage
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "designclothes");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("App", "failed to create directory");
            }
        }
        File tempfile = new File(Environment.getExternalStorageDirectory()+"/designClothes/tshirt"+imgnum+".jpg");
        while(tempfile.exists()){
            imgnum++;
            tempfile = new File(Environment.getExternalStorageDirectory()+"/designClothes/tshirt"+imgnum+".jpg");
        }
        final File file = new File(Environment.getExternalStorageDirectory()+"/designClothes/tshirt"+imgnum+".jpg");

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
                ImageOperations(num, strTorso, 1);
                break;
            case 2:
                ImageOperations(num, strleftsleeve, 2);
                break;
            case 3:
                ImageOperations(num, strcorner, 3);
                break;
            case 4:
                ImageOperations(num, strcollar, 4);
                break;
        }

    }

    private void ImageOperations(int num, int strpath, int img) {
        String photoPath = Environment.getExternalStorageDirectory() + "/designClothes/pic" + num + ".jpg";

        Bitmap rotated = BitmapFactory.decodeFile(photoPath);
        //rotated = Bitmap.createScaledBitmap(rotated , olay.getWidth(), olay.getHeight(), true);
        Bitmap bitmap = rotateBitmap(rotated);
        Bitmap b = convertToTorso(bitmap, strpath);
       // Bitmap c;
       // Matrix matrix;
        //Bitmap c = Bitmap.createScaledBitmap(b,b.getWidth()/2,b.getHeight()/2,true);Log.d("isize2", b.getWidth()+" "+b.getHeight());
        switch (img) {
            case 1:
                olay.setImageAlpha(255);
                olay.setImageBitmap(b);
                break;
            case 2:
                rsleeve.setImageAlpha(255);
                lsleeve.setImageAlpha(255);
                lsleeve.setImageBitmap(b);
                rsleeve.setImageBitmap(b);
                break;
            case 3:
                rightcorner.setImageAlpha(255);
                leftcorner.setImageAlpha(255);
                rightcorner.setImageBitmap(b);
                leftcorner.setImageBitmap(b);
                break;
            case 4:
                collar.setImageAlpha(255);
                collar.setImageBitmap(b);
                break;
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
       // rotatedPhoto = Bitmap.createScaledBitmap(rotatedPhoto, olay.getWidth(), olay.getHeight(), true);
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
