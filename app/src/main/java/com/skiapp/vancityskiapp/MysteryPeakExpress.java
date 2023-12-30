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
                                    ImageView status = findViewById(R.id.imageView389);
                                    switch (seymourSingleton.mysteryPeakExpress) {
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

                                    TextView runsOpen = findViewById(R.id.textView387);
                                    runsOpen.setText("Runs Open: " + seymourSingleton.mysteryPeakExpressRunsOpen + "/19");
                                    TextView terrainParksOpen = findViewById(R.id.textView422);
                                    terrainParksOpen.setText("Terrain Parks Open: " + seymourSingleton.mysteryPeakExpressTerrainParksOpen + "/3");

                                    ImageView manning = findViewById(R.id.imageView390);
                                    setStatus(manning, seymourSingleton.manning);
                                    ImageView boomerang = findViewById(R.id.imageView415);
                                    setStatus(boomerang, seymourSingleton.boomerang);
                                    ImageView crowfoot = findViewById(R.id.imageView416);
                                    setStatus(crowfoot, seymourSingleton.crowfoot);
                                    ImageView earls = findViewById(R.id.imageView417);
                                    setStatus(earls, seymourSingleton.earls);
                                    ImageView elevatorShaft = findViewById(R.id.imageView418);
                                    setStatus(elevatorShaft, seymourSingleton.elevatorShaft);
                                    ImageView friendlyNutHouse = findViewById(R.id.imageView419);
                                    setStatus(friendlyNutHouse, seymourSingleton.friendlyNuthouse);
                                    ImageView gunBarrel = findViewById(R.id.imageView420);
                                    setStatus(gunBarrel, seymourSingleton.gunBarrel);
                                    ImageView loomerExpress = findViewById(R.id.imageView421);
                                    setStatus(loomerExpress, seymourSingleton.looperExpress);
                                    ImageView mysteryLake = findViewById(R.id.imageView422);
                                    setStatus(mysteryLake, seymourSingleton.mysteryLake);
                                    ImageView northlandsRun = findViewById(R.id.imageView423);
                                    setStatus(northlandsRun, seymourSingleton.northlandsRun);
                                    ImageView petes = findViewById(R.id.imageView424);
                                    setStatus(petes, seymourSingleton.petes);
                                    ImageView slingshot = findViewById(R.id.imageView425);
                                    setStatus(slingshot, seymourSingleton.slingshot);
                                    ImageView towerline = findViewById(R.id.imageView426);
                                    setStatus(towerline, seymourSingleton.towerline);
                                    ImageView velvetGully = findViewById(R.id.imageView427);
                                    setStatus(velvetGully, seymourSingleton.velvetGully);
                                    ImageView wonger = findViewById(R.id.imageView428);
                                    setStatus(wonger, seymourSingleton.wonger);
                                    ImageView devilsDrop = findViewById(R.id.imageView429);
                                    setStatus(devilsDrop, seymourSingleton.devilsDrop);
                                    ImageView noelsFlight = findViewById(R.id.imageView430);
                                    setStatus(noelsFlight, seymourSingleton.noelsFlight);
                                    ImageView nutcracker = findViewById(R.id.imageView431);
                                    setStatus(nutcracker, seymourSingleton.nutcracker);
                                    ImageView unicorn = findViewById(R.id.imageView432);
                                    setStatus(unicorn, seymourSingleton.unicorn);

                                    ImageView northlands = findViewById(R.id.imageView436);
                                    setStatus(northlands, seymourSingleton.northlands);
                                    ImageView nuthouse = findViewById(R.id.imageView437);
                                    setStatus(nuthouse, seymourSingleton.nuthouse);
                                    ImageView rockstarEnergyPit = findViewById(R.id.imageView438);
                                    setStatus(rockstarEnergyPit, seymourSingleton.theRockstarEnergyPit);
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
