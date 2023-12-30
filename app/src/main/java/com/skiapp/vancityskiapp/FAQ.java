package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FAQ extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_f_a_q);

        Button openFAQ2 = findViewById(R.id.button20);
        openFAQ2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFAQ2();
            }
        });
    }

    public void openFAQ2() {
        Intent intent = new Intent(this, FAQ2.class);
        startActivity(intent);
    }
}