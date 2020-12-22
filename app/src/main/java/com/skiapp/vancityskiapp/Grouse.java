package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.util.Calendar;

public class Grouse extends AppCompatActivity {

    private Calendar cal = Calendar.getInstance();
    private int hour = cal.get(Calendar.HOUR_OF_DAY);
    private GrouseSingleton grouseSingleton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grouse);

        Button button22 = findViewById(R.id.button22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @SuppressLint("ResourceType")
                    public void run() {
                        try {
                            grouseSingleton = GrouseSingleton.getInstance(new ResultListener() {

                                @Override
                                public void onResultFetched() {
                                    TextView tv45 = findViewById(R.id.textView45);
                                    tv45.setText(grouseSingleton.temperature);
                                    TextView tv46 = findViewById(R.id.textView46);
                                    tv46.setText(grouseSingleton.weather);
                                    TextView tv47 = findViewById(R.id.textView47);
                                    switch (grouseSingleton.visibility) {
                                        case "Limited Visibility":
                                            tv47.setText("Visibility: Limited");
                                            break;
                                        case "Variable Visibility":
                                            tv47.setText("Visibility: Variable");
                                            break;
                                        case "Unlimited Visibility":
                                            tv47.setText("Visibility: Unlimited");
                                            break;
                                    }
                                    TextView tv52 = findViewById(R.id.textView52);
                                    tv52.setText(grouseSingleton.twelveHrSnow);
                                    TextView tv54 = findViewById(R.id.textView54);
                                    tv54.setText(grouseSingleton.overnightSnow);
                                    TextView tv56 = findViewById(R.id.textView56);
                                    tv56.setText(grouseSingleton.twentyFourHrSnow);
                                    TextView tv58 = findViewById(R.id.textView58);
                                    tv58.setText(grouseSingleton.fortyEightHrSnow);
                                    ImageView iv16 = findViewById(R.id.imageView16);

                                    setStatus(iv16, grouseSingleton.greenwayChair);
                                    ImageView iv17 = findViewById(R.id.imageView17);
                                    setStatus(iv17, grouseSingleton.olympicExpressChair);
                                    ImageView iv18 = findViewById(R.id.imageView18);
                                    setStatus(iv18, grouseSingleton.screamingEagleChair);
                                    ImageView iv19 = findViewById(R.id.imageView19);
                                    setStatus(iv19, grouseSingleton.peakChair);
                                    ImageView iv20 = findViewById(R.id.imageView20);
                                    setStatus(iv20, grouseSingleton.magicCarpet);

                                    ImageView iv21 = findViewById(R.id.imageView21);
                                    setStatus(iv21, grouseSingleton.chaletRoad);


                                    ImageView iv22 = findViewById(R.id.imageView22);
                                    setStatus(iv22, grouseSingleton.skiWee);
                                    ImageView iv23 = findViewById(R.id.imageView23);
                                    setStatus(iv23, grouseSingleton.blueFace);
                                    ImageView iv24 = findViewById(R.id.imageView24);
                                    setStatus(iv24, grouseSingleton.coolasCorner);
                                    ImageView iv25 = findViewById(R.id.imageView25);
                                    setStatus(iv25, grouseSingleton.dogleg);
                                    ImageView iv26 = findViewById(R.id.imageView26);
                                    setStatus(iv26, grouseSingleton.grinderTracks);
                                    ImageView iv27 = findViewById(R.id.imageView27); // LOWER BUCKHORN
                                    setStatus(iv27, grouseSingleton.lowerBuckhorn);
                                    ImageView iv28 = findViewById(R.id.imageView28);
                                    setStatus(iv28, grouseSingleton.mountainHighway);
                                    ImageView iv29 = findViewById(R.id.imageView29); // SIDE CUT
                                    setStatus(iv29, grouseSingleton.sideCut);
                                    ImageView iv30 = findViewById(R.id.imageView30);
                                    setStatus(iv30, grouseSingleton.tyeeChute);
                                    ImageView iv31 = findViewById(R.id.imageView31);
                                    setStatus(iv31, grouseSingleton.blazes);
                                    ImageView iv32 = findViewById(R.id.imageView32);  // COFFIN
                                    setStatus(iv32, grouseSingleton.coffin);
                                    ImageView iv33 = findViewById(R.id.imageView33);
                                    setStatus(iv33, grouseSingleton.hades);
                                    ImageView iv34 = findViewById(R.id.imageView34);
                                    setStatus(iv34, grouseSingleton.lowerPeak);
                                    ImageView iv35 = findViewById(R.id.imageView35);
                                    setStatus(iv35, grouseSingleton.peak);
                                    ImageView iv36 = findViewById(R.id.imageView36);
                                    setStatus(iv36, grouseSingleton.devilsAdvocate);
                                    ImageView iv37 = findViewById(R.id.imageView37);  // DEVIL'S ADVOCATE
                                    setStatus(iv37, grouseSingleton.purgatory);
                                    ImageView iv38 = findViewById(R.id.imageView38);  // PARADISE
                                    setStatus(iv38, grouseSingleton.paradise);
                                    ImageView iv39 = findViewById(R.id.imageView39);
                                    setStatus(iv39, grouseSingleton.theCut);
                                    ImageView iv40 = findViewById(R.id.imageView40);
                                    setStatus(iv40, grouseSingleton.centennial);
                                    ImageView iv41 = findViewById(R.id.imageView41);
                                    setStatus(iv41, grouseSingleton.deliverance);
                                    ImageView iv42 = findViewById(R.id.imageView42);
                                    setStatus(iv42, grouseSingleton.expo);
                                    ImageView iv43 = findViewById(R.id.imageView43);  // HEAVEN'S SAKE
                                    setStatus(iv43, grouseSingleton.heavensSake);
                                    ImageView iv44 = findViewById(R.id.imageView44);
                                    setStatus(iv44, grouseSingleton.lowerSideCut);
                                    ImageView iv45 = findViewById(R.id.imageView45);
                                    setStatus(iv45, grouseSingleton.paperTrail);
                                    ImageView iv46 = findViewById(R.id.imageView46);  // SKYLINE
                                    setStatus(iv46, grouseSingleton.skyline);
                                    ImageView iv47 = findViewById(R.id.imageView47);
                                    setStatus(iv47, grouseSingleton.upperBuckhorn);
                                    ImageView iv48 = findViewById(R.id.imageView48); // CHIMNEY
                                    setStatus(iv48, grouseSingleton.chimney);
                                    ImageView iv49 = findViewById(R.id.imageView49);
                                    setStatus(iv49, grouseSingleton.expoGlades);
                                    ImageView iv50 = findViewById(R.id.imageView50);
                                    setStatus(iv50, grouseSingleton.inferno);
                                    ImageView iv51 = findViewById(R.id.imageView51);
                                    setStatus(iv51, grouseSingleton.outerLimits);
                                    ImageView iv52 = findViewById(R.id.imageView52);
                                    setStatus(iv52, grouseSingleton.upperBlazes);
                                    ImageView iv53 = findViewById(R.id.imageView53);
                                    setStatus(iv53, grouseSingleton.peakGlades);

                                    ImageView iv103 = findViewById(R.id.imageView103);
                                    setStatus(iv103, grouseSingleton.cutJumpLine);
                                    ImageView iv104 = findViewById(R.id.imageView104);
                                    setStatus(iv104, grouseSingleton.paradiseJibPark);
                                    System.out.println(grouseSingleton.terrainParksOpen);
                                    ImageView iv105 = findViewById(R.id.imageView105);
                                    setStatus(iv105, grouseSingleton.cutRookiePark);
                                    ImageView iv106 = findViewById(R.id.imageView106);
                                    setStatus(iv106, grouseSingleton.cutPark);
                                    ImageView iv107 = findViewById(R.id.imageView107);
                                    setStatus(iv107, grouseSingleton.lightWalk);
                                    ImageView iv108 = findViewById(R.id.imageView108);
                                    setStatus(iv108, grouseSingleton.grousePark);

                                    ImageView iv122 = findViewById(R.id.imageView122);
                                    setStatus(iv122, grouseSingleton.blueGrouseLoop);
                                    ImageView iv123 = findViewById(R.id.imageView123);
                                    setStatus(iv123, grouseSingleton.damMountainLoop);
                                    ImageView iv124 = findViewById(R.id.imageView124);
                                    setStatus(iv124, grouseSingleton.snowshoeGrind);
                                    ImageView iv125 = findViewById(R.id.imageView125);
                                    setStatus(iv125, grouseSingleton.thunderbirdRidge);

                                    TextView tv48 = findViewById(R.id.textView48);
                                    tv48.setText("Runs Open: " + grouseSingleton.runsOpen + "/34");
                                    TextView tv50 = findViewById(R.id.textView50);
                                    tv50.setText("Lifts Open: " + grouseSingleton.liftsOpen + "/5");
                                    TextView textView114 = findViewById(R.id.textView114);
                                    textView114.setText("Terrain Parks Open: " + grouseSingleton.terrainParksOpen + "/6");
                                    TextView textView227 = findViewById(R.id.textView227);
                                    textView227.setText("Snowshoe Trails Open: " + grouseSingleton.snowshoeTrailsOpen + "/5");

                                    ImageView imageView14 = findViewById(R.id.imageView14);
                                    setGrousePic(grouseSingleton.picture, imageView14);
                                }
                            }, getApplicationContext());
                            grouseSingleton.startThread();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(grouseSingleton.greenwayChair);
                        Button button4 = findViewById(R.id.button4);
                        button4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.snow-forecast.com/resorts/Grouse-Mountain/6day/mid"));
                                startActivity(browserIntent);
                            }
                        });
                    }
                });
            }
        }.start();
    }

    public void setGrousePic(String text, ImageView image) {
        String[] arr = text.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        text = sb.toString().trim();

        if (text.equals("High Overcast Skies")) {
            image.setImageResource(R.drawable.cloudy);
            return;
        }
        if (text.equals("Low Overcast Skies")) {
            image.setImageResource(R.drawable.cloudy);
            return;
        }
        if (text.equals("Clear Skies")) {
            image.setImageResource(R.drawable.clear_night);
            return;
        }
        if (text.equals("Sunny Skies") || text.equals("Blue Bird Skies!")) {
            image.setImageResource(R.drawable.sunny);
            return;
        }
        if (text.equals("Raining Skies")) {
            image.setImageResource(R.drawable.chance_of_showers);
            return;
        }
        if (text.equals("Mix of Snow & Rain")) {
            image.setImageResource(R.drawable.wet_snow_mixed_with_rain);
            return;
        }
        if (text.equals("Snowing Heavily")) {
            image.setImageResource(R.drawable.snow);
            return;
        }
        if (text.equals("Lightly Snowing Skies")) {
            image.setImageResource(R.drawable.light_snow);
            return;
        }

        if (hour >= 6 && hour < 21) {
            switch (text) {
                case "Mainly Cloudy Skies":
                    image.setImageResource(R.drawable.mainly_cloudy_day);
                    return;
                case "Mix Of Sun & Cloud":
                    image.setImageResource(R.drawable.mainly_sunny);
                    return;
                case "Sunny Skies":
                    image.setImageResource(R.drawable.sunny);
                    return;
            }
        } else {
            switch (text) {
                case "Mainly Cloudy Skies":
                    image.setImageResource(R.drawable.mainly_cloudy_night);
                    return;
                case "Clear Skies":
                    image.setImageResource(R.drawable.clear_night);
                    return;
            }
        }
    }

    public void setStatus(ImageView image, String status) {
        if (status.equals("open")) {
            image.setImageResource(R.drawable.greencheck);
        } else {
            image.setImageResource(R.drawable.redx);
        }
    }

    public void refresh() {
        Intent intent = new Intent(this, Grouse.class);
        startActivity(intent);
    }
}


