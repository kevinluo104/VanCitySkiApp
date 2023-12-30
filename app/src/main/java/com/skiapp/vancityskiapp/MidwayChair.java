package com.skiapp.vancityskiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MidwayChair extends AppCompatActivity {

    private CypressSingleton cypressSingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midway_chair);
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            cypressSingleton = CypressSingleton.getInstance(new ResultListener() {
                                @Override
                                public void onResultFetched() {
                                    ImageView status = findViewById(R.id.imageView305);
                                    switch(cypressSingleton.midwayChair) {
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

                                    ImageView shuttle = findViewById(R.id.imageView306);
                                    ImageView blaster = findViewById(R.id.imageView315);
                                    ImageView hutRun = findViewById(R.id.imageView316);
                                    ImageView webbSite = findViewById(R.id.imageView317);
                                    setStatus(shuttle, cypressSingleton.shuttle);
                                    setStatus(blaster, cypressSingleton.blaster);
                                    setStatus(hutRun, cypressSingleton.hutRun);
                                    setStatus(webbSite, cypressSingleton.webbSite);

                                    TextView runsOpen = findViewById(R.id.textView315);
                                    runsOpen.setText("Runs Open: " + cypressSingleton.runsOpenMidwayChair + "/4");
                                }
                            }, getApplicationContext());
                            cypressSingleton.startThread();
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
}
