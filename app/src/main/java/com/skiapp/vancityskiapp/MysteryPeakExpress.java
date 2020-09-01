package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class MysteryPeakExpress extends AppCompatActivity {

    private SeymourSingleton seymourSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery_peak_express);

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            seymourSingleton = SeymourSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView389 = findViewById(R.id.imageView389);
                                    switch (seymourSingleton.mysteryPeakExpress) {
                                        case "open":
                                            imageView389.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView389.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView389.setImageResource(R.drawable.triangle);
                                            break;
                                    }

                                    TextView textView387 = findViewById(R.id.textView387);
                                    textView387.setText("Runs Open: " + seymourSingleton.mysteryPeakExpressRunsOpen + "/19");
                                    TextView textView422 = findViewById(R.id.textView422);
                                    textView422.setText("Terrain Parks Open: " + seymourSingleton.mysteryPeakExpressTerrainParksOpen + "/3");

                                    ImageView imageView390 = findViewById(R.id.imageView390);
                                    setStatus(imageView390, seymourSingleton.manning);
                                    ImageView imageView415 = findViewById(R.id.imageView415);
                                    setStatus(imageView415, seymourSingleton.boomerang);
                                    ImageView imageView416 = findViewById(R.id.imageView416);
                                    setStatus(imageView416, seymourSingleton.crowfoot);
                                    ImageView imageView417 = findViewById(R.id.imageView417);
                                    setStatus(imageView417, seymourSingleton.earls);
                                    ImageView imageView418 = findViewById(R.id.imageView418);
                                    setStatus(imageView418, seymourSingleton.elevatorShaft);
                                    ImageView imageView419 = findViewById(R.id.imageView419);
                                    setStatus(imageView419, seymourSingleton.friendlyNuthouse);
                                    ImageView imageView420 = findViewById(R.id.imageView420);
                                    setStatus(imageView420, seymourSingleton.gunBarrel);
                                    ImageView imageView421 = findViewById(R.id.imageView421);
                                    setStatus(imageView421, seymourSingleton.looperExpress);
                                    ImageView imageView422 = findViewById(R.id.imageView422);
                                    setStatus(imageView422, seymourSingleton.mysteryLake);
                                    ImageView imageView423 = findViewById(R.id.imageView423);
                                    setStatus(imageView423, seymourSingleton.northlandsRun);
                                    ImageView imageView424 = findViewById(R.id.imageView424);
                                    setStatus(imageView424, seymourSingleton.petes);
                                    ImageView imageView425 = findViewById(R.id.imageView425);
                                    setStatus(imageView425, seymourSingleton.slingshot);
                                    ImageView imageView426 = findViewById(R.id.imageView426);
                                    setStatus(imageView426, seymourSingleton.towerline);
                                    ImageView imageView427 = findViewById(R.id.imageView427);
                                    setStatus(imageView427, seymourSingleton.velvetGully);
                                    ImageView imageView428 = findViewById(R.id.imageView428);
                                    setStatus(imageView428, seymourSingleton.wonger);
                                    ImageView imageView429 = findViewById(R.id.imageView429);
                                    setStatus(imageView429, seymourSingleton.devilsDrop);
                                    ImageView imageView430 = findViewById(R.id.imageView430);
                                    setStatus(imageView430, seymourSingleton.noelsFlight);
                                    ImageView imageView431 = findViewById(R.id.imageView431);
                                    setStatus(imageView431, seymourSingleton.nutcracker);
                                    ImageView imageView432 = findViewById(R.id.imageView432);
                                    setStatus(imageView432, seymourSingleton.unicorn);

                                    ImageView imageView436 = findViewById(R.id.imageView436);
                                    setStatus(imageView436, seymourSingleton.northlands);
                                    ImageView imageView438 = findViewById(R.id.imageView438);
                                    setStatus(imageView438, seymourSingleton.nuthouse);
                                    ImageView imageView437 = findViewById(R.id.imageView437);
                                    setStatus(imageView437, seymourSingleton.theRockstarEnergyPit);
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
