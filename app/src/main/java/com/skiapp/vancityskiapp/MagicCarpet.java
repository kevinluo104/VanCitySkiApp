package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MagicCarpet extends AppCompatActivity {

    private SeymourSingleton seymourSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_carpet);

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            seymourSingleton = SeymourSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView378 = findViewById(R.id.imageView378);
                                    switch (seymourSingleton.goldieMagicCarpet) {
                                        case "open":
                                            imageView378.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView378.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView378.setImageResource(R.drawable.triangle);
                                            break;
                                    }

                                    TextView textView380 = findViewById(R.id.textView380);
                                    textView380.setText("Runs Open: " + seymourSingleton.goldieMagicCarpetRunsOpen + "/3");
                                    TextView textView421 = findViewById(R.id.textView421);
                                    if (seymourSingleton.mushroom.equals("Open")) {
                                        textView421.setText("Terrain Parks Open: 1/1");
                                    } else {
                                        textView421.setText("Terrain Parks Open: 0/1");
                                    }
                                    ImageView imageView379 = findViewById(R.id.imageView379);
                                    setStatus(imageView379, seymourSingleton.flowerBasin);
                                    ImageView imageView386 = findViewById(R.id.imageView386);
                                    setStatus(imageView386, seymourSingleton.mushroomRun);
                                    ImageView imageView387 = findViewById(R.id.imageView387);
                                    setStatus(imageView387, seymourSingleton.rookiesRun);
                                    ImageView imageView440 = findViewById(R.id.imageView440);
                                    setStatus(imageView440, seymourSingleton.mushroom);
                                }
                            }, getApplicationContext());
                            seymourSingleton.startThread();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }.start();
    }

    public void setStatus(ImageView image, String status) {
        switch (status) {
            case "Open":
            case "open":
                image.setImageResource(R.drawable.greencheck);
                return;
            case "Closed":
            case "closed":
                image.setImageResource(R.drawable.redx);
                return;
            case "Standby":
            case "standby":
                image.setImageResource(R.drawable.triangle);
                return;
        }
    }
}
