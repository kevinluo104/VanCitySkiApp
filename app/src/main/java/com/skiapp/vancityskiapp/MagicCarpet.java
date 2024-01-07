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
                                    ImageView status = findViewById(R.id.imageView378);
                                    switch (seymourSingleton.goldieMagicCarpet) {
                                        case "open":
                                            status.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            status.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            status.setImageResource(R.drawable.triangle);
                                            break;
                                    }

                                    TextView runsOpen = findViewById(R.id.textView380);
                                    runsOpen.setText("Runs Open: " + seymourSingleton.goldieMagicCarpetRunsOpen + "/3");
                                    TextView terrainParksOpen = findViewById(R.id.textView421);
                                    System.out.println("MUSHROOM 1: " + seymourSingleton.mushroom);
                                    if (seymourSingleton.mushroom.equals("open")) {
                                        terrainParksOpen.setText("Terrain Parks Open: 1/1");
                                    } else {
                                        terrainParksOpen.setText("Terrain Parks Open: 0/1");
                                    }
                                    ImageView flowerBasin = findViewById(R.id.imageView379);
                                    setStatus(flowerBasin, seymourSingleton.flowerBasin);
                                    ImageView mushroomRun = findViewById(R.id.imageView386);
                                    setStatus(mushroomRun, seymourSingleton.mushroomRun);
                                    ImageView goldieMeadows = findViewById(R.id.imageView387);
                                    setStatus(goldieMeadows, seymourSingleton.goldieMagicCarpet);
                                    ImageView mushroom = findViewById(R.id.imageView440);
                                    setStatus(mushroom, seymourSingleton.mushroom);
                                    System.out.println("MUSHROOM 2: " + seymourSingleton.mushroom);
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
            case "?":
                image.setImageResource(R.drawable.question_mark);
                return;
        }
    }
}
