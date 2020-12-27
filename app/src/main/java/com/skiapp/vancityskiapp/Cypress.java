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
        TextView tv256 = findViewById(R.id.textView256);
        tv256.setText("");
        Button button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEagleExpress();
            }
        });
        Button button6 = findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLionsExpress();
            }
        });
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRavenRidge();
            }
        });
        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEasyRider();
            }
        });
        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSkyChair();
            }
        });
        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMidwayChair();
            }
        });
        Button button21 = findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
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
                                    TextView textView112 = findViewById(R.id.textView112); //temp
                                    textView112.setText(cypressSingleton.temperature + "°C");
                                    TextView textView113 = findViewById(R.id.textView113); //cond
                                      /*  String[] arr = cypressSingleton.conditions.split(" ");
                                        StringBuffer sb = new StringBuffer();

                                        for (int i = 0; i < arr.length; i++) {
                                            sb.append(Character.toUpperCase(arr[i].charAt(0)))
                                                    .append(arr[i].substring(1)).append(" ");
                                        }*/
                                    //    textView113.setText(sb.toString().trim());
                                    textView113.setText(cypressSingleton.conditions);

                                    ImageView imageView127 = findViewById(R.id.imageView127);
                                    setCypressPic(cypressSingleton.conditions, imageView127);
                                    TextView tv120 = findViewById(R.id.textView120);
                                    tv120.setText(cypressSingleton.fortyEightHrSnow);
                                    TextView tv118 = findViewById(R.id.textView118);
                                    tv118.setText(cypressSingleton.twentyFourHrSnow);
                                    TextView tv122 = findViewById(R.id.textView122);
                                    tv122.setText(cypressSingleton.sevenDaySnow);
                                    TextView tv124 = findViewById(R.id.textView124);
                                    tv124.setText(cypressSingleton.seasonSnow);
                                    TextView tv273 = findViewById(R.id.textView273);
                                    tv273.setText("Conditions: " + cypressSingleton.snowConditions);
                                    ImageView imageView128 = findViewById(R.id.imageView128);
                                    setChairliftStatus(imageView128, cypressSingleton.eagleExpress);
                                    ImageView imageView129 = findViewById(R.id.imageView129);
                                    setChairliftStatus(imageView129, cypressSingleton.lionsExpress);
                                    ImageView imageView130 = findViewById(R.id.imageView130);
                                    setChairliftStatus(imageView130, cypressSingleton.ravenRidge);
                                    ImageView imageView131 = findViewById(R.id.imageView131);
                                    setChairliftStatus(imageView131, cypressSingleton.easyRider);
                                    ImageView imageView132 = findViewById(R.id.imageView132);
                                    setChairliftStatus(imageView132, cypressSingleton.skyChair);
                                    ImageView imageView133 = findViewById(R.id.imageView133);
                                    setChairliftStatus(imageView133, cypressSingleton.midwayChair);
                                    TextView textView116 = findViewById(R.id.textView116);
                                    textView116.setText("Lifts Open: " + cypressSingleton.liftsOpen + "/6");
                                    System.out.println("Cypress Lifts Open: " + cypressSingleton.liftsOpen);
                                    TextView textView115 = findViewById(R.id.textView115);
                                    textView115.setText("Runs Open: " + cypressSingleton.runsOpen + "/61");
                                    TextView textView226 = findViewById(R.id.textView226);
                                    textView226.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpen + "/6");
                                    TextView textView171 = findViewById(R.id.textView171);
                                    textView171.setText("Runs Open: " + cypressSingleton.runsOpenEagleExpress + "/13");
                                    TextView textView38 = findViewById(R.id.textView38);
                                    textView38.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenEagleExpress + "/3");
                                    TextView textView219 = findViewById(R.id.textView219);
                                    textView219.setText("Runs Open: " + cypressSingleton.runsOpenLionsExpress + "/24");
                                    TextView textView224 = findViewById(R.id.textView224);
                                    textView224.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenLionsExpress + "/1");
                                    TextView textView220 = findViewById(R.id.textView220);
                                    textView220.setText("Runs Open: " + cypressSingleton.runsOpenRavenRidge + "/13");
                                    TextView textView221 = findViewById(R.id.textView221);
                                    textView221.setText("Runs Open: " + cypressSingleton.runsOpenEasyRider + "/1");
                                    TextView textView225 = findViewById(R.id.textView225);
                                    textView225.setText("Terrain Parks Open: " + cypressSingleton.terrainParksOpenEasyRider + "/2");
                                    TextView textView222 = findViewById(R.id.textView222);
                                    textView222.setText("Runs Open: " + cypressSingleton.runsOpenSkyChair + "/6");
                                    TextView textView223 = findViewById(R.id.textView223);
                                    textView223.setText("Runs Open: " + cypressSingleton.runsOpenMidwayChair + "/4");
                                }
                            }, getApplicationContext());
                            cypressSingleton.startThread();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Button button15 = findViewById(R.id.button15);
                        button15.setOnClickListener(new View.OnClickListener() {
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
        if (text.equals("Rain")) {
            image.setImageResource(R.drawable.rain);
            return;
        }
        if (text.equals("Partly Cloudy Night")) {
            image.setImageResource(R.drawable.cloudy_periods);
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
