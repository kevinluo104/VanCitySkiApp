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

public class Cypress extends AppCompatActivity {
    private Calendar cal = Calendar.getInstance();
    private int hour = cal.get(Calendar.HOUR_OF_DAY);
    private CypressSingleton cypressSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cypress);
        TextView errorText = findViewById(R.id.textView256);
        errorText.setText("");
        Button openEagleExpress = findViewById(R.id.button5);
        openEagleExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEagleExpress();
            }
        });
        Button openLionsExpress = findViewById(R.id.button6);
        openLionsExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLionsExpress();
            }
        });
        Button openRavenRidge = findViewById(R.id.button7);
        openRavenRidge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRavenRidge();
            }
        });
        Button openEasyRider = findViewById(R.id.button8);
        openEasyRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEasyRider();
            }
        });
        Button openSkyChair = findViewById(R.id.button9);
        openSkyChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSkyChair();
            }
        });
        Button openMidwayChair = findViewById(R.id.button10);
        openMidwayChair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMidwayChair();
            }
        });
        Button refresh = findViewById(R.id.button21);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refresh();
            }
        });

        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    TextView temperature = findViewById(R.id.textView112); //temp
                                    temperature.setText(cypressSingleton.temperature + "°C");
                                    TextView conditions = findViewById(R.id.textView113); //cond
                                      /*  String[] arr = cypressSingleton.conditions.split(" ");
                                        StringBuffer sb = new StringBuffer();

                                        for (int i = 0; i < arr.length; i++) {
                                            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                                                    .append(arr[i].substring(1)).append(" ");
                                        }*/
                                    //    conditions.setText(sb.toString().trim());
                                    conditions.setText(cypressSingleton.conditions);

                                    ImageView weather = findViewById(R.id.imageView127);
                                    setCypressPic(cypressSingleton.conditions, weather);
                                    TextView fortyEightHrSnow = findViewById(R.id.textView120);
                                    fortyEightHrSnow.setText(cypressSingleton.fortyEightHrSnow);
                                    TextView twentyFourHrSnow = findViewById(R.id.textView118);
                                    twentyFourHrSnow.setText(cypressSingleton.twentyFourHrSnow);
                                    TextView sevenDaySnow = findViewById(R.id.textView122);
                                    sevenDaySnow.setText(cypressSingleton.sevenDaySnow);
                                    TextView seasonSnow = findViewById(R.id.textView124);
                                    seasonSnow.setText(cypressSingleton.seasonSnow);
                                   // TextView tv273 = findViewById(R.id.textView273);
                                   // tv273.setText("Conditions: " + cypressSingleton.snowConditions);
                                    ImageView eagleExpress = findViewById(R.id.imageView128);
                                    setChairliftStatus(eagleExpress, cypressSingleton.eagleExpress);
                                    ImageView lionsExpress = findViewById(R.id.imageView129);
                                    setChairliftStatus(lionsExpress, cypressSingleton.lionsExpress);
                                    ImageView ravenRidge = findViewById(R.id.imageView130);
                                    setChairliftStatus(ravenRidge, cypressSingleton.ravenRidge);
                                    ImageView easyRider = findViewById(R.id.imageView131);
                                    setChairliftStatus(easyRider, cypressSingleton.easyRider);
                                    ImageView skyChair = findViewById(R.id.imageView132);
                                    setChairliftStatus(skyChair, cypressSingleton.skyChair);
                                    ImageView midwayChair = findViewById(R.id.imageView133);
                                    setChairliftStatus(midwayChair, cypressSingleton.midwayChair);
                                    TextView liftsOpen = findViewById(R.id.textView116);
                                    liftsOpen.setText("Lifts Open: " + cypressSingleton.liftsOpen + "/6");
                                    System.out.println("Cypress Lifts Open: " + cypressSingleton.liftsOpen);
                                    TextView runsOpen = findViewById(R.id.textView115);
                                    runsOpen.setText("Runs Open: " + cypressSingleton.runsOpen + "/62");
                                    TextView terrainParksOpen = findViewById(R.id.textView226);
                                    terrainParksOpen.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpen + "/7");
                                    TextView runsOpenEagleExpress = findViewById(R.id.textView171);
                                    runsOpenEagleExpress.setText("Runs Open: " + cypressSingleton.runsOpenEagleExpress + "/13");
                                    TextView terrainParksOpenEagleExpress = findViewById(R.id.textView38);
                                    terrainParksOpenEagleExpress.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenEagleExpress + "/3");
                                    TextView runsOpenLionsExpress = findViewById(R.id.textView219);
                                    runsOpenLionsExpress.setText("Runs Open: " + cypressSingleton.runsOpenLionsExpress + "/24");
                                    TextView terrainParksOpenLionsExpress = findViewById(R.id.textView224);
                                    terrainParksOpenLionsExpress.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenLionsExpress + "/2");
                                    TextView runsOpenRavenRidge = findViewById(R.id.textView220);
                                    runsOpenRavenRidge.setText("Runs Open: " + cypressSingleton.runsOpenRavenRidge + "/13");
                                    TextView runsOpenEasyRider = findViewById(R.id.textView221);
                                    runsOpenEasyRider.setText("Runs Open: " + cypressSingleton.runsOpenEasyRider + "/1");
                                    TextView terrainParksOpenEasyRider = findViewById(R.id.textView225);
                                    terrainParksOpenEasyRider.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenEasyRider + "/2");
                                    TextView runsOpenSkyChair = findViewById(R.id.textView222);
                                    runsOpenSkyChair.setText("Runs Open: " + cypressSingleton.runsOpenSkyChair + "/6");
                                    TextView runsOpenMidwayChair = findViewById(R.id.textView223);
                                    runsOpenMidwayChair.setText("Runs Open: " + cypressSingleton.runsOpenMidwayChair + "/4");
                                }
                            }, getApplicationContext());
                            cypressSingleton.startThread();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Button forecast = findViewById(R.id.button15);
                        forecast.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.snow-forecast.com/resorts/Cypress-Mountain/6day/mid"));
                                startActivity(browserIntent);
                            }
                        });
                    }
                });
            }
        }.start();
    }


    public void openEagleExpress() {
        Intent intent = new Intent(this, EagleExpress.class);
        startActivity(intent);
    }


    public void openLionsExpress() {
        Intent intent = new Intent(this, LionsExpress.class);
        startActivity(intent);
    }

    public void openRavenRidge() {
        Intent intent = new Intent(this, RavenRidge.class);
        startActivity(intent);
    }

    public void openEasyRider() {
        Intent intent = new Intent(this, EasyRider.class);
        intent.putExtra("something", true);
        startActivity(intent);
    }


    public void openSkyChair() {
        Intent intent = new Intent(this, SkyChair.class);
        startActivity(intent);
    }

    public void openMidwayChair() {
        Intent intent = new Intent(this, MidwayChair.class);
        startActivity(intent);
    }

    public void setCypressPic(String text, ImageView image) {
        String[] arr = text.split(" ");
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                    .append(arr[i].substring(1)).append(" ");
        }
        text = sb.toString().trim();
        if (text.equals("Cloudy")) {
            image.setImageResource(R.drawable.cloudy);
            return;
        }
        if (text.equals("Clear Night")) {
            image.setImageResource(R.drawable.clear_night);
            return;
        }
        if (text.equals("Rain/Snow")) {
            image.setImageResource(R.drawable.wet_snow_mixed_with_rain);
            return;
        }
        if (text.equals("Sunny")) {
            image.setImageResource(R.drawable.sunny);
            return;
        }
        if (text.equals("Snow")) {
            image.setImageResource(R.drawable.snow);
            return;
        }
        if (text.equals("Snow Showers/some Snow")) {
            image.setImageResource(R.drawable.periods_of_light_snow);
            return;
        }
        if (text.equals("Rain")) {
            image.setImageResource(R.drawable.rain);
            return;
        }
        if (text.equals("Partly Cloudy Night")) {
            image.setImageResource(R.drawable.cloudy_periods);
            return;
        }
        if (text.equals("Windy")) {
            image.setImageResource(R.drawable.windy);
            return;
        }
        if (text.equals("Freezing Rain/ice Pellets")) {
            image.setImageResource(R.drawable.ice_pellets);
            return;
        }
        if (text.equals("Cloud/sun/mixed")) {
            image.setImageResource(R.drawable.a_mix_of_sun_and_cloud);
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


    public void setChairliftStatus(ImageView image, String status) {
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
        Intent intent = new Intent(this, Cypress.class);
        startActivity(intent);
    }

}
