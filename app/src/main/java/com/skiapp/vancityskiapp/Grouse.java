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

        Button refresh = findViewById(R.id.button22);
        refresh.setOnClickListener(new View.OnClickListener() {
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
                                    TextView temperature = findViewById(R.id.textView45);
                                    temperature.setText(grouseSingleton.temperature);
                                    TextView conditions = findViewById(R.id.textView46);
                                    conditions.setText(grouseSingleton.weather);
                                    TextView visibility = findViewById(R.id.textView47);
                                    switch (grouseSingleton.visibility) {
                                        case "Limited Visibility":
                                            visibility.setText("Visibility: Limited");
                                            break;
                                        case "Variable Visibility":
                                            visibility.setText("Visibility: Variable");
                                            break;
                                        case "Unlimited Visibility":
                                            visibility.setText("Visibility: Unlimited");
                                            break;
                                    }
                                    TextView twelveHrSnow = findViewById(R.id.textView52);
                                    twelveHrSnow.setText(grouseSingleton.twelveHrSnow);
                                    TextView overnightSnow = findViewById(R.id.textView54);
                                    overnightSnow.setText(grouseSingleton.overnightSnow);
                                    TextView twentyFourHrSnow = findViewById(R.id.textView56);
                                    twentyFourHrSnow.setText(grouseSingleton.twentyFourHrSnow);
                                    TextView fortyEightHrSnow = findViewById(R.id.textView58);
                                    fortyEightHrSnow.setText(grouseSingleton.fortyEightHrSnow);
                                    ImageView greenwayChair = findViewById(R.id.imageView16);

                                    setStatus(greenwayChair, grouseSingleton.greenwayChair);
                                    ImageView olympicExpressChair = findViewById(R.id.imageView17);
                                    setStatus(olympicExpressChair, grouseSingleton.olympicExpressChair);
                                    ImageView screamingEagleChair = findViewById(R.id.imageView18);
                                    setStatus(screamingEagleChair, grouseSingleton.screamingEagleChair);
                                    ImageView peakChair = findViewById(R.id.imageView19);
                                    setStatus(peakChair, grouseSingleton.peakChair);
                                    ImageView magicCarpet = findViewById(R.id.imageView20);
                                    setStatus(magicCarpet, grouseSingleton.magicCarpet);
                                    ImageView handleTow = findViewById(R.id.imageView215);
                                    setStatus(handleTow, grouseSingleton.handleTow);

                                    ImageView chaletRoad = findViewById(R.id.imageView21);
                                    setStatus(chaletRoad, grouseSingleton.chaletRoad);


                                    ImageView skiWee = findViewById(R.id.imageView22);
                                    setStatus(skiWee, grouseSingleton.skiWee);
                                    ImageView blueFace = findViewById(R.id.imageView23);
                                    setStatus(blueFace, grouseSingleton.blueFace);
                                    ImageView coolasCorner = findViewById(R.id.imageView24);
                                    setStatus(coolasCorner, grouseSingleton.coolasCorner);
                                    ImageView dogleg = findViewById(R.id.imageView25);
                                    setStatus(dogleg, grouseSingleton.dogleg);
                                    ImageView grinderTracks = findViewById(R.id.imageView26);
                                    setStatus(grinderTracks, grouseSingleton.grinderTracks);
                                    ImageView lowerBuckhorn = findViewById(R.id.imageView27);
                                    setStatus(lowerBuckhorn, grouseSingleton.lowerBuckhorn);
                                    ImageView mountainHighway = findViewById(R.id.imageView28);
                                    setStatus(mountainHighway, grouseSingleton.mountainHighway);
                                    ImageView sideCut = findViewById(R.id.imageView29);
                                    setStatus(sideCut, grouseSingleton.sideCut);
                                    ImageView tyeeChute = findViewById(R.id.imageView30);
                                    setStatus(tyeeChute, grouseSingleton.tyeeChute);
                                    ImageView blazes = findViewById(R.id.imageView31);
                                    setStatus(blazes, grouseSingleton.blazes);
                                    ImageView coffin = findViewById(R.id.imageView32);
                                    setStatus(coffin, grouseSingleton.coffin);
                                    ImageView hades = findViewById(R.id.imageView33);
                                    setStatus(hades, grouseSingleton.hades);
                                    ImageView lowerPeak = findViewById(R.id.imageView34);
                                    setStatus(lowerPeak, grouseSingleton.lowerPeak);
                                    ImageView peak = findViewById(R.id.imageView35);
                                    setStatus(peak, grouseSingleton.peak);
                                    ImageView devilsAdvocate = findViewById(R.id.imageView36);
                                    setStatus(devilsAdvocate, grouseSingleton.devilsAdvocate);
                                    ImageView purgatory = findViewById(R.id.imageView37);
                                    setStatus(purgatory, grouseSingleton.purgatory);
                                    ImageView paradise = findViewById(R.id.imageView38);
                                    setStatus(paradise, grouseSingleton.paradise);
                                    ImageView theCut = findViewById(R.id.imageView39);
                                    setStatus(theCut, grouseSingleton.theCut);
                                    ImageView centennial = findViewById(R.id.imageView40);
                                    setStatus(centennial, grouseSingleton.centennial);
                                    ImageView deliverance = findViewById(R.id.imageView41);
                                    setStatus(deliverance, grouseSingleton.deliverance);
                                    ImageView expo = findViewById(R.id.imageView42);
                                    setStatus(expo, grouseSingleton.expo);
                                    ImageView heavensSake = findViewById(R.id.imageView43);
                                    setStatus(heavensSake, grouseSingleton.heavensSake);
                                    ImageView lowerSideCut = findViewById(R.id.imageView44);
                                    setStatus(lowerSideCut, grouseSingleton.lowerSideCut);
                                    ImageView paperTrail = findViewById(R.id.imageView45);
                                    setStatus(paperTrail, grouseSingleton.paperTrail);
                                    ImageView skyline = findViewById(R.id.imageView46);
                                    setStatus(skyline, grouseSingleton.skyline);
                                    ImageView upperBuckhorn = findViewById(R.id.imageView47);
                                    setStatus(upperBuckhorn, grouseSingleton.upperBuckhorn);
                                    ImageView chimney = findViewById(R.id.imageView48);
                                    setStatus(chimney, grouseSingleton.chimney);
                                    ImageView expoGlades = findViewById(R.id.imageView49);
                                    setStatus(expoGlades, grouseSingleton.expoGlades);
                                    ImageView inferno = findViewById(R.id.imageView50);
                                    setStatus(inferno, grouseSingleton.inferno);
                                    ImageView outerLimits = findViewById(R.id.imageView51);
                                    setStatus(outerLimits, grouseSingleton.outerLimits);
                                    ImageView upperBlazes = findViewById(R.id.imageView52);
                                    setStatus(upperBlazes, grouseSingleton.upperBlazes);
                                    ImageView peakGlades = findViewById(R.id.imageView53);
                                    setStatus(peakGlades, grouseSingleton.peakGlades);
                                    ImageView noMansLand = findViewById(R.id.imageView314);
                                    setStatus(noMansLand, grouseSingleton.noMansLand);

                                    ImageView cutJumpLine = findViewById(R.id.imageView103);
                               //     setStatus(cutJumpLine, grouseSingleton.cutJumpLine);
                                    cutJumpLine.setImageResource(R.drawable.redx);
                                    ImageView paradiseJibPark = findViewById(R.id.imageView104);
                                    setStatus(paradiseJibPark, grouseSingleton.paradiseJibPark);
                                    ImageView cutRookiePark = findViewById(R.id.imageView105);
                                    setStatus(cutRookiePark, grouseSingleton.cutRookiePark);
                                    ImageView cutPark = findViewById(R.id.imageView106);
                                    setStatus(cutPark, grouseSingleton.sideCutPark);
                                    ImageView lightWalk = findViewById(R.id.imageView107);
                                    setStatus(lightWalk, grouseSingleton.lightWalk);
//                                    ImageView iv108 = findViewById(R.id.imageView108);
//                                    setStatus(iv108, grouseSingleton.grousePark);

                                    ImageView blueGrouseLoop = findViewById(R.id.imageView122);
                                    setStatus(blueGrouseLoop, grouseSingleton.blueGrouseLoop);
                                    ImageView damMountainLoop = findViewById(R.id.imageView123);
                                    setStatus(damMountainLoop, grouseSingleton.damMountainLoop);
                                    ImageView snowshoeGrind = findViewById(R.id.imageView124);
                                    setStatus(snowshoeGrind, grouseSingleton.snowshoeGrind);
                                    ImageView thunderbirdRidge = findViewById(R.id.imageView125);
                                    setStatus(thunderbirdRidge, grouseSingleton.thunderbirdRidge);

                                    TextView runsOpen = findViewById(R.id.textView48);
                                    runsOpen.setText("Runs Open: " + grouseSingleton.runsOpen + "/34");
                                    TextView liftsOpen = findViewById(R.id.textView50);
                                    liftsOpen.setText("Lifts Open: " + grouseSingleton.liftsOpen + "/6");
                                    TextView terrainParksOpen = findViewById(R.id.textView114);
                                    terrainParksOpen.setText("Terrain Parks Open: " + grouseSingleton.terrainParksOpen + "/4");
                                    TextView snowshoeTrailsOpen = findViewById(R.id.textView227);
                                    snowshoeTrailsOpen.setText("Snowshoe Trails Open: " + grouseSingleton.snowshoeTrailsOpen + "/5");

                                    ImageView grousePic = findViewById(R.id.imageView14);
                                    setGrousePic(grouseSingleton.picture, grousePic);
                                }
                            }, getApplicationContext());
                            grouseSingleton.startThread();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(grouseSingleton.greenwayChair);
                        Button forecast = findViewById(R.id.button4);
                        forecast.setOnClickListener(new View.OnClickListener() {
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

    public void setGrousePic(String condition, ImageView image) {
        String[] arr = condition.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        condition = sb.toString().trim();

        if (condition.equals("High Overcast Skies")) {
            image.setImageResource(R.drawable.cloudy);
            return;
        }
        if (condition.equals("Low Overcast Skies")) {
            image.setImageResource(R.drawable.cloudy);
            return;
        }
        if (condition.equals("Clear Skies")) {
            image.setImageResource(R.drawable.clear_night);
            return;
        }
        if (condition.equals("Sunny Skies") || condition.equals("Blue Bird Skies!")) {
            image.setImageResource(R.drawable.sunny);
            return;
        }
        if (condition.equals("Raining Skies")) {
            image.setImageResource(R.drawable.chance_of_showers);
            return;
        }
        if (condition.equals("Mix of Snow & Rain")) {
            image.setImageResource(R.drawable.wet_snow_mixed_with_rain);
            return;
        }
        if (condition.equals("Snowing Heavily")) {
            image.setImageResource(R.drawable.snow);
            return;
        }
        if (condition.equals("Lightly Snowing Skies")) {
            image.setImageResource(R.drawable.light_snow);
            return;
        }
        if (condition.equals("Snowy Skies")) {
            image.setImageResource(R.drawable.snow);
            return;
        }
        if (condition.equals("Flurries")) {
            image.setImageResource(R.drawable.light_snow);
            return;
        }
        if (condition.equals("Mix Of Sun & Cloud")) {
            image.setImageResource(R.drawable.mainly_sunny);
            return;
        }
        if (condition.equals("Lightly Raining Skies")) {
            image.setImageResource(R.drawable.chance_of_showers);
            return;
        }

        if (hour >= 6 && hour < 21) {
            switch (condition) {
                case "Mainly Cloudy Skies":
                    image.setImageResource(R.drawable.mainly_cloudy_day);
                    return;
                case "Sunny Skies":
                    image.setImageResource(R.drawable.sunny);
                    return;
            }
        } else {
            switch (condition) {
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
        } else if (status.equals("standby")) {
            image.setImageResource(R.drawable.triangle);
        } else {
            image.setImageResource(R.drawable.redx);
        }
    }

    public void refresh() {
        Intent intent = new Intent(this, Grouse.class);
        startActivity(intent);
    }
}


