package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Objects;

public class Seymour extends AppCompatActivity {

    private Calendar cal = Calendar.getInstance();
    private int hour = cal.get(Calendar.HOUR_OF_DAY);
    private SeymourSingleton seymourSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seymour);


        Button openBrocktonChair = findViewById(R.id.button11);
        openBrocktonChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrocktonChair();
            }
        });
        Button openLodgeChair = findViewById(R.id.button12);
        openLodgeChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLodgeChair();
            }
        });
        Button openMysteryPeakExpress = findViewById(R.id.button13);
        openMysteryPeakExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMysteryPeakExpress();
            }
        });
        Button openMagicCarpet = findViewById(R.id.button14);
        openMagicCarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMagicCarpet();
            }
        });
        Button forecast = findViewById(R.id.button16);
        forecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.snow-forecast.com/resorts/Mount-Seymour/6day/mid"));
                startActivity(browserIntent);
            }
        });
        Button refresh = findViewById(R.id.button23);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });

        new Thread() {
            public void run() {
                runOnUiThread( new Runnable()
                {
                    public void run()
                    {
                        try {
                            seymourSingleton = SeymourSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    TextView conditions = findViewById(R.id.textView323);
                                    conditions.setText(seymourSingleton.conditions);
                                    TextView temperature = findViewById(R.id.textView322);
                                    temperature.setText(seymourSingleton.temperature);
                                    TextView visibility = findViewById(R.id.textView324);
                                    visibility.setText("Visibility: " + seymourSingleton.visibility);
                                    TextView snowConditions = findViewById(R.id.textView325);
                                    snowConditions.setText(seymourSingleton.snowConditions);
                                    TextView runsOpen = findViewById(R.id.textView326);
                                    runsOpen.setText("Runs Open: " + seymourSingleton.runsOpen + "/42");
                                    TextView liftsOpen = findViewById(R.id.textView228);
                                    liftsOpen.setText("Lifts Open: " + seymourSingleton.liftsOpen + "/4");
                                    TextView terrainParksOpen = findViewById(R.id.textView229);
                                    terrainParksOpen.setText("Terrain Parks Open: " + seymourSingleton.terrainParksOpen + "/4");
                                    TextView tubeParksOpen = findViewById(R.id.textView230);
                                    tubeParksOpen.setText("Tube Parks Open: " + seymourSingleton.tubeParksOpen + "/2");
                                    TextView snowshoeTrailStatus = findViewById(R.id.textView231);
                                   // String snowshoeTrialStatus = seymourSingleton.snowshoeTrailsStatus.substring(0, 1).toUpperCase() + seymourSingleton.snowshoeTrailsStatus.substring(1);
                                    if (Objects.equals(seymourSingleton.discoverySnowshoeTrails, "open")) {
                                        snowshoeTrailStatus.setText("Snowshoe Trails Status: Open");
                                    } else {
                                        snowshoeTrailStatus.setText("Snowshoe Trails Status: Closed");
                                    }

                                    setWeatherIcon(seymourSingleton.conditions);
                                    TextView fortyEightHrSnow = findViewById(R.id.textView328);
                                    fortyEightHrSnow.setText(seymourSingleton.fortyEightHrSnow);
                                    TextView twentyFourHrSnow = findViewById(R.id.textView330);
                                    twentyFourHrSnow.setText(seymourSingleton.twentyFourHrSnow);
                                    TextView sevenDaySnow = findViewById(R.id.textView332);
                                    sevenDaySnow.setText(seymourSingleton.sevenDaySnow);
                                    TextView seasonSnow = findViewById(R.id.textView334);
                                    seasonSnow.setText(seymourSingleton.seasonSnow);

                                    ImageView brocktonChair = findViewById(R.id.imageView320);
                                    setStatus(brocktonChair, seymourSingleton.brocktonChair);
                                    ImageView lodgeChair = findViewById(R.id.imageView321);
                                    setStatus(lodgeChair, seymourSingleton.lodgeChair);
                                    ImageView mysteryPeakExpress = findViewById(R.id.imageView322);
                                    setStatus(mysteryPeakExpress, seymourSingleton.mysteryPeakExpress);
                                    ImageView goldieMagicCarpet = findViewById(R.id.imageView323);
                                    setStatus(goldieMagicCarpet, seymourSingleton.goldieMagicCarpet);

                                    ImageView enquistSnowTubePark = findViewById(R.id.imageView324);
                                    setStatus(enquistSnowTubePark, seymourSingleton.enquistSnowTubePark);
                                    ImageView tobogganArea = findViewById(R.id.imageView325);
                                    setStatus(tobogganArea, seymourSingleton.tobogganArea);
                                    ImageView discoverySnowshoeTrails = findViewById(R.id.imageView326);
                                    setStatus(discoverySnowshoeTrails, seymourSingleton.discoverySnowshoeTrails);
                                    TextView brocktonChairRunsOpen = findViewById(R.id.textView341);
                                    brocktonChairRunsOpen.setText("Runs Open: " + seymourSingleton.brocktonChairRunsOpen + "/10");
                                    TextView lodgeChairRunsOpen = findViewById(R.id.textView342);
                                    lodgeChairRunsOpen.setText("Runs Open: " + seymourSingleton.lodgeChairRunsOpen + "/9");
                                    TextView lodgeChairTerrainParksText = findViewById(R.id.textView423);
                                    lodgeChairTerrainParksText.setText("Terrain Parks Open: " + seymourSingleton.lodgeChairTerrainParksOpen + "/2");
                                    TextView mysteryPeakRunsOpen = findViewById(R.id.textView343);
                                    mysteryPeakRunsOpen.setText("Runs Open: " + seymourSingleton.mysteryPeakExpressRunsOpen+ "/20");
                                    TextView mysteryPeakTerrainParksText = findViewById(R.id.textView424);
                                    mysteryPeakTerrainParksText.setText("Terrain Parks Open: " + seymourSingleton.mysteryPeakExpressTerrainParksOpen + "/2");
                                    TextView magicCarpetRunsOpen = findViewById(R.id.textView344);
                                    magicCarpetRunsOpen.setText("Runs Open: " + seymourSingleton.goldieMagicCarpetRunsOpen + "/3");
                                    TextView goldieMagicCarpetTerrainParksText = findViewById(R.id.textView425);
                                    if (seymourSingleton.mushroom.equals("open")) {
                                        goldieMagicCarpetTerrainParksText.setText("Terrain Parks Open: 1/1");
                                    } else {
                                        goldieMagicCarpetTerrainParksText.setText("Terrain Parks Open: 0/1");
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

    public void openBrocktonChair() {
        Intent intent = new Intent(this, BrocktonChair.class);
        startActivity(intent);
    }

    public void openLodgeChair() {
        Intent intent = new Intent(this, LodgeChair.class);
        startActivity(intent);
    }

    public void openMagicCarpet() {
        Intent intent = new Intent(this, MagicCarpet.class);
        startActivity(intent);
    }

    public void openMysteryPeakExpress() {
        Intent intent = new Intent(this, MysteryPeakExpress.class);
        startActivity(intent);
    }

    public void setWeatherIcon(String conditions) {
        ImageView imageView319 = findViewById(R.id.imageView319);
        String[] arr = conditions.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        conditions = sb.toString().trim();

        switch (conditions) {
            case "Light Rain":
                imageView319.setImageResource(R.drawable.chance_of_showers);
                return;
            case "Snowing":
           // case "Fresh snow":
                imageView319.setImageResource(R.drawable.snow);
                return;
            case "Sunny":
            case "Fresh Snow":
            case "Chilly":
                imageView319.setImageResource(R.drawable.sunny);
                return;
            case "Clear":
                imageView319.setImageResource(R.drawable.clear_night);
                return;
            case "Overcast":
                imageView319.setImageResource(R.drawable.overcast);
                return;
            case "Light Snow":
                imageView319.setImageResource(R.drawable.light_snow);
                return;
            case "Mix Of Sun And Cloud":
                imageView319.setImageResource(R.drawable.a_mix_of_sun_and_cloud);
                return;
            case "Raining":
                imageView319.setImageResource(R.drawable.rain);
                return;
            case "Windy":
                imageView319.setImageResource(R.drawable.windy);
                return;
            case "A Mix Of Sun And Snow":
                imageView319.setImageResource(R.drawable.chance_of_flurries);
                return;
            case "Clear Skies":
                if (hour < 17) {
                    imageView319.setImageResource(R.drawable.sunny);
                } else {
                    imageView319.setImageResource(R.drawable.clear_night);
                }
                return;
        }
    }


    public void setStatus(ImageView image, String status) {
        System.out.println("STTER" + status);
        System.out.println("IMASGD" + image.toString());

        switch (status.toLowerCase()) {
            case "open":
                image.setImageResource(R.drawable.greencheck);
                return;
            case "closed":
                image.setImageResource(R.drawable.redx);
                return;
            case "standby":
                image.setImageResource(R.drawable.triangle);
                return;
        }
    }

    public void refresh() {
        Intent intent = new Intent(this, Seymour.class);
        startActivity(intent);
    }
}