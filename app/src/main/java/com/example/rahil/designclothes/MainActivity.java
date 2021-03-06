package com.example.rahil.designclothes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button Tshirt, Shirt ,Dress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tshirt = findViewById(R.id.teeBtn);
        Shirt = findViewById(R.id.shirtBtn);
        Dress = findViewById(R.id.dressbtn);

        Tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(getApplicationContext() , TShirtActivity.class);
            startActivity(i);
            }
        });

        Shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(getApplicationContext(), ShirtActivity.class);
                startActivity(i);
            }
        });

        Dress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DressActivity.class);
                startActivity(i);
            }
        });
    }
}
