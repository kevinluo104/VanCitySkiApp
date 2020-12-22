package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LodgeChair extends AppCompatActivity {

    private SeymourSingleton seymourSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodge_chair);

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            seymourSingleton = SeymourSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView imageView352 = findViewById(R.id.imageView352);
                                    switch (seymourSingleton.lodgeChair) {
                                        case "open":
                                            imageView352.setImageResource(R.drawable.greencheck);
                                            break;
                                        case "closed":
                                            imageView352.setImageResource(R.drawable.redx);
                                            break;
                                        case "standby":
                                            imageView352.setImageResource(R.drawable.triangle);
                                            break;
                                    }
                                    TextView textView367 = findViewById(R.id.textView367);
                                    textView367.setText("Runs Open: " + seymourSingleton.lodgeChairRunsOpen + "/9");
                                    TextView textView420 = findViewById(R.id.textView420);
                                    textView420.setText("Terrain Parks Open: " + seymourSingleton.lodgeChairTerrainParksOpen + "/2");

                                    ImageView imageView368 = findViewById(R.id.imageView368);
                                    setStatus(imageView368, seymourSingleton.lodgeConnector);
                                    ImageView imageView369 = findViewById(R.id.imageView369);
                                    setStatus(imageView369, seymourSingleton.mushroomRun);
                                    ImageView imageView370 = findViewById(R.id.imageView370);
                                    setStatus(imageView370, seymourSingleton.rookiesRun);
                                    ImageView imageView371 = findViewById(R.id.imageView371);
                                    setStatus(imageView371, seymourSingleton.cabinTrail);
                                    ImageView imageView372 = findViewById(R.id.imageView372);
                                    setStatus(imageView372, seymourSingleton.chucksPlace);
                                    ImageView imageView373 = findViewById(R.id.imageView373);
                                    setStatus(imageView373, seymourSingleton.lowerUnicorn);
                                    ImageView imageView374 = findViewById(R.id.imageView374);
                                    setStatus(imageView374, seymourSingleton.mistletoe);
                                    ImageView imageView375 = findViewById(R.id.imageView375);
                                    setStatus(imageView375, seymourSingleton.seymour16s);
                                    ImageView imageView376 = findViewById(R.id.imageView376);
                                    setStatus(imageView376, seymourSingleton.trapperJohns);
                                    ImageView imageView443 = findViewById(R.id.imageView443);
                                    setStatus(imageView443, seymourSingleton.mushroom);
                                    ImageView imageView444 = findViewById(R.id.imageView444);
                                    setStatus(imageView444, seymourSingleton.rookies);
                                    if (seymourSingleton.mushroom.equals("open")) {
                                        imageView443.setImageResource(R.drawable.greencheck);
                                    } else {
                                        imageView443.setImageResource(R.drawable.redx);
                                    }
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
