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
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView tshirt;
    ImageView olay;
    Intent i;
    int num = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tshirt = findViewById(R.id.tshirt_main);
        olay = findViewById(R.id.tshirt_olay);

        olay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "Toast for fun", Toast.LENGTH_SHORT).show();
                i = new Intent(v.getContext() , camera.class);
                startActivityForResult(i , 1);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 ){
            num = data.getIntExtra("image", 0 );
            Log.d("Testing", String.valueOf(num));
            ImageOperations(num);
        }
    }

    private void ImageOperations(int num) {
        String photoPath = Environment.getExternalStorageDirectory()+"/designClothes/pic"+num+".jpg";

        Bitmap rotated = BitmapFactory.decodeFile(photoPath);

        Bitmap bitmap = rotateBitmap(rotated);
        Bitmap b= convertToHeart(bitmap);
        Bitmap c = Bitmap.createScaledBitmap(b,b.getWidth()/2,b.getHeight()/2,true);
        olay.setImageBitmap(c);

    }

    private Bitmap rotateBitmap(Bitmap photo){
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedPhoto = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
        // Bitmap rotatedPhoto = Bitmap.createScaledBitmap(rotatedPhoto1,photo.getWidth()/2,photo.getHeight()/2,false);
        return rotatedPhoto ;
    }

    private Bitmap convertToHeart(Bitmap src) {
        return BitmapUtils.getCroppedBitmap(src, getHeartPath(src));
    }

    private Path getHeartPath(Bitmap src) {
        return resizePath(PathParser.createPathFromPathData(getString(R.string.tshirt1)),
                src.getWidth(), src.getHeight());
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
