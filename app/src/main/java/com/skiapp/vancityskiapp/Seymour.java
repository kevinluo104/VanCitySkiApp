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

public class Seymour extends AppCompatActivity {

    private Calendar cal = Calendar.getInstance();
    private int hour = cal.get(Calendar.HOUR_OF_DAY);
    private SeymourSingleton seymourSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seymour);

        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBrocktonChair();
            }
        });
        Button button12 = findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLodgeChair();
            }
        });
        Button button13 = findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMysteryPeakExpress();
            }
        });
        Button button14 = findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMagicCarpet();
            }
        });
        Button button16 = findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.snow-forecast.com/resorts/Mount-Seymour/6day/mid"));
                startActivity(browserIntent);
            }
        });
        Button button23 = findViewById(R.id.button23);
        button23.setOnClickListener(new View.OnClickListener() {
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
                                    TextView textView323 = findViewById(R.id.textView323);
                                    textView323.setText(seymourSingleton.conditions);
                                    TextView textView322 = findViewById(R.id.textView322);
                                    textView322.setText(seymourSingleton.temperature);
                                    TextView textView324 = findViewById(R.id.textView324);
                                    textView324.setText("Visibility: " + seymourSingleton.visibility);
                                    TextView textView325 = findViewById(R.id.textView325);
                                    textView325.setText(seymourSingleton.snowConditions);
                                    TextView textView326 = findViewById(R.id.textView326);
                                    textView326.setText("Runs Open: " + seymourSingleton.runsOpen + "/41");
                                    TextView textView228 = findViewById(R.id.textView228);
                                    textView228.setText("Lifts Open: " + seymourSingleton.liftsOpen + "/4");
                                    TextView textView229 = findViewById(R.id.textView229);
                                    textView229.setText("Terrain Parks Open: " + seymourSingleton.terrainParksOpen + "/5");
                                    TextView textView230 = findViewById(R.id.textView230);
                                    textView230.setText("Tube Parks Open: " + seymourSingleton.tubeParksOpen + "/2");
                                    TextView textView231 = findViewById(R.id.textView231);
                                    String snowshoeTrialStatus = seymourSingleton.snowshoeTrailsStatus.substring(0, 1).toUpperCase() + seymourSingleton.snowshoeTrailsStatus.substring(1);
                                    textView231.setText("Snowshoe Trails Status: " + snowshoeTrialStatus);
                                    setWeatherIcon(seymourSingleton.conditions);
                                    TextView textView328 = findViewById(R.id.textView328);
                                    textView328.setText(seymourSingleton.fortyEightHrSnow);
                                    TextView textView330 = findViewById(R.id.textView330);
                                    textView330.setText(seymourSingleton.twentyFourHrSnow);
                                    TextView textView332 = findViewById(R.id.textView332);
                                    textView332.setText(seymourSingleton.sevenDaySnow);
                                    TextView textView334 = findViewById(R.id.textView334);
                                    textView334.setText(seymourSingleton.seasonSnow);

                                    ImageView imageView320 = findViewById(R.id.imageView320);
                                    setStatus(imageView320, seymourSingleton.brocktonChair);
                                    ImageView imageView321 = findViewById(R.id.imageView321);
                                    setStatus(imageView321, seymourSingleton.lodgeChair);
                                    ImageView imageView322 = findViewById(R.id.imageView322);
                                    setStatus(imageView322, seymourSingleton.mysteryPeakExpress);
                                    ImageView imageView323 = findViewById(R.id.imageView323);
                                    setStatus(imageView323, seymourSingleton.goldieMagicCarpet);

                                    ImageView imageView324 = findViewById(R.id.imageView324);
                                    setStatus(imageView324, seymourSingleton.enquistSnowTubePark);
                                    ImageView imageView325 = findViewById(R.id.imageView325);
                                    setStatus(imageView325, seymourSingleton.tobagganArea);
                                    ImageView imageView326 = findViewById(R.id.imageView326);
                                    setStatus(imageView326, seymourSingleton.discoverySnowshoeTrails);
                                    TextView textView341 = findViewById(R.id.textView341);
                                    textView341.setText("Runs Open: " + seymourSingleton.brocktonChairRunsOpen + "/11");
                                    TextView textView342 = findViewById(R.id.textView342);
                                    textView342.setText("Runs Open: " + seymourSingleton.lodgeChairRunsOpen + "/9");
                                    TextView textView423 = findViewById(R.id.textView423);
                                    textView423.setText("Terrain Parks Open: " + seymourSingleton.lodgeChairTerrainParksOpen + "/2");
                                    TextView textView343 = findViewById(R.id.textView343);
                                    textView343.setText("Runs Open: " + seymourSingleton.mysteryPeakExpressRunsOpen+ "/19");
                                    TextView textView424 = findViewById(R.id.textView424);
                                    textView424.setText("Terrain Parks Open: " + seymourSingleton.mysteryPeakExpressTerrainParksOpen + "/3");
                                    TextView textView344 = findViewById(R.id.textView344);
                                    textView344.setText("Runs Open: " + seymourSingleton.goldieMagicCarpetRunsOpen + "/3");
                                    TextView textView425 = findViewById(R.id.textView425);
                                    if (seymourSingleton.mushroom.equals("open")) {
                                        textView425.setText("Terrain Parks Open: 1/1");
                                    } else {
                                        textView425.setText("Terrain Parks Open: 0/1");
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

    public void setWeatherIcon(String text) {
        ImageView imageView319 = findViewById(R.id.imageView319);
        String[] arr = text.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        text = sb.toString().trim();

        switch (text) {
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
        }
            if (hour >= 6 && hour < 21) {
                switch (text) {
                }
            } else {
                switch (text) {
                }

            }
        }


    public void setStatus(ImageView image, String status) {
        switch (status) {
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