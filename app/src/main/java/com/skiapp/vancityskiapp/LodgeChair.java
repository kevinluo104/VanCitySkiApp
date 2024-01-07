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
                                    ImageView status = findViewById(R.id.imageView352);
                                    switch (seymourSingleton.lodgeChair) {
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
                                    TextView runsOpen = findViewById(R.id.textView367);
                                    runsOpen.setText("Runs Open: " + seymourSingleton.lodgeChairRunsOpen + "/9");
                                    TextView textView420 = findViewById(R.id.textView420);
                                    textView420.setText("Terrain Parks Open: " + seymourSingleton.lodgeChairTerrainParksOpen + "/2");

                                    ImageView lodgeConnector = findViewById(R.id.imageView368);
                                    setStatus(lodgeConnector, seymourSingleton.lodgeConnector);
                                    System.out.println("LODGECONE" + seymourSingleton.lodgeConnector);
//                                    ImageView mushroomRun = findViewById(R.id.imageView369);
//                                    setStatus(mushroomRun, seymourSingleton.mushroomRun);
                                    ImageView rookiesRun = findViewById(R.id.imageView370);
                                    setStatus(rookiesRun, seymourSingleton.rookiesRun);
                                    ImageView cabinTrail = findViewById(R.id.imageView371);
                                    setStatus(cabinTrail, seymourSingleton.cabinTrail);
                                    ImageView chucksPlace = findViewById(R.id.imageView372);
                                    setStatus(chucksPlace, seymourSingleton.chucksPlace);
                                    ImageView lowerUnicorn = findViewById(R.id.imageView373);
                                    setStatus(lowerUnicorn, seymourSingleton.lowerUnicorn);
                                    ImageView mistletoe = findViewById(R.id.imageView374);
                                    setStatus(mistletoe, seymourSingleton.mistletoe);
                                    ImageView seymour16s = findViewById(R.id.imageView375);
                                    setStatus(seymour16s, seymourSingleton.seymour16s);
                                    ImageView lowerTrapperJohn = findViewById(R.id.imageView376);
                                    setStatus(lowerTrapperJohn, seymourSingleton.lowerTrapperJohn);
                                    ImageView upperTrapperJohn = findViewById(R.id.imageView120);
                                    setStatus(upperTrapperJohn, seymourSingleton.upperTrapperJohn);
                                    System.out.println("MUSHROOM: " + seymourSingleton.mushroom);
                                    ImageView mushroom = findViewById(R.id.imageView443);
                                    setStatus(mushroom, seymourSingleton.mushroom);
                                    ImageView rookies = findViewById(R.id.imageView444);
                                    setStatus(rookies, seymourSingleton.rookies);
                                    if (seymourSingleton.mushroom.equals("open")) {
                                        mushroom.setImageResource(R.drawable.greencheck);
                                    } else {
                                        mushroom.setImageResource(R.drawable.redx);
                                    }

                                    System.out.println("LODG222ECONE" + seymourSingleton.lodgeConnector);
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
